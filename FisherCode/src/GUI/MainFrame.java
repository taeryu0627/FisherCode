package GUI;

import javax.swing.*;
import java.awt.*;

import Models.Script;
import Models.Stage1Script;
import GUI.ResultPanel;

public class MainFrame extends JFrame {

    // 게임 상태 변수
    private Script currentScript;
    private Stage1Script stage1 = new Stage1Script();
    private int wrongCount = 0;     // 틀린 개수
    private int correctCount = 0;   // 맞춘 개수

    // 🔹 현재 스테이지 (1~3)
    private int currentStage = 1;

    // UI 구성 요소
    private PhonePanel phonePanel;
    private NotePanel notePanel;

    // 🔹 이제는 "맞은 문제"가 아니라 "현재 스테이지"를 표시
    private JLabel stageLabel; 

    // (defaultLines / domainLines / patternLines 는 NotePanel에서 쓰고 있으니
    //  계속 두어도 되고, 안 쓰면 지워도 됨)
    private String[] defaultLines() {
        return new String[]{
                "신뢰할 수 없는 유형의 URL 접속을 주의해야 한다",
                "",
                "1. 알 수 없는 최상위 도메인",
                "   - .com, .org 등 신뢰 도메인",
                "   - .xyz, .biz 등 생소 도메인",
                "",
                "2. 유사 도메인 링크 조심",
                "   - google.com -> g00gle.com",
                "   - paypal.com -> paypaI.com"
        };
    }

    private String[] domainLines() {
        return new String[]{
                "URL 클릭 전 반드시 발신자를 다시 확인하세요.",
                "",
                "1. 은행/관공서는 공식 도메인을 사용",
                "2. '지금 바로 클릭'은 위험 신호",
                "3. 개인정보 요청은 99% 스미싱"
        };
    }

    private String[] patternLines() {
        return new String[]{
                "자주 쓰이는 스미싱 패턴",
                "",
                "- 택배 배송 조회 링크",
                "- 본인 인증 유도",
                "- 과태료/벌금 미납 안내",
                "- 재난 지원금 링크",
                "",
                "의심되면 직접 전화 확인!"
        };
    }

    public MainFrame() {
        setTitle("보이스피싱 예방 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);

        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        setLayout(new BorderLayout(20, 0));

        createMainLayout();   // 메인 게임 화면 구성
        loadRandomScript();   // 첫 문제 출제

        setVisible(true);
    }


    // 메인 레이아웃 UI 구성
    private void createMainLayout() {

        // 배경 패널 적용
        BackgroundPanel mainPanel = new BackgroundPanel("/resources/background/Dialog1.png");
        mainPanel.setLayout(new BorderLayout(20, 20));
        setContentPane(mainPanel);

        // 🔹 상단: 현재 스테이지 표시
        stageLabel = new JLabel("현재 스테이지: " + currentStage, SwingConstants.RIGHT);
        stageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        // 🔹 화면 모서리에서 살짝 띄우기 (위쪽 10px, 오른쪽 20px)
        stageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 20));

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setOpaque(false);
        northPanel.add(stageLabel, BorderLayout.EAST);

        mainPanel.add(northPanel, BorderLayout.NORTH);


        // 중앙: Phone + Note
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        centerPanel.setOpaque(false);

        // Phone Panel
        phonePanel = new PhonePanel("/resources/PhoneImg_2.png");
        centerPanel.add(phonePanel);

        // Note Panel (내부에 버튼 포함)
        notePanel = new NotePanel("/resources/NoteImg3.png");
        centerPanel.add(notePanel);

        // 오른쪽 Yes/No 버튼
        ButtonPanel yesBtn = new ButtonPanel(true);
        yesBtn.addActionListener(e -> checkAnswer(true));

        ButtonPanel noBtn = new ButtonPanel(false);
        noBtn.addActionListener(e -> checkAnswer(false));

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        btnPanel.add(yesBtn);
        btnPanel.add(Box.createVerticalStrut(20));
        btnPanel.add(noBtn);

        JPanel eastWrapper = new JPanel(new BorderLayout());
        eastWrapper.setOpaque(false);
        eastWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        eastWrapper.add(btnPanel, BorderLayout.SOUTH);

        // 최종 배치
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(eastWrapper, BorderLayout.EAST);
    }

 // 🔹 스테이지 계산 & 라벨 + 연출
    private void updateStage() {
        int oldStage = currentStage;
        int newStage;

        if (correctCount >= 8) {
            newStage = 3;
        } else if (correctCount >= 4) {
            newStage = 2;
        } else {
            newStage = 1;
        }

        currentStage = newStage;

        // 라벨 텍스트 갱신
        if (stageLabel != null) {
            stageLabel.setText("현재 스테이지: " + currentStage);
        }

        // 🔥 스테이지가 올라갔을 때만 연출
        if (newStage > oldStage) {
            JOptionPane.showMessageDialog(
                    this,
                    "스테이지 " + newStage + "에 진입했습니다!",
                    "Stage Up!",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // 정답 확인 로직 (3번 틀리면 결과 화면 이동)
    private void checkAnswer(boolean userAnswer) {
        boolean correct = (userAnswer == currentScript.isScam());

        if (correct) {
            correctCount++;

            // 🔹 정답 맞출 때마다 스테이지 갱신
            updateStage();

            JOptionPane.showMessageDialog(
                    this,
                    "정확한 판단입니다!",
                    "정답",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadRandomScript();

        } else {
            wrongCount++;

            JOptionPane.showMessageDialog(
                    this,
                    "다시 생각해보세요!",
                    "틀렸습니다",
                    JOptionPane.ERROR_MESSAGE
            );

            if (wrongCount >= 3) {
                showResultPanel(); // 결과 창 띄우기
            }
        }
    }

    // 랜덤 문제 호출
    private void loadRandomScript() {
        currentScript = stage1.getRandomScript();
        phonePanel.setRecvMessage(currentScript.getMessage());
        phonePanel.setReplyMessage("링크에 들어가도 되나요?");
    }

    // 결과 화면으로 전환
    private void showResultPanel() {
        getContentPane().removeAll();
        setContentPane(new ResultPanel(correctCount, wrongCount, this::restartGame));

        revalidate();
        repaint();
    }

    // 게임 재시작
    public void restartGame() {
        wrongCount = 0;
        correctCount = 0;
        currentStage = 1;          // 🔹 스테이지도 1로 초기화

        getContentPane().removeAll();
        createMainLayout();
        loadRandomScript();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

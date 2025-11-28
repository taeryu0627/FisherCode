package GUI;

import javax.swing.*;
import java.awt.*;

import Models.Script;
import Models.MainModel;

public class MainFrame extends JFrame {

    // 게임 상태 변수
	private MainModel mainModel = new MainModel();
    private Script currentScript;
    private int wrongCount = 0;     // 틀린 개수
    private int correctCount = 0;   // 맞춘 개수

    // 현재 스테이지 (1~3)
    private int currentStage = 1;

    // UI 구성 요소
    private PhonePanel phonePanel;
    private NotePanel notePanel;

    // "현재 스테이지"를 표시
    private JLabel stageLabel; 


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

        // 상단: 현재 스테이지 표시
        stageLabel = new JLabel("현재 스테이지: " + currentStage, SwingConstants.RIGHT);
        stageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        // 화면 모서리에서 살짝 띄우기 (위쪽 10px, 오른쪽 20px)
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

 // 스테이지 계산 & 라벨 + 연출
    private void updateStage() {
        int oldStage = currentStage;
        int newStage;

        if (correctCount >= 12)      newStage = 4;
        else if (correctCount >= 8)  newStage = 3;
        else if (correctCount >= 4)  newStage = 2;
        else                         newStage = 1;

        currentStage = newStage;

        if (stageLabel != null) {
            stageLabel.setText("현재 스테이지: " + currentStage);
        }

        // 🔹 노트 패널에게도 스테이지 변경 사실 알려주기
        if (notePanel != null) {
            notePanel.updateStage(currentStage);
        }

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

            // 정답 맞출 때마다 스테이지 갱신
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
        currentScript = mainModel.getScriptForStage(currentStage);

        if (currentScript == null) {
            System.err.println("[MainFrame] currentScript가 null입니다. 문제를 불러오지 못했습니다.");
            return;
        }

        phonePanel.setRecvMessage(currentScript.getMessage());
        // phonePanel.setReplyMessage("링크에 들어가도 되나요?");
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
        currentStage = 1;          // 스테이지도 1로 초기화

        getContentPane().removeAll();
        createMainLayout();
        loadRandomScript();
        revalidate();
        repaint();
    }
    
    // 메인 테스트를 위해 남겨둠
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
*/
}

package GUI;

import javax.swing.*;
import java.awt.*;

import Models.Script;
import Models.Stage1Script;
import GUI.ResultPanel;

public class MainFrame extends JFrame {

    // -----------------------
    //  Fields
    // -----------------------
    private Script currentScript;
    private final Stage1Script stage1 = new Stage1Script();

    private PhonePanel phonePanel;  // 중앙 폰 UI 연결용

    private int correctCount = 0;   // 정답 수
    private int wrongCount = 0;     // 오답 수
    private JLabel scoreLabel;


    // -----------------------
    //  Constructor
    // -----------------------
    public MainFrame() {
        setTitle("보이스피싱 예방 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);

        // 프레임 기본 여백
        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        setLayout(new BorderLayout(20, 0));

        mainDesign();       // 첫 화면 구성
        loadRandomScript(); // 첫 문제 로딩

        setVisible(true);
    }


    // -----------------------
    //  메인 UI 구성
    // -----------------------
    private void mainDesign() {

        // 배경 패널 적용
        BackgroundPanel mainPanel =
                new BackgroundPanel("/resources/background/Dialog1.png");
        mainPanel.setLayout(new BorderLayout(20, 20));
        setContentPane(mainPanel);
        
        scoreLabel = new JLabel("맞춘 문제: 0개");
        scoreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);  // ⭐ 오른쪽 정렬

        mainPanel.add(scoreLabel, BorderLayout.NORTH);

        // 중앙(폰 + 노트)
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        centerPanel.setOpaque(false);

        phonePanel = new PhonePanel("/resources/PhoneImg_2.png");
        centerPanel.add(phonePanel);

        centerPanel.add(new NotePanel("/resources/NoteImg.png"));

        // 오른쪽 버튼 영역
        JPanel buttonPanel = createButtonPanel();

        // 배경 패널에 추가
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
    }


    // -----------------------
    //  YES / NO 버튼 패널
    // -----------------------
    private JPanel createButtonPanel() {

        ButtonPanel yesBtn = new ButtonPanel(true);
        yesBtn.addActionListener(e -> checkAnswer(true));

        ButtonPanel noBtn = new ButtonPanel(false);
        noBtn.addActionListener(e -> checkAnswer(false));

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        btnPanel.add(yesBtn);
        btnPanel.add(Box.createVerticalStrut(20)); // 간격
        btnPanel.add(noBtn);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        wrapper.add(btnPanel, BorderLayout.SOUTH);

        return wrapper;
    }


    // -----------------------
    //  문제 정답 체크
    // -----------------------
    private void checkAnswer(boolean userAnswer) {
        boolean correct = (userAnswer == currentScript.isScam());

        if (correct) {
        	correctCount++;
        	updateScoreLabel();

            Object[] options = {"다음 문제"};
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "정확한 판단입니다",
                    "정답입니다!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                loadRandomScript();
            }

        } else {
            wrongCount++;

            // 3회 틀리면 바로 결과 화면으로 이동
            if (wrongCount >= 3) {
                showResultPanel();
                return;
            }

            Object[] options = {"재도전"};
            JOptionPane.showOptionDialog(
                    this,
                    "다시 생각해보세요 (" + wrongCount + "/3)",
                    "틀렸습니다!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[0]
            );
        }
    }

    private void updateScoreLabel() {
        if (scoreLabel != null) {
            scoreLabel.setText("맞춘 문제: " + correctCount + "개");
        }
    }

    // -----------------------
    //  랜덤 시나리오 로딩
    // -----------------------
    private void loadRandomScript() {

        currentScript = stage1.getRandomScript();

        phonePanel.setRecvMessage(currentScript.getMessage());
        phonePanel.setReplyMessage("링크에 들어가도 되나요?");
    }


    // -----------------------
    //  결과 화면 전환
    // -----------------------
    private void showResultPanel() {

        ResultPanel resultPanel =
                new ResultPanel(correctCount, wrongCount, this::restartGame);

        setContentPane(resultPanel);

        // 프레임 여백 유지
        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        revalidate();
        repaint();
    }


    // -----------------------
    //  게임 다시 시작
    // -----------------------
    private void restartGame() {

        // 스코어 초기화
        correctCount = 0;
        wrongCount = 0;

        // 메인 화면 재구성 + 첫 문제 로딩
        mainDesign();
        loadRandomScript();

        revalidate();
        repaint();
    }


    // -----------------------
    //  Entry Point
    // -----------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

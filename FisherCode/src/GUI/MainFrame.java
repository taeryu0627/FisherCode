package GUI;

import javax.swing.*;
import java.awt.*;

import Models.Script;
import Models.ScriptList;

public class MainFrame extends JFrame {

    private Script currentScript;
    private ScriptList stage1 = new ScriptList();

    // ★ PhonePanel을 필드로 선언
    private PhonePanel phonePanel;

    public MainFrame() {
        setTitle("보이스피싱 예방 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);

        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        setLayout(new BorderLayout(20, 0));

        // 버튼 세팅
        mainDesign();

        // ★ 첫 문제 랜덤 출제
        loadRandomScript();

        setVisible(true);
    }

    private void mainDesign() {
        // 중앙 : 폰 + 노트
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 30, 0));

        // ★ 여기서 PhonePanel을 필드에 할당
        phonePanel = new PhonePanel("/resources/PhoneImg_2.png");
        centerPanel.add(phonePanel);

        centerPanel.add(new NotePanel("/resources/NoteImg.png"));
        add(centerPanel, BorderLayout.CENTER);
        
        // 우측 아래 : 도장 버튼 두 개
        ButtonPanel yesBtn = new ButtonPanel(true);
        yesBtn.addActionListener(e -> checkAnswer(true));

        ButtonPanel noBtn  = new ButtonPanel(false);
        noBtn.addActionListener(e -> checkAnswer(false));

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        btnPanel.add(yesBtn);
        btnPanel.add(Box.createVerticalStrut(20)); //여백 추가
        btnPanel.add(noBtn);

        JPanel eastWrapper = new JPanel(new BorderLayout());
        eastWrapper.setOpaque(false);
        eastWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        eastWrapper.add(btnPanel, BorderLayout.SOUTH);
        add(eastWrapper, BorderLayout.EAST);
    }

    /// ------------ 스미싱 여부 판단 ------------
    private void checkAnswer(boolean userAnswer) {
        boolean correct = (userAnswer == currentScript.isScam());

        if (correct) {
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
                loadRandomScript(); // ★ 다음 랜덤 문제
            }

        } else {
            Object[] options = {"재도전"};
            JOptionPane.showOptionDialog(
                    this,
                    "다시 생각해보세요",
                    "틀렸습니다!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[0]
            );
        }
    }

    /// ------------ 랜덤 시나리오 함수 ------------
    private void loadRandomScript() {
        // ★ Stage1Script에서 랜덤 Script 하나 받아오기
        currentScript = stage1.getRandomScript();

        // ★ PhonePanel에 문자 내용/답장 세팅
        phonePanel.setRecvMessage(currentScript.getMessage());
        phonePanel.setReplyMessage("링크에 들어가도 되나요?");

        // 필요하면 NotePanel도 여기서 같이 업데이트 가능
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

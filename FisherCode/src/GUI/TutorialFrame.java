package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogFrame extends JFrame {

    private Image backgroundImage1;
    private Image backgroundImage2;
    private JTextArea textArea;
    private JLabel nextArrow;
    
    private String[] dialogs = {
    		"김경사(나)\n여기가 내가 새로 발령받은 스미싱 대응팀 부서구나.",
    		"김경사(나)\n팀 이름이 피셔 코드라고 하던데\n내가 잘할 수 있을까?",
    		"\n(누군가 걸어오는 소리)",
    		"김경사(나)\n저 사람은?",
    		"박경위\n너가 새로 전임해 온 김경사인가?",
    		"김경사(나)\n네 제가 김경사입니다.",
    		"박경위\n현재 피셔 코드 부서는 스미싱 대응으로 인해 인력이 매우 부족한 상황이야.\n자네에겐 미안하지만\n바로 부서 적응에 필요한 기초 교육을 실시하겠네",
    		"김경사(나)\n알겠습니다."

    };
    private int dialogIndex = 0;
    private int charIndex = 0;
    private Timer typingTimer;
    private int typingSpeed = 20;
    
    public DialogFrame() {
        setTitle("대화창");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);

        backgroundImage1 = new ImageIcon("/resources/background/Dialog1.png").getImage();
        backgroundImage2 = new ImageIcon("/resources/background/Dialog2.png").getImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (dialogIndex >= 3) {
                    g.drawImage(backgroundImage2, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.drawImage(backgroundImage1, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(null);
        add(backgroundPanel);

        // 화살표 버튼
        ImageIcon arrowIcon = new ImageIcon("/resources/Arrow.png");
        Image scaledArrow = arrowIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        nextArrow = new JLabel(new ImageIcon(scaledArrow));
        nextArrow.setBounds(1120, 550, 60, 60);  
        nextArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextArrow.setVisible(false); // 대사 다 끝나면 보이게
        backgroundPanel.add(nextArrow);    
        
        nextArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (typingTimer != null && typingTimer.isRunning()) {
            		textArea.setText(dialogs[dialogIndex]);
                    typingTimer.stop();
                    nextArrow.setVisible(true);
                } else {
                	showNextDialog();
                	if (dialogIndex == 3) { 
                        backgroundPanel.repaint();
                	}
                }
            }
        });
        
        // 텍스트 영역
        textArea = new JTextArea();
        textArea.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        textArea.setForeground(Color.WHITE);
        //textArea.setOpaque(true);
        textArea.setBackground(new Color(30, 120, 200));
        textArea.setBounds(100, 430, 1090, 200);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        backgroundPanel.add(textArea);

        startTypingAnimation();
        setVisible(true);
    }
    
    
    // 채팅 효과
    private void startTypingAnimation() {
        textArea.setText(""); 
        charIndex = 0;
    	
        typingTimer = new Timer(typingSpeed, e -> {
            if (charIndex < dialogs[dialogIndex].length()) {
                textArea.append(String.valueOf(dialogs[dialogIndex].charAt(charIndex++)));
            } else {
                typingTimer.stop();
                nextArrow.setVisible(true);
            }
        });
        typingTimer.start();
    }

    private void showNextDialog() {
        dialogIndex++;

        if (dialogIndex >= dialogs.length) {
            dispose();
            new TutorialFrame().setVisible(true);
            return;
        }

        nextArrow.setVisible(false);
        startTypingAnimation();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DialogFrame::new);
    }
}


package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TutorialFrame extends JFrame {
	/**
	 * 튜토리얼 화면
	 * dialogs : 출력 대사
	 * dialogIndex넘버에 따라서 튜토리얼 이미지 전환
	 */
	
    private Image TutorialIMG1;
    private Image TutorialIMG2;
    private Image TutorialIMG3;
    private Image TutorialIMG4;
    private Image TutorialIMG5;
    private Image TutorialIMG6;
    private Image TutorialIMG7;

    private JTextArea textArea;
    private JLabel nextArrow;
    
    // --- 튜토리얼 대사 ---
    private String[] dialogs = {
    		// 대화 부분 (dialogIndex0 ~ dialogIndex6)
    		"김경사(나)\n여기가 내가 새로 발령받은 스미싱 대응팀 부서구나.",
    		"김경사(나)\n팀 이름이 피셔 코드라고 하던데\n내가 잘할 수 있을까?",
    		"\n(누군가 걸어오는 소리)",
    		"김경사(나)\n저 사람은?", // dialogIndex3
    		"박경위\n너가 새로 전임해 온 김경사인가?",
    		"김경사(나)\n네 제가 김경사입니다.",
    		"박경위\n현재 피셔 코드 부서는 스미싱 대응으로 인해 인력이 매우 부족한 상황이야.\n자네에겐 미안하지만\n바로 부서 적응에 필요한 기초 교육을 실시하겠네",
    		"김경사(나)\n알겠습니다.",
    		// 튜토리얼 시작 (dialogIndex8 ~ dialogIndex15)
    		"박경위\n업무를 시작하면 휴대폰 하나와 노트 하나를 지급 받네.", // dialogIndex8
    		"박경위\n왼쪽의 휴대폰을 통해서 메시지 내용을 확인할 수 있고\n"
    		+ "오른쪽의 노트를 통해서는 스미싱 검거 매뉴얼을 확인할 수 있어.",
    		"박경위\n직무를 시작하기에 앞서 직무 수행 방법을 알려주겠네.", // dialogIndex10
    		"박경위\n왼쪽의 휴대폰 메시지는 시민들이 스미싱인지 아닌지 확인하기 위해서 보내온 메시지네.", //11
    		"박경위\n오른쪽의 노트에는 최근 스미싱 동향에 따른 대응 매뉴얼이 적혀있어.\n" // 12
    		+ "매뉴얼의 내용은 매일 새롭게 추가되며 꼼꼼히 확인해야 해.",
    		"박경위\n노트의 옆에는 스미싱 여부를 판단 하는 버튼이 있네.", // dialogIndex13
    		"박경위\n이렇게 간단하게 직무 수행 방법에 대한 설명이 끝났네.\n"
    		+ "모르는 것은 앞으로 일을 하면서 배우도록.",
    		"김경사(나)\n도착하자마자 업무 시작이라니...."
    };
    private int dialogIndex = 0;
    private int charIndex = 0;
    private Timer typingTimer;
    private int typingSpeed = 20;
    
    public TutorialFrame() {
        setTitle("튜토리얼");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);
        // --- 튜토리얼 이미지 ---
        TutorialIMG1 = new ImageIcon("/resources/background/Dialog1.png").getImage();
        TutorialIMG2 = new ImageIcon("/resources/background/Dialog2.png").getImage();
        TutorialIMG3 = new ImageIcon("/resources/background/Tutorial1.png").getImage();
        TutorialIMG4 = new ImageIcon("/resources/background/Tutorial2.png").getImage();
        TutorialIMG5 = new ImageIcon("/resources/background/Tutorial3.png").getImage();
        TutorialIMG6 = new ImageIcon("/resources/background/Tutorial4.png").getImage();
        TutorialIMG7 = new ImageIcon("/resources/background/Tutorial5.png").getImage();
        
        // --- 튜토리얼 이미지 순서 ---
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image currentImage = TutorialIMG1;
                
                if (dialogIndex >= 14) {
                    currentImage = TutorialIMG2;
                } else if (dialogIndex >= 13) {
                    currentImage = TutorialIMG7;
                } else if (dialogIndex >= 12) {
                    currentImage = TutorialIMG6;
                } else if (dialogIndex >= 11) {
                    currentImage = TutorialIMG5;
                } else if (dialogIndex >= 10) {
                    currentImage = TutorialIMG4;
                } else if (dialogIndex >= 8) {
                    currentImage = TutorialIMG3;
                } else if (dialogIndex >= 3) {
                    currentImage = TutorialIMG2;
                } else {
                    currentImage = TutorialIMG1;
                }
                g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        add(backgroundPanel);

        // --- 다음 대화 화살표 버튼 ---
        ImageIcon arrowIcon = new ImageIcon("/resources/Arrow.png");
        Image scaledArrow = arrowIcon.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);

        nextArrow = new JLabel(new ImageIcon(scaledArrow));
        nextArrow.setBounds(1100, 550, 80, 60);  
        nextArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextArrow.setVisible(false); // 초기에는 표시 X (대화가 끝난 후 표시)
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
                    if (dialogIndex == 3 || dialogIndex == 8 || dialogIndex == 10 || dialogIndex == 11 || dialogIndex == 12 || dialogIndex == 13 || dialogIndex == 14) {
                        backgroundPanel.repaint();
                    }
                }
            }
        });
        
        // --- 텍스트 영역 ---
        textArea = new JTextArea();
        Font customFont = loadFont("/resources/fonts/HY울릉도B.ttf", 24f);
        textArea.setFont(customFont);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(new Color(30, 120, 200));
        textArea.setBounds(100, 430, 1090, 200);
        textArea.setMargin(new Insets(10, 10, 10, 10));
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

    // 폰트 로드
    private Font loadFont(String path, float size) {
        try {
            return Font.createFont(
                    Font.TRUETYPE_FONT,
                    new java.io.File("/resources/font/HY울릉도B.ttf")
            ).deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("맑은 고딕", Font.PLAIN, (int) size);
        }
    }
    // 대화가 끝나면 MainFrame 표시
    private void showNextDialog() {
        dialogIndex++;

        if (dialogIndex >= dialogs.length) {
            dispose();
            new MainFrame().setVisible(true);
            return;
        }

        nextArrow.setVisible(false);
        startTypingAnimation();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TutorialFrame::new);
    }
}


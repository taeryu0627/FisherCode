package FischerCode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stage1 extends JFrame{
    private Stage1Script stage1Script = new Stage1Script();
    private Script currentScript;
    private JTextArea receiveMsg;    
    
    private ImageIcon phoneImg;
    private ImageIcon memoImg;
    private ImageIcon btnYesImg;
    private ImageIcon btnNoImg;
    //private imageIcon btnYesHoverimg;
    //private imageIcon btnNoHoverimg;

    private JLabel btnYes;
    private JLabel btnNo;
    
	public Stage1() {
		setTitle("피셔코드 메인 GUI 구상");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,500);
		setLocationRelativeTo(null);
				
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        add(mainPanel);
        
        /// ------------------------
        /// 왼쪽 폰 화면
        /// ------------------------
        
        phoneImg = new ImageIcon("src/images/경찰청 휴대폰.png");

        int phoneWidth = 500; 
        int phoneHeight = 500; 

        JPanel phonePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(phoneImg.getImage(), -20, -20, phoneWidth, phoneHeight, this);
            }
        };
        phonePanel.setLayout(null);
        mainPanel.add(phonePanel);
        
        // 대화 패널
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(null);
        chatPanel.setOpaque(false);
        chatPanel.setBounds(130, 100, 190, 280);
        phonePanel.add(chatPanel);

        // 수신 메시지 (왼쪽)
        receiveMsg = new JTextArea();
        receiveMsg.setLineWrap(true);
        receiveMsg.setWrapStyleWord(true);
        receiveMsg.setEditable(false);
        receiveMsg.setOpaque(true);
        receiveMsg.setBackground(Color.WHITE);
        receiveMsg.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        receiveMsg.setBounds(10, 30, 130, 100);
        receiveMsg.setFont(new Font("맑은 고딕", Font.PLAIN, 8));

        chatPanel.add(receiveMsg);
        //
        loadRandomScript();
        
        chatPanel.add(receiveMsg);
        
        /// -------------
        /// 오른쪽 메모 화면
        /// -------------
        
        memoImg = new ImageIcon("src/images/메모장 이미지.png");

        int memoWidth = 286; 
        int memoHeight = 400; 
        
        JPanel memoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(memoImg.getImage(), 20, 30, memoWidth, memoHeight, this);
            }
        };
        memoPanel.setLayout(null);
        mainPanel.add(memoPanel);

        // 메모 내용
        JTextArea memoText = new JTextArea(
                "신뢰할 수 없는 유형의 URL\n"
                + "접속을 주의해야 한다\n"
                + "1. 알 수 없는 최상위 도메인 링크를 조심해야 한다\n."
                + " - 주로 신뢰할 수 있는\n"
                + "도메인\n"
                + ".com, .org\n"
                + " - 신뢰할 수 없는 생소한\n"
                + "도메인\n"
                + ".xyz, .biz, .info .co\n"
                + "2. 유사 도메인 링크를 조심해야 한다.\n"
                + " - google.com -> g00gle.com\n"
                + " - paypal.com -> paypaI.com\n"
        );
        memoText.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        memoText.setOpaque(false);
        memoText.setEditable(false);
        memoText.setBounds(80, 100, 200, 300);
        memoPanel.add(memoText);
        
        ///
        /// 스미싱 확인 버튼
        /// 
        JPanel buttonPanel = new JPanel(null);  
        buttonPanel.setOpaque(false);

        // 임시 이미지
        btnYesImg = new ImageIcon("src/images/스미싱 맞음.png");
        btnNoImg = new ImageIcon("src/images/스미싱 아님.png");
        //btnYesHoverImg = new ImageIcon("src/images/.png");
        //btnNoHoverImg = new ImageIcon("src/images/.png"); 
        
        // YES 버튼
        btnYes = new JLabel(btnYesImg);
        btnYes.setBounds(310, 260, 85, 85);
        btnYes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        memoPanel.add(btnYes);

        // NO 버튼
        btnNo = new JLabel(btnNoImg);
        btnNo.setBounds(310, 360, 85, 85);
        btnNo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        memoPanel.add(btnNo);

        
        /// ---------
        /// 버튼 이벤트 (정답 or 오답)
        /// ---------
        btnYes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent  e) {
            	checkAnswer(true);
            }
        });
        btnNo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent  e) {
            	checkAnswer(false);
            }
        });
    }
	
	/// ------------
	/// 랜덤 시나리오 함수
	/// ------------
	private void loadRandomScript() {
    	currentScript = stage1Script.getRandomScript();
    	receiveMsg.setText(currentScript.getMessage());
	}
	/// ------------
	/// 스미싱 여부 판단 
	/// ------------
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
                loadRandomScript();
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
	
		
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Stage1().setVisible(true);
        });
	}
}

package FischerCode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stage1 extends JFrame{
    private Stage1Script stage1Script = new Stage1Script();
    private Script currentScript;   // 현재 문제
    private JTextArea receiveMsg;    
    
	public Stage1() {
		setTitle("피셔코드 메인 GUI 구상");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,500);
		setLocationRelativeTo(null);
		
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        mainPanel.setBackground(Color.LIGHT_GRAY);
        add(mainPanel);
        
        /// ------------------------
        /// 왼쪽 폰 화면 -> 나중에 이미지로
        /// ------------------------
        JPanel phonePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                int phoneWidth = 260;
                int phoneHeight = 440;

                int x = (panelWidth - phoneWidth) / 2;
                int y = (panelHeight - phoneHeight) / 2;

                g.setColor(Color.BLACK);
                g.fillRoundRect(x, y, phoneWidth, phoneHeight, 40, 40);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRoundRect(x + 20, y + 30, phoneWidth - 40, phoneHeight - 60, 20, 20);
            }
        };
        phonePanel.setLayout(null);
        phonePanel.setBackground(Color.WHITE);
        mainPanel.add(phonePanel);
        
        // 대화 패널
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(null);
        chatPanel.setOpaque(false);
        chatPanel.setBounds(90, 60, 220, 380);
        phonePanel.add(chatPanel);

        // 수신 메시지 (왼쪽)
        receiveMsg = new JTextArea();
        receiveMsg.setLineWrap(true);
        receiveMsg.setWrapStyleWord(true);
        receiveMsg.setEditable(false);
        receiveMsg.setOpaque(true);
        receiveMsg.setBackground(Color.WHITE);
        receiveMsg.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        receiveMsg.setBounds(10, 20, 180, 60);
        receiveMsg.setFont(new Font("맑은 고딕", Font.PLAIN, 8));

        chatPanel.add(receiveMsg);
        //
        loadRandomScript();
        
        // 송신 메시지 (오른쪽)
        JTextArea sendMsg = new JTextArea("내 골반이 멈추지 않은 탓일까T.T");
        sendMsg.setLineWrap(true);
        sendMsg.setWrapStyleWord(true);
        sendMsg.setEditable(false);
        sendMsg.setOpaque(true);
        sendMsg.setBackground(new Color(135, 206, 250));
        sendMsg.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        sendMsg.setBounds(40, 90, 170, 25);
        sendMsg.setFont(new Font("맑은 고딕", Font.PLAIN, 8));

        chatPanel.add(receiveMsg);
        chatPanel.add(sendMsg);
        
        /// -------------
        /// 오른쪽 메모 화면
        /// -------------
        JPanel memoPanel = new JPanel();
        memoPanel.setLayout(new BorderLayout(10, 10));
        memoPanel.setBackground(Color.WHITE);
        memoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(memoPanel);

        // 메모 내용 (체크박스 + 버튼)
        JPanel checklistPanel = new JPanel();
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS));
        checklistPanel.setBackground(new Color(245, 245, 245));
        checklistPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JCheckBox chk1 = new JCheckBox("1. 출처가 불분명한 링크는 클릭하지 않기");
        JCheckBox chk2 = new JCheckBox("2. 앱 다운로드는 공식 앱 스토어만 이용하기");
        chk1.setSelected(true);

        checklistPanel.add(Box.createVerticalStrut(10));
        checklistPanel.add(chk1);
        checklistPanel.add(Box.createVerticalStrut(5));
        checklistPanel.add(chk2);
        checklistPanel.add(Box.createVerticalGlue());

        memoPanel.add(checklistPanel, BorderLayout.CENTER);
        
        // 스미싱 확인 버튼
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnYes = new JButton("스미싱이 맞습니다");
        JButton btnNo = new JButton("스미싱이 아닙니다");

        btnYes.setBackground(Color.DARK_GRAY);
        btnYes.setForeground(Color.WHITE);
        btnNo.setBackground(Color.DARK_GRAY);
        btnNo.setForeground(Color.WHITE);

        buttonPanel.add(btnYes);
        buttonPanel.add(btnNo);

        memoPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        /// ---------
        /// 버튼 이벤트 (정답 or 오답)
        /// ---------
        btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	checkAnswer(true);
            }
        });
        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    "정확한 판단입니",
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

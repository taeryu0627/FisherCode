package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
        setTitle("피셔 코드 시작 GUI 구상");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        mainPanel.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(mainPanel);

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

        // 피셔 코드 제목
        JLabel title = new JLabel("피셔 코드", SwingConstants.CENTER);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        title.setForeground(Color.BLACK);
        title.setBounds(90, 180, 220, 40);
        phonePanel.add(title);

        // 시작 버튼
        JButton startBtn = new JButton("start");
        startBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        startBtn.setBackground(Color.DARK_GRAY);
        startBtn.setForeground(Color.LIGHT_GRAY);
        startBtn.setFocusPainted(false);
        startBtn.setBounds(147, 300, 100, 40);
        phonePanel.add(startBtn);

        /// -------------------
        /// 오른쪽 메모 화면 (빈화면)
        /// -------------------
        JPanel memoPanel = new JPanel();
        memoPanel.setLayout(new BorderLayout(10, 10));
        memoPanel.setBackground(Color.WHITE);
        memoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel checklistPanel = new JPanel();
        checklistPanel.setBackground(new Color(245, 245, 245));
        checklistPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        memoPanel.add(checklistPanel, BorderLayout.CENTER);
        
        mainPanel.add(memoPanel);
        
        // 버튼 이벤트 (게임 시작)
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
               // new Stage1().setVisible(true); // mainGui 열기
            }
        });
	}

}

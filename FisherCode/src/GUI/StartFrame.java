package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartFrame extends JFrame {

    private Image backgroundImage;
    private JLabel startButton;
    private ImageIcon startButtonIcon;
    private ImageIcon startButtonHoverIcon;

    private final int BUTTON_WIDTH = 218;
    private final int BUTTON_HEIGHT = 100;
    private final double BUTTON_SCALE_FACTOR = 1.1;

    public StartFrame() {
        setTitle("Fischer Code 시작");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null); 

        backgroundImage = new ImageIcon("src/images/StartScreen1.png").getImage();
        startButtonIcon = new ImageIcon("src/images/StartBtn.png");
        startButtonHoverIcon = new ImageIcon("src/images/StartBtn_hover.png"); 

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        add(backgroundPanel);

        // 시작 버튼
        if (startButtonIcon.getImage() != null) {
            Image scaledButton = startButtonIcon.getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH);
            startButton = new JLabel(new ImageIcon(scaledButton));
            
            int initialX = (getWidth() - BUTTON_WIDTH) / 2;
            int initialY = 450; 
            startButton.setBounds(initialX, initialY, BUTTON_WIDTH, BUTTON_HEIGHT);
            startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            backgroundPanel.add(startButton);

            startButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose(); 
                    new DialogFrame(); 
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (startButtonHoverIcon.getImage() != null) {
                    	int scaledHoverWidth = (int)(BUTTON_WIDTH * BUTTON_SCALE_FACTOR);
                        int scaledHoverHeight = (int)(BUTTON_HEIGHT * BUTTON_SCALE_FACTOR);

                        int newX = initialX - (scaledHoverWidth - BUTTON_WIDTH) / 2;
                        int newY = initialY - (scaledHoverHeight - BUTTON_HEIGHT) / 2;

                        Image scaledHoverImage = startButtonHoverIcon.getImage().getScaledInstance(scaledHoverWidth, scaledHoverHeight, Image.SCALE_SMOOTH);
                        startButton.setIcon(new ImageIcon(scaledHoverImage));
                        startButton.setBounds(newX, newY, scaledHoverWidth, scaledHoverHeight);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (startButtonIcon.getImage() != null) {
                    	Image scaledNormal = startButtonIcon.getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH);
                        startButton.setIcon(new ImageIcon(scaledNormal));
                        startButton.setBounds(initialX, initialY, BUTTON_WIDTH, BUTTON_HEIGHT);
                    }
                }
            });
        }
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartFrame::new);
    }
}
package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ButtonPanel extends JButton {

    private static final String URL = null;
    private Image img;
    
    
    public ButtonPanel(boolean check) {
    	String resourcePath = new String();
    	if(check) resourcePath = "YesBtn.png";
    	else resourcePath = "NoBtn.png";

        URL url = getClass().getResource("/resources/" + resourcePath);

        // 버튼 크기 크게 키우는 부분 (원하는 값으로 조절)
        Dimension size = new Dimension(80, 80);  
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        // 배경/테두리 안 보이게 (이미지만 보이게)
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        
        img = new ImageIcon(url).getImage();
	}

	@Override
    protected void paintComponent(Graphics g) {
        // 이미지 그리기
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
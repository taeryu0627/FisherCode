package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class NotePanel extends JPanel {

    private Image noteImg;
    

    public NotePanel(String resourcePath) {
        URL url = getClass().getResource(resourcePath);
        noteImg = new ImageIcon(url).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 노트 이미지 배경
        g.drawImage(noteImg, 0, 0, getWidth(), getHeight(), this);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(0x11, 0x7A, 0xB8)); // 약간 파란색 계열
        g2.setFont(new Font("맑은 고딕", Font.BOLD, 22));

        // 제목 (위쪽 왼쪽 부분)
        int w = getWidth();
        int h = getHeight();
        int startX = (int) (w * 0.18);   // 노트 안쪽 여백 기준
        int y = (int) (h * 0.16);

        // 본문 텍스트
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        
        // 매뉴얼 텍스트
        // 이후 해당 부분에 대해서 회의 필요
        String[] lines = {
                "신뢰할 수 없는 유형의 URL 접속을 주의해야 한다",
                "1. 알 수 없는 최상위 도메인",
                "   - 주로 신뢰할 수 있는 도메인",
                "     .com, .org",
                "   - 신뢰할 수 없는 생소한 도메인",
                "     .xyz, .biz, .info, .co",
                "2. 유사 도메인 링크를 조심",
                "   - google.com -> g00gle.com",
                "   - paypal.com -> paypaI.com"
        };

        int lineGap = 34;
        for (String line : lines) {
            g2.drawString(line, startX, y);
            y += lineGap;
        }
    }
}
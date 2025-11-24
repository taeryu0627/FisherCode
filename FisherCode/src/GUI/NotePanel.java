package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class NotePanel extends JPanel {

    private Image noteImg;

    public NotePanel(String resourcePath) {
        setOpaque(false); // 바깥 여백은 배경(mainPanel)이 보이도록
        URL url = getClass().getResource(resourcePath);
        if (url == null) {
            System.err.println("NotePanel 이미지 경로 오류: " + resourcePath);
        } else {
            noteImg = new ImageIcon(url).getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (noteImg == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // 노트 배경 이미지 전체 채우기
        g2.drawImage(noteImg, 0, 0, w, h, this);

        // 본문 텍스트
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));

        int startX = (int) (w * 0.18);   // 노트 안쪽 여백
        int y      = (int) (h * 0.16);   // 첫 줄 높이
        int lineGap = 34;

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

        for (String line : lines) {
            g2.drawString(line, startX, y);
            y += lineGap;
        }

        g2.dispose();
    }
}
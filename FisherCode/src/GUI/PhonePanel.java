package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class PhonePanel extends JPanel {

    private Image phoneImg;

    // 외부에서 받아올 문자 내용들
    private List<String> recvMsgLines = Arrays.asList();
    private String replyText = "링크에 들어가도 되나요?";

    public PhonePanel(String resourcePath) {
        URL url = getClass().getResource(resourcePath);
        phoneImg = new ImageIcon(url).getImage();
    }

    // Stage1Script에서 호출할 메서드
    public void setRecvMessage(String msg) {
        // \n 기준으로 줄 나누기
        this.recvMsgLines = Arrays.asList(msg.split("\n"));
        repaint();
    }

    public void setReplyMessage(String reply) {
        this.replyText = reply;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(phoneImg, 0, 0, getWidth(), getHeight(), this);
        
        int w = getWidth() - 30;
        int h = getHeight();

        int screenX = (int) (w * 0.17);
        int screenY = (int) (h * 0.09);
        int screenW = (int) (w * 0.72);
        int screenH = (int) (h * 0.75);
        int headerH = (int) (screenH * 0.14);
        
        int msgAreaX = screenX + 10;
        int msgAreaY = screenY + headerH + 20;
        int msgAreaW = screenW - 20;

        Graphics2D g2 = (Graphics2D) g.create(); // 지역변수 g2 생성
        design(g2, screenX, screenY, screenW, screenH, msgAreaX, msgAreaY, msgAreaW, headerH);   // g2 전달
        g2.dispose();
    }
    
    protected void design(Graphics2D g2,
            int screenX, int screenY,
            int screenW, int screenH,
            int msgAreaX, int msgAreaY,
            int msgAreaW, int headerH) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        topDesign(g2, screenX, screenY, screenW, headerH);
        bottomDesign(g2, msgAreaX, msgAreaY, msgAreaW);
    }
    
    // 화면 상단 디자인
    protected void topDesign(Graphics2D g2,
            int screenX, int screenY,
            int screenW, int headerH) {

        String title = "발신자 번호";
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleX = screenX + (screenW - fmTitle.stringWidth(title)) / 2;
        int titleY = screenY + fmTitle.getAscent() + 5;
        g2.drawString(title, titleX, titleY);

        String number = "02-123-456";
        g2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        FontMetrics fmNum = g2.getFontMetrics();
        int numX = screenX + (screenW - fmNum.stringWidth(number)) / 2;
        int numY = titleY + fmNum.getHeight();
        g2.drawString(number, numX, numY);
    }

    // 화면 하단 디자인
    protected void bottomDesign(Graphics2D g2,
            int msgAreaX, int msgAreaY,
            int msgAreaW) {

        g2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        FontMetrics fmRecv = g2.getFontMetrics();

        int paddingX = 18;
        int paddingY = 16;
        int lineGap = 4;

        int textBlockHeight = 0;
        for (String line : recvMsgLines) {
            textBlockHeight += fmRecv.getHeight() + lineGap;
        }
        textBlockHeight -= lineGap;

        int bubble1W = (int) (msgAreaW * 0.85);
        int bubble1H = textBlockHeight + paddingY * 2;
        int bubble1X = msgAreaX + 10;
        int bubble1Y = msgAreaY;
        int arc = 24;

        g2.setColor(new Color(0, 0, 0, 35));
        g2.fillRoundRect(bubble1X + 2, bubble1Y + 3, bubble1W, bubble1H, arc, arc);

        g2.setColor(new Color(245, 245, 247));
        g2.fillRoundRect(bubble1X, bubble1Y, bubble1W, bubble1H, arc, arc);
        g2.setColor(new Color(220, 220, 220));
        g2.drawRoundRect(bubble1X, bubble1Y, bubble1W, bubble1H, arc, arc);

        g2.setColor(Color.BLACK);
        int textX = bubble1X + paddingX;
        int textY = bubble1Y + paddingY + fmRecv.getAscent();
        for (String line : recvMsgLines) {
            g2.drawString(line, textX, textY);
            textY += fmRecv.getHeight() + lineGap;
        }

        // 내 말풍선
        g2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        FontMetrics fmReply = g2.getFontMetrics();

        int replyPaddingX = 18;
        int replyPaddingY = 10;
        int replyTextW = fmReply.stringWidth(replyText);
        int bubble2W = replyTextW + replyPaddingX * 2;
        int bubble2H = fmReply.getHeight() + replyPaddingY * 2;

        int bubble2X = msgAreaX + msgAreaW - bubble2W - 10;
        int bubble2Y = bubble1Y + bubble1H + 20;

        g2.setColor(new Color(0, 0, 0, 35));
        g2.fillRoundRect(bubble2X + 2, bubble2Y + 3, bubble2W, bubble2H, 20, 20);

        g2.setColor(new Color(80, 140, 255));
        g2.fillRoundRect(bubble2X, bubble2Y, bubble2W, bubble2H, 20, 20);

        g2.setColor(Color.WHITE);
        int replyTextX = bubble2X + replyPaddingX;
        int replyTextY = bubble2Y + replyPaddingY + fmReply.getAscent();
        g2.drawString(replyText, replyTextX, replyTextY);
    }
}

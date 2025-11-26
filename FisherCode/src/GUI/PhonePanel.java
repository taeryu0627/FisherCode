package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import Models.FontUlleungdoM;

public class PhonePanel extends JPanel {

    private Image phoneImg;
    
    private List<String> recvMsgLines = List.of(""); // 안전한 기본값
    private String replyText = "링크에 들어가도 되나요?";

    public PhonePanel(String resourcePath) {

        setOpaque(false); // 배경 패널 이미지가 자연스럽게 보이도록 설정

        URL url = getClass().getResource(resourcePath);
        if (url == null) {
            System.err.println("PhonePanel 이미지 로드 실패: " + resourcePath);
        } else {
            phoneImg = new ImageIcon(url).getImage();
        }
    }

    public void setRecvMessage(String msg) {
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

        if (phoneImg == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 배경(폰 테두리) 이미지 먼저 그림
        g2.drawImage(phoneImg, 0, 0, getWidth(), getHeight(), this);

        // 화면 좌표 계산
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

        // 디자인 함수 호출
        topDesign(g2, screenX, screenY, screenW, headerH);
        bottomDesign(g2, msgAreaX, msgAreaY, msgAreaW);

        g2.dispose();
    }

    private void topDesign(Graphics2D g2, int screenX, int screenY, int screenW, int headerH) {

        String title = "발신자 번호";
        g2.setColor(Color.WHITE);
        g2.setFont(FontUlleungdoM.getCustomFont(32f).deriveFont(Font.BOLD));
        FontMetrics fmTitle = g2.getFontMetrics();

        int titleX = screenX + (screenW - fmTitle.stringWidth(title)) / 2;
        int titleY = screenY + fmTitle.getAscent() + 5;
        g2.drawString(title, titleX, titleY);

        String number = "02-123-456";
        g2.setFont(FontUlleungdoM.getCustomFont(18f));
        FontMetrics fmNum = g2.getFontMetrics();

        int numX = screenX + (screenW - fmNum.stringWidth(number)) / 2;
        int numY = titleY + fmNum.getHeight();
        g2.drawString(number, numX, numY);
    }

    private void bottomDesign(Graphics2D g2, int msgAreaX, int msgAreaY, int msgAreaW) {

        // 상대방 메시지 텍스트
    	g2.setFont(FontUlleungdoM.getCustomFont(15f));
        FontMetrics fm = g2.getFontMetrics();

        int paddingX = 18;
        int paddingY = 16;
        int lineGap = 4;

        int textBlockHeight = Math.max( fm.getHeight(), 0 );
        textBlockHeight = 0;
        for (String line : recvMsgLines) {
            textBlockHeight += fm.getHeight() + lineGap;
        }
        textBlockHeight -= lineGap;

        int bubbleW = (int) (msgAreaW * 0.85);
        int bubbleH = textBlockHeight + paddingY * 2;
        int bubbleX = msgAreaX + 10;
        int bubbleY = msgAreaY;

        int arc = 24;

        // 말풍선 그림자
        g2.setColor(new Color(0, 0, 0, 35));
        g2.fillRoundRect(bubbleX + 2, bubbleY + 3, bubbleW, bubbleH, arc, arc);

        // 말풍선 본체
        g2.setColor(new Color(245, 245, 247));
        g2.fillRoundRect(bubbleX, bubbleY, bubbleW, bubbleH, arc, arc);
        g2.setColor(new Color(220, 220, 220));
        g2.drawRoundRect(bubbleX, bubbleY, bubbleW, bubbleH, arc, arc);

        // 텍스트
        g2.setColor(Color.BLACK);
        int textX = bubbleX + paddingX;
        int textY = bubbleY + paddingY + fm.getAscent();

        for (String line : recvMsgLines) {
            g2.drawString(line, textX, textY);
            textY += fm.getHeight() + lineGap;
        }

        // 내 답장 말풍선
        g2.setFont(FontUlleungdoM.getCustomFont(15f));
        FontMetrics fm2 = g2.getFontMetrics();

        int replyTextW = fm2.stringWidth(replyText);
        int replyBubbleW = replyTextW + 36;
        int replyBubbleH = fm2.getHeight() + 20;

        int replyX = msgAreaX + msgAreaW - replyBubbleW - 10;
        int replyY = bubbleY + bubbleH + 20;

        g2.setColor(new Color(0, 0, 0, 35));
        g2.fillRoundRect(replyX + 2, replyY + 3, replyBubbleW, replyBubbleH, 20, 20);

        g2.setColor(new Color(80, 140, 255));
        g2.fillRoundRect(replyX, replyY, replyBubbleW, replyBubbleH, 20, 20);

        g2.setColor(Color.WHITE);
        int replyTextX = replyX + 18;
        int replyTextY = replyY + 10 + fm2.getAscent();
        g2.drawString(replyText, replyTextX, replyTextY);
    }
}

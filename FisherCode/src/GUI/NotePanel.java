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
                "   - paypal.com -> paypaI.com",
                "공공 기관 사칭을 조심하세요",
                "1. 정부 기관에서 통보하는 공문, 고지서는 등기 우편을 통해 통지합니다",
                "	-공식 도메인 확인:",
                " 	-go.kr로 끝나는 도메인",
                "2. 특정 url 접속 유도",
                "	-이벤트 당첨을 확인하는 주소",
                " 	-세금 고지서, 벌금 고지서 등으로 특정 url에 접속을 유도",
                "송금을 강요 받으면 일단 침착하고 송금을 미루길 바랍니다",
                "1.직접적인 금전 유도",
                "	-보증금, 수수료 등을 빌미로 송금을 요구",
                "	-지인인척 하는 미상의 연락처로부터 별도의 계좌에 금전 요구",
                
        };

        int lineGap = 34;
        for (String line : lines) {
            g2.drawString(line, startX, y);
            y += lineGap;
        }
    }
}
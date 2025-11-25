package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class NotePanel extends JPanel {

    /* ============================
     *  배경 이미지 & 텍스트 데이터
     * ============================ */

    private Image noteImg;

    // 기본 안내문
    private final String[] defaultLines = {
            "신뢰할 수 없는 유형의 URL 접속을 주의해야 한다",
            "",
            "1. 알 수 없는 최상위 도메인",
            "   - .com, .org 등 신뢰 도메인",
            "   - .xyz, .biz 등 생소 도메인",
            "",
            "2. 유사 도메인 링크 조심",
            "   - google.com -> g00gle.com",
            "   - paypal.com -> paypaI.com"
    };

    // 도메인 관련 안내문
    private final String[] domainLines = {
            "URL 클릭 전 반드시 발신자를 다시 확인하세요.",
            "",
            "1. 은행/관공서는 공식 도메인을 사용",
            "2. 주소창을 꼭 확인 (.go.kr / .bank 등)",
            "3. '지금 바로 클릭'은 위험 신호",
            "4. 개인정보 요청은 대부분 스미싱"
    };

    // 패턴 관련 안내문
    private final String[] patternLines = {
            "자주 쓰이는 스미싱 패턴",
            "",
            "- 택배 배송 조회 링크",
            "- 본인 인증 유도",
            "- 과태료/벌금 미납 안내",
            "- 재난 지원금 / 지원금 신청 링크",
            "",
            "조금이라도 이상하면 직접 전화로 확인하세요!"
    };

    // 실제로 화면에 그릴 현재 문장들
    private String[] currentLines = defaultLines;


    /* ============================
     *  생성자
     * ============================ */
    public NotePanel(String resourcePath) {
        setOpaque(false);
        setLayout(new BorderLayout());

        // 배경 이미지 로드
        URL url = getClass().getResource(resourcePath);
        if (url != null) {
            noteImg = new ImageIcon(url).getImage();
        } else {
            System.err.println("NotePanel 이미지 로드 실패: " + resourcePath);
        }

        // 상단 탭 버튼 바 추가
        add(createHorizontalTabBar(), BorderLayout.NORTH);
    }


    /* ============================
     *  상단 가로 버튼 바 생성
     * ============================ */
    private JComponent createHorizontalTabBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panel.setOpaque(false);

        JButton btnDefault = createTabButton("기본",   new Color(255, 80, 80));   // 빨강
        JButton btnDomain  = createTabButton("도메인", new Color(80, 130, 255));  // 파랑
        JButton btnPattern = createTabButton("패턴",   new Color(255, 210, 60));  // 노랑

        // 텍스트 세트 교체
        btnDefault.addActionListener(e -> {
            currentLines = defaultLines;
            repaint();
        });

        btnDomain.addActionListener(e -> {
            currentLines = domainLines;
            repaint();
        });

        btnPattern.addActionListener(e -> {
            currentLines = patternLines;
            repaint();
        });

        panel.add(btnDefault);
        panel.add(btnDomain);
        panel.add(btnPattern);

        return panel;
    }

    // 탭 버튼 공통 스타일
    private JButton createTabButton(String text, Color bgColor) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setContentAreaFilled(true);
        b.setOpaque(true);
        b.setBorderPainted(false);

        b.setBackground(bgColor);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        b.setPreferredSize(new Dimension(100, 40));
        b.setMaximumSize(new Dimension(120, 45));

        b.setRolloverEnabled(false);   // ★ Hover 효과 제거
        b.putClientProperty("JButton.buttonType", "square"); 
        return b;
    }


    /* ============================
     *  그림 그리기
     * ============================ */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (noteImg == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // 노트 배경 이미지
        g2.drawImage(noteImg, 0, 0, w, h, this);

        // 글자 시작 위치 (노트 안쪽 기준)
        int startX = (int) (w * 0.18);
        int y      = (int) (h * 0.20);   // 위에 버튼 바가 있으니 살짝 더 내려줌
        int lineGap = 32;

        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));

        if (currentLines != null) {
            for (String line : currentLines) {
                g2.drawString(line, startX, y);
                y += lineGap;
            }
        }

        g2.dispose();
    }

    /* ============================
     *  외부에서 강제로 내용 바꾸고 싶을 때
     * ============================ */
    public void setLines(String[] lines) {
        this.currentLines = lines;
        repaint();
    }
}

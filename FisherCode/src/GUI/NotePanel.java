package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class NotePanel extends JPanel {

    private Image noteImg;

    // 노트 내용
    private String[] lines = {
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

    // 오른쪽 탭 버튼 패널
    private JPanel tabPanel;

    public NotePanel(String resourcePath) {
        setOpaque(false);
        setLayout(null);   // ★ 절대 좌표 배치로 겹치게 놓기

        URL url = getClass().getResource(resourcePath);
        if (url != null) {
            noteImg = new ImageIcon(url).getImage();
        } else {
            System.err.println("NotePanel 이미지 로드 실패: " + resourcePath);
        }

        createTabButtons();
    }

    // 외부(MainFrame)에서 내용 바꾸고 싶을 때도 사용할 수 있도록 public 유지
    public void setLines(String[] lines) {
        this.lines = lines;
        repaint();
    }

    // 오른쪽 색 탭 자리에 들어갈 버튼들 생성
    private void createTabButtons() {
        tabPanel = new JPanel();
        tabPanel.setOpaque(false);
        tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));

        JButton btn1 = createTabButton("기본",  new Color(220, 50, 50));   // 빨강
        JButton btn2 = createTabButton("도메인", new Color(60, 120, 255)); // 파랑
        JButton btn3 = createTabButton("패턴",  new Color(240, 200, 40));  // 노랑

        tabPanel.add(btn1);
        tabPanel.add(Box.createVerticalStrut(40));
        tabPanel.add(btn2);
        tabPanel.add(Box.createVerticalStrut(40));
        tabPanel.add(btn3);

        // 내용 변경 액션
        btn1.addActionListener(e -> setLines(new String[]{
                "신뢰할 수 없는 유형의 URL 접속을 주의해야 한다",
                "",
                "1. 알 수 없는 최상위 도메인",
                "   - .com, .org 등 신뢰 도메인",
                "   - .xyz, .biz 등 생소 도메인",
                "",
                "2. 유사 도메인 링크 조심",
                "   - google.com -> g00gle.com",
                "   - paypal.com -> paypaI.com"
        }));

        btn2.addActionListener(e -> setLines(new String[]{
                "URL 클릭 전 반드시 발신자를 다시 확인하세요.",
                "",
                "1. 은행/관공서는 공식 도메인을 사용",
                "2. '지금 바로 클릭'은 위험 신호",
                "3. 개인정보 요청은 99% 스미싱"
        }));

        btn3.addActionListener(e -> setLines(new String[]{
                "자주 쓰이는 스미싱 패턴",
                "",
                "- 택배 배송 조회 링크",
                "- 본인 인증 유도",
                "- 과태료/벌금 미납 안내",
                "- 재난 지원금 링크",
                "",
                "의심되면 직접 전화 확인!"
        }));

        add(tabPanel); // ★ NotePanel 내부에 추가
    }

    // 탭 버튼 공통 스타일
    private JButton createTabButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(true);
        b.setOpaque(true);

        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("맑은 고딕", Font.BOLD, 12));

        b.setPreferredSize(new Dimension(70, 40));
        b.setMaximumSize(new Dimension(70, 40));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        return b;
    }

    // 레이아웃 시 버튼 위치를 노트 오른쪽으로 겹치게 배치
    @Override
    public void doLayout() {
        super.doLayout();

        if (tabPanel != null) {
            int w = getWidth();
            int h = getHeight();

            int tabWidth  = 70;
            int tabHeight = 40;
            int gap       = 40;

            // ★ 탭 패널 전체 영역(노트 오른쪽에 살짝 겹치게)
            int x = w - (int)(w * 0.06) - tabWidth;     // 오른쪽에서 약간 안쪽
            int y = (int)(h * 0.20);                    // 위에서 조금 내려오게

            int totalH = tabHeight * 3 + gap * 2;
            tabPanel.setBounds(x, y, tabWidth, totalH);
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

        // 노트 이미지 배경
        g2.drawImage(noteImg, 0, 0, w, h, this);

        // 글자 시작 위치
        int startX = (int) (w * 0.18);
        int y      = (int) (h * 0.16);
        int lineGap = 34;

        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));

        if (lines != null) {
            for (String line : lines) {
                g2.drawString(line, startX, y);
                y += lineGap;
            }
        }

        g2.dispose();
    }
}

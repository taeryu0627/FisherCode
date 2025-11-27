package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import Models.FontUlleungdoB;

public class NotePanel extends JPanel {

    // 배경 이미지 & 텍스트 데이터
    private Image noteImg;
    private float currentFontSize = 18f; 
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
    
 // Stage 2 : 정부/공공기관 사칭
    private final String[] governmentScamLines = {
        "정부/공공기관 사칭 스미싱 분석 및 판단 지침",
        "",
        "1. 비공식 발신 정보 확인:",
        "   - 메시지에 '국제발신' or 'Web발신' 표기 있는지 확인",
        "   - 기관명을 명확히 표기하지 않고 ",
        "       [세금], [수사중] 등 키워드만 쓰는 경우 의심",
        "",
        "2. 불필요한 행동 요구 집중 분석:",
        "   - 정부기관은 문자로 앱 설치(APK 파일)를 유도하지 않음",
        "   - 계좌 비밀번호, 카드 정보 등 금융 정보를 ",
        "       문자로 입력 요청하는지 확인",
        "",
        "3. 불안감 조성 패턴:",
        "   - '지금 즉시', '기간 마감 임박', '수사 대상' 등 ",
        "       긴급한 조치를 요구하는지 분석"
    };

    // Stage 3: 지인/가족 사칭
    private final String[] familyScamLines = {
        "지인/가족 사칭 스미싱 (메신저 피싱) 분석 지침",
        "",
        "1. 발신 번호 및 상황 설정의 비정상성:",
        "   - 낯선 번호를 사용하며 '휴대폰 고장', '친구가 대신 보냄'",
        "       등 전화 통화 불가 사유 제시",
        "",
        "2. 요청 내용의 긴급성 및 구체성 분석:",
        "   - '급하게', '당장' 등의 표현으로 ",
        "       금전 이체(용돈, 대리 결제)를 요청하는지 확인",
        "   - 수수료를 선입금하면 큰 금액의 경품을",
        "       받을 수 있다는 내용인지 확인",
        "",
        "3. 개인 정보 요구 수준 검토:",
        "   - 신분증 사본, 계좌 비밀번호, 공인인증서 등",
        "       타인이 알면 안 되는 민감한 정보를 요구하는지 분석",
        ""
    };

    // 현재 표시할 문장들
    private String[] currentLines = defaultLines;

    // 🔹 탭 버튼들을 필드로 관리
    private JButton btnDefault;
    private JButton btnDomain;
    private JButton btnPattern;

    // 생성자
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

        // 🔹 초기 스테이지 1 기준으로 버튼 보이기 설정
        updateStage(1);
    }

    // 상단 가로 버튼 바 생성
    private JComponent createHorizontalTabBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panel.setOpaque(false);

        btnDefault = createTabButton("도메인",   new Color(255, 80, 80));   // 빨강
        btnDomain  = createTabButton("정부사칭", new Color(80, 130, 255));  // 파랑
        btnPattern = createTabButton("지인사칭",   new Color(255, 210, 60));  // 노랑

        // 텍스트 세트 교체
        btnDefault.addActionListener(e -> {
            currentLines = defaultLines;
            currentFontSize = 18f;   // 폰트 크기 변경
            repaint();
        });

        btnDomain.addActionListener(e -> {
            currentLines = governmentScamLines;
            currentFontSize = 16f;   // 폰트 크기 변경
            repaint();
        });

        btnPattern.addActionListener(e -> {
            currentLines = familyScamLines;
            currentFontSize = 16f;   // 폰트 크기 변경
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
        b.setFont(FontUlleungdoB.getCustomFont(14f).deriveFont(Font.BOLD));
        b.setPreferredSize(new Dimension(100, 40));
        b.setMaximumSize(new Dimension(120, 45));

        b.setRolloverEnabled(false);   // Hover 효과 제거
        b.putClientProperty("JButton.buttonType", "square");
        return b;
    }

    // 🔹 스테이지에 따라 버튼 보이기/숨기기
    public void updateStage(int stage) {
        // stage 1 : 도메인
        // stage 2 : 정부사칭 + 도메인
        // stage 3 이상 : 정부사칭 + 도메인 + 지인사칭

        if (btnDefault != null) btnDefault.setVisible(true);
        if (btnDomain  != null) btnDomain.setVisible(stage >= 2);
        if (btnPattern != null) btnPattern.setVisible(stage >= 3);

        revalidate();
        repaint();
    }

    // 그림 그리기
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

        // 글자 위치
        int startX = (int) (w * 0.18);
        int y      = (int) (h * 0.16);
        int lineGap = 34;

        g2.setColor(Color.DARK_GRAY);
        g2.setFont(FontUlleungdoB.getCustomFont(currentFontSize));  // ★ 현재 폰트 크기 적용

        if (currentLines != null) {
            for (String line : currentLines) {
                g2.drawString(line, startX, y);
                y += lineGap;
            }
        }

        g2.dispose();
    }

    // 외부에서 강제로 내용 바꾸고 싶을 때
    public void setLines(String[] lines) {
        this.currentLines = lines;
        repaint();
    }
}
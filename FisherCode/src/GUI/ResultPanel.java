package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import Models.FontUlleungdoM;

public class ResultPanel extends JPanel {

    //  Fields
    private final int correctCount;   // 맞춘 문제 수
    private final int wrongCount;     // 틀린 문제 수
    private final char grade;         // A, B, C, F
    private final Runnable onRestart; // 다시 시작 콜백(MainFrame::restartGame)
    
    private Image bgImg;


    //  Constructor

    public ResultPanel(int correctCount, int wrongCount, Runnable onRestart) {
        this.correctCount = correctCount;
        this.wrongCount = wrongCount;
        this.onRestart = onRestart;
        this.grade = calcGrade(correctCount, wrongCount);

        
        setLayout(new BorderLayout());
        // 배경 이미지 로드
        URL bgUrl = getClass().getResource("/resources/background/Dialog1.png");
        if (bgUrl != null) {
            bgImg = new ImageIcon(bgUrl).getImage();
        } else {
            System.err.println("배경 이미지 로드 실패: /resources/background/ResultBg.png");
        }

        setLayout(new BorderLayout(0, 20));

        JPanel center = new JPanel(new GridLayout(1, 2, 30, 0));
        center.setOpaque(false); // 배경이 비치도록
        center.add(createLeftPanel());
        center.add(createRightPanel());

        add(center, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImg != null) {
            g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
        }
    }


    //  등급 계산 로직
    private char calcGrade(int correct, int wrong) {
        if (correct >= 15) return 'A';
        else if (correct >= 12) return 'B';
        else if (correct >= 8) return 'C';
        else return 'F';
    }

    //  Left : exResult?.png
    private JComponent createLeftPanel() {
        // exResultA.png / exResultB.png / exResultC.png / exResultF.png
        String path = "/resources/result/exResult" + grade + ".png";
        return new SingleImagePanel(path);
    }


    //  Right : paperImg + Result?.png + 텍스트
    private JComponent createRightPanel() {
        String paperPath = "/resources/result/paperImg.png";
        String scorePath = "/resources/result/Result" + grade + ".png";
        return new ScorePaperPanel(paperPath, scorePath, grade, correctCount, wrongCount);
    }

    //  Bottom : 다시 시작 버튼

    private JComponent createBottomPanel() {
        // 다시 시작 버튼
        JButton restartBtn = new JButton("다시 시작");
        restartBtn.setFont(FontUlleungdoM.getCustomFont(18f).deriveFont(Font.BOLD));
        restartBtn.addActionListener(e -> {
            if (onRestart != null) {
                onRestart.run();
            }
        });

        // 처음으로 버튼 (StartFrame으로 돌아가기)
        JButton toStartBtn = new JButton("처음으로");
        toStartBtn.setFont(FontUlleungdoM.getCustomFont(18f).deriveFont(Font.BOLD));
        toStartBtn.addActionListener(e -> {
            // 이 패널을 감싸고 있는 최상위 윈도우(JFrame) 얻기
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();      // 현재 창 닫기 (MainFrame)
            }
            new StartFrame();          // 시작 화면 다시 열기
        });

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(restartBtn);
        panel.add(Box.createHorizontalStrut(20)); // 버튼 사이 간격
        panel.add(toStartBtn);
        return panel;
    }


    //  내부 클래스들

    
    private static class SingleImagePanel extends JPanel {
        private final Image img;

        public SingleImagePanel(String path) {
            setOpaque(false);
            URL url = ResultPanel.class.getResource(path);
            if (url != null) {
                img = new ImageIcon(url).getImage();
            } else {
                System.err.println("이미지 로드 실패: " + path);
                img = null;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }


    private static class ScorePaperPanel extends JPanel {

        private final Image paperImg;
        private final Image scoreImg;
        private final char grade;
        private final int correct;
        private final int wrong;

        public ScorePaperPanel(String paperPath,
                               String scorePath,
                               char grade,
                               int correct,
                               int wrong) {
            setOpaque(false);
            this.grade = grade;
            this.correct = correct;
            this.wrong = wrong;

            URL paperUrl = ResultPanel.class.getResource(paperPath);
            URL scoreUrl = ResultPanel.class.getResource(scorePath);

            if (paperUrl != null) {
                paperImg = new ImageIcon(paperUrl).getImage();
            } else {
                System.err.println("이미지 로드 실패: " + paperPath);
                paperImg = null;
            }

            if (scoreUrl != null) {
                scoreImg = new ImageIcon(scoreUrl).getImage();
            } else {
                System.err.println("이미지 로드 실패: " + scorePath);
                scoreImg = null;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // 1) 종이 배경
            if (paperImg != null) {
                g2.drawImage(paperImg, 0, 0, w, h, this);
            }

            int marginX = (int) (w * 0.12);
            int topY    = (int) (h * 0.18);

            // 2) 제목: 직무평가
            g2.setColor(new Color(0x222222));
            g2.setFont(new Font("맑은 고딕", Font.BOLD, 40));
            String title = "직무평가";
            FontMetrics fmTitle = g2.getFontMetrics();
            int titleX = (w - fmTitle.stringWidth(title)) / 2;
            int titleY = topY;
            g2.drawString(title, titleX, titleY);

            // 3) 등급 이미지(score?.png)를 제목 아래에 가운데 배치
            if (scoreImg != null) {
                int targetW = (int) (w * 0.35);  // 종이 폭의 35% 정도로 축소
                int origW   = scoreImg.getWidth(null);
                int origH   = scoreImg.getHeight(null);
                int targetH = (int) (targetW * (origH / (double) origW));

                int x = (w - targetW) / 2;
                int y = titleY + 30;

                g2.drawImage(scoreImg, x, y, targetW, targetH, this);

                topY = y + targetH + 40; // 아래 텍스트 시작점
            } else {
                // 이미지 없을 때 fallback: 글자로 등급 표시
            	g2.setFont(FontUlleungdoM.getCustomFont(110f).deriveFont(Font.BOLD));
                String gradeStr = String.valueOf(grade);
                FontMetrics fmGrade = g2.getFontMetrics();
                int gradeX = (w - fmGrade.stringWidth(gradeStr)) / 2;
                int gradeY = titleY + fmGrade.getAscent() + 40;
                g2.setColor(new Color(0xD32F2F));
                g2.drawString(gradeStr, gradeX, gradeY);
                topY = gradeY + 40;
            }

            // 4) 맞춘/틀린 문제 수
            g2.setFont(FontUlleungdoM.getCustomFont(22f));
            g2.setColor(new Color(0x333333));

            int infoY = topY;
            String correctText = "맞춘 문제 : " + correct + "개";
            String wrongText   = "틀린 문제 : " + wrong + "개";

            g2.drawString(correctText, marginX, infoY);
            g2.drawString(wrongText,   marginX, infoY + 35);

            g2.dispose();
        }
    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Result Test");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(1300, 700);
            f.setLocationRelativeTo(null);
            f.setContentPane(new ResultPanel(7, 2, () -> {}));
            f.setVisible(true);
        });
    }
*/
}

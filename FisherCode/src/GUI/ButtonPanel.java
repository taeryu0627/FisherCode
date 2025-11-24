package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ButtonPanel extends JButton {

    // 버튼에 사용할 논리 크기 (원하는 대로 조절)
    private static final int BTN_W = 110;
    private static final int BTN_H = 110;

    public ButtonPanel(boolean isYes) {

        String baseName = isYes ? "YesBtn" : "NoBtn";   // 정상 / 스미싱 구분

        // 기본 아이콘 & hover 아이콘
        ImageIcon normalIcon = loadScaledIcon("/resources/" + baseName + ".png");
        ImageIcon hoverIcon  = loadScaledIcon("/resources/" + baseName + "_hover.png");

        // 기본 아이콘
        if (normalIcon != null) {
            setIcon(normalIcon);
        }

        // Hover 아이콘 (없으면 기본 아이콘 그대로 사용)
        if (hoverIcon != null) {
            setRolloverIcon(hoverIcon);
        } else if (normalIcon != null) {
            setRolloverIcon(normalIcon);
        }

        // 버튼 크기 설정 (아이콘 크기랑 맞춰주기)
        Dimension size = new Dimension(BTN_W, BTN_H);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        // 버튼 기본 UI 제거 → 도장 이미지만 보이게
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    // 이미지를 BTN_W×BTN_H 로 부드럽게 리사이즈해서 아이콘으로 반환
    private ImageIcon loadScaledIcon(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.err.println("버튼 이미지 찾을 수 없음: " + path);
            return null;
        }

        ImageIcon origin = new ImageIcon(url);
        Image scaled = origin.getImage().getScaledInstance(
                BTN_W, BTN_H, Image.SCALE_SMOOTH
        );
        return new ImageIcon(scaled);
    }
}

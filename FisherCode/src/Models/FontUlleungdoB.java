package Models;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontUlleungdoB {
    
    private static Font customFont = null;
    
    private static final String FONT_PATH = "src/resources/fonts/HY울릉도B.ttf";

    public static Font getCustomFont(float size) {
        if (customFont == null) {
            try {
                // 폰트 파일에서 폰트 로드
                customFont = Font.createFont(
                    Font.TRUETYPE_FONT, 
                    new File(FONT_PATH)
                );
            } catch (IOException | FontFormatException e) {
                // 오류 발생 시 맑은 고딕 사용
                System.err.println("폰트 로드 실패 기본 폰트를 사용함");
                e.printStackTrace();
                customFont = new Font("맑은 고딕", Font.PLAIN, (int) size); 
            }
        }
        
        // 폰트를 지정된 크기로 출력
        return customFont.deriveFont(size);
    }
}
package Models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ScriptLoader {

    /**
     * resources 안의 파일에서 Script 리스트를 읽어온다.
     * @param resourcePath 예: "/scripts.csv"
     */
    public static List<Script> loadFromResource(String resourcePath) throws IOException {
        List<Script> scripts = new ArrayList<>();

        InputStream in = ScriptLoader.class.getResourceAsStream(resourcePath);
        if (in == null) {
            throw new IOException("리소스를 찾을 수 없습니다: " + resourcePath);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                // 빈 줄 / 주석은 패스
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                // scamLevel;message;
                String[] parts = line.split(";", 3);
                if (parts.length < 3) {
                    System.err.println("형식이 올바르지 않은 줄: " + line);
                    continue;
                }

//                String id = parts[0].trim();
//                String category = parts[1].trim();
//                boolean isScam = Boolean.parseBoolean(parts[].trim());
                int scamLevel = Integer.parseInt(parts[0].trim());
                boolean isScam = Boolean.parseBoolean(parts[1].trim());
                String message = parts[2]
                        .replace("\\n", "\n")   // 파일 안의 \n → 실제 줄바꿈
                        .trim();

                scripts.add(new Script(scamLevel, isScam, message));
            }
        }

        return scripts;
    }
}
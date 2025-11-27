package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainModel {
    private Random random = new Random();
    private List<Script> scripts = new ArrayList<>();

    public Script getRandomScript() {
        if (scripts.isEmpty()) return null;  // 리스트 비어 있을 때 방어 코드
        return scripts.get(random.nextInt(scripts.size()));
    }

    // scamLevel 기준으로 랜덤 스크립트 가져오기
    public Script getRandomScriptByLevel(int level) {
        List<Script> filtered = new ArrayList<>();

        for (Script s : scripts) {
            if (s.getScamLevel() == level) {   // getCategory() 대신 getScamLevel()
                filtered.add(s);
            }
        }

        if (filtered.isEmpty()) {
            return getRandomScript();          // 해당 레벨이 없으면 전체에서 랜덤
        }
        return filtered.get(random.nextInt(filtered.size()));
    }
}
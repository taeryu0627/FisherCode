package Models;

import java.util.*;

public class StageManager {

    private final List<Script> scripts;
    private int currentIndex = 0;

    public StageManager(List<Script> scripts) {
        if (scripts == null || scripts.isEmpty()) {
            throw new IllegalArgumentException("StageManager에 빈 scripts 리스트가 전달되었습니다.");
        }
        this.scripts = scripts;
    }
    
    public Script getRandom() {
        Random r = new Random();
        currentIndex = r.nextInt(scripts.size());
        return getCurrent();
    }

    /** 현재 스테이지 Script 반환 */
    public Script getCurrent() {
        return scripts.get(currentIndex);
    }

    /** 다음 스테이지로 이동 (마지막이면 그대로 유지) */
    public Script next() {
        if (currentIndex < scripts.size() - 1) {
            currentIndex++;
        }
        return getCurrent();
    }

    /** 이전 스테이지로 이동 (처음이면 그대로 유지) */
    public Script prev() {
        if (currentIndex > 0) {
            currentIndex--;
        }
        return getCurrent();
    }

    /** 처음 스테이지로 리셋 */
    public Script reset() {
        currentIndex = 0;
        return getCurrent();
    }

    public boolean hasNext() {
        return currentIndex < scripts.size() - 1;
    }

    public boolean hasPrev() {
        return currentIndex > 0;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getStageCount() {
        return scripts.size();
    }
}

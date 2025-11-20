package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Stage1Script {

    // 원본 문제 리스트
    private final List<Script> scripts = new ArrayList<>();
    // 셔플된 문제 풀
    private final List<Script> pool = new ArrayList<>();

    private final Random random = new Random();

    public Stage1Script() {

        // 문제 1 - 유심 재고
        scripts.add(new Script(
                "stage1",
                "유심재고",
                true,
                "[Web발신]\n"
              + "[SKT 유심 재고 도착알림]\n"
              + "고객님이 예약하신 USIM이 대리점에 입고\n"
              + "되었습니다. 방문 전 본인확일을 위해\n"
              + "아래 URL을 눌러 본인 확인을 해주세요\n"
              + "http://kb-check.xyz"
        ));

        // 문제 2 - 성남시청 코로나
        scripts.add(new Script(
                "stage2",
                "코로나",
                false,
                "[성남시청]1월 30일 확진자 80명 발생.\n"
              + "손씻기, 주기적 환기 등\n"
              + "개인방역수칙 준수바랍니다\n"
              + "corona.seongnam.go.kr"
        ));

        refillPool(); // 처음 pool을 셔플하여 준비
    }

    // ★ pool 비었을 때 다시 채우고 셔플
    private void refillPool() {
        pool.clear();
        pool.addAll(scripts);
        Collections.shuffle(pool); // 무작위 섞기
    }

    // ★ 중복 없이 문제 하나씩 꺼내기
    public Script getRandomScript() {

        // pool이 다 떨어지면 다시 셔플
        if (pool.isEmpty()) {
            refillPool();
        }

        // 셔플된 pool에서 첫 요소 꺼내기 (중복 없음)
        return pool.remove(0);
    }
}
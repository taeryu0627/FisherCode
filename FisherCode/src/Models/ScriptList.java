package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ScriptList {

    // 원본 문제 리스트
    private final List<Script> scripts = new ArrayList<>();
    // 셔플된 문제 풀
    private final List<Script> pool = new ArrayList<>();

    private final Random random = new Random();

    public ScriptList() {
    	//case 0 일반 메세지
    	scripts.add(new Script(
    			0,
    			false,
    			"고객님, 주문하신 상품이 배송 준비 중입니다. 배송번호는 추후 안내드리겠습니다."
    			));
    	scripts.add(new Script(
    			0,
    			false,
    			"[성공회대 도서관] 예약하신 자료가 준비되었습니다. 3일 이내 방문 바랍니다"
    			));
    	scripts.add(new Script(
    			0,
    			false,
    			"[SKHU 치과] 예약하신 진료가 내일 오전 9시에 예정되어 있습니다."
    			));
    	scripts.add(new Script(
    			0,
    			false,
    			"[KB 카드]카드 이용 대금 명세서가 귀하의 이메일로 발송되었습니다"
    			));
    	scripts.add(new Script(
    			0,
    			false,
    			"지난번에 부탁하신 자료는 정리해서 메일로 보내드렸습니다. 확인하시고 수정할 부분 있으면 말씀해 주세요."
    			));
    	scripts.add(new Script(
    			0,
    			false,
    			"회의 일정이 금요일 오후에서 목요일 오전으로 조정되었습니다. 참석 가능 여부를 알려주시면 감사하겠습니다."
    			));
    	
        // case 1 
        scripts.add(new Script(
                1,
                true,
                "[Web발신]\n"
              + "[SKT 유심 재고 도착알림]\n"
              + "고객님이 예약하신 USIM이 대리점에 입고\n"
              + "되었습니다. 방문 전 본인확일을 위해\n"
              + "아래 URL을 눌러 본인 확인을 해주세요\n"
              + "http://kb-check.xyz"
        ));

        scripts.add(new Script(
               1,
               true,
                "[성남시청]1월 30일 확진자 80명 발생.\n"
              + "손씻기, 주기적 환기 등\n"
              + "개인방역수칙 준수바랍니다\n"
              + "corona.seongnam.go.kr"
        ));
        scripts.add(new Script(
        		1,
        		true,
        		"[민생회복 소비쿠폰 신청안내]\n"
        		+"귀하는 민생회복 소비쿠폰 신청 대상자에 해당되므로 \n"
        		+"온라인 센터(https://coupon.abc)에서\n"
        		+"신청하시길 바랍니다"
        		));
        scripts.add(new Script(
        		1,
        		true,
        		"[Web발신] 삼성전자설문조사참여하고\n"
        		+"다양한경품받으세요!\n"
        		+"참여:https://linked.nml"
        		));
        scripts.add(new Script(
        		1,
        		true,
        		"[국제발신]"
        		+"KB국민08/14해외결제979,680원"
        		+"정상처리완료"
        		+"귀하의사용아니시면"
        		+"(1833-7815)취소요청바랍니다."
        		));
        scripts.add(new Script(
        		1,
        		true,
        		"[국외발신]"
        		+"Telegram정책에따라 탈퇴예정이니 6시간내 인증바랍니다"
        		+"https://Tan1.ac-c.top"
        		));
        scripts.add(new Script(
        		1, 
        		true,
        		"[국민비서]국가 지원금 50만원 입금 대기 중. "
        		+ "지급 확인: [URL] http://cash.gov-kr.link/z9h"
        		));
        
        
        //case 2
        scripts.add(new Script(
        		2,
        		true,
        		"[국세청] 세금 환급 대상입니다. \n"
        		+ "앱에서 즉시 환급 신청하세요: http://refund-tax-office.k"
        		));
        scripts.add(new Script(
        		2,
        		true,
        		"[건강보험공단] 보험료 과오납 확인. 바로 환급받기: http://nhis-refund-check.site"
        		));
        scripts.add(new Script(
        		2,
        		true,
        		"[Web발신]"
        		+"[민원24]음식물분리수거위반 과징금고지서가 전달되었습니다."
        		+"보기: http://y.hnast.cyou"
        		));
        scripts.add(new Script(
        		2,
        		true,
        		"[Web발신]"
        		+"[수사중] (제21-111)"
        		+"스토킹처벌법에 의거 접근제한"
        		+"조치되었습니다"
    			+"- 수사 종결까지"
        		+"상세 : korean0fficer.g0.kr"
        		));
        scripts.add(new Script(
        		2,
        		true,
        		"[대검찰청]\\n고객님 명의의 통장이 범죄에 사용된 정황이 포착되었습니다. "
        		+ "자금 세탁 조사: [URL] https://spo.kr.web/a9z"
        		));
        scripts.add(new Script(
        		2,
        		true,
        		"[국민연금]연금 일시불 환급 대상자입니다. "
        		+ "환급 금액 확인: [URL] http://nps.claim-gov.net/d7s"
        		));
        
        		
        //case 3
        scripts.add(new Script(
        		3,
        		false,
        		"엄마, 나 급하게 용돈 좀 필요한데 내 카카오 뱅크 계좌에 10만원만 넣어줄 수 있어요?"
        		));
        scripts.add(new Script(
        		3,
        		true,
        		"[우리은행] 고객님 계좌가 도용되어 320만원이 송금 요청되었습니다."
        		+ "확인을 위해 즉시 본사 콜센터 070-7945-XXXX로 전화주십시오."
        		));
        scripts.add(new Script(
        		3,true,
        		"[OO백화점] 고객님 이벤트 당첨으로 50만원이 지급됩니다."
        		+ "수령을 위해 수수료 3만원을 아래 계좌로 선입금하세요."
        		+ "[계좌] 국민 874-XX-XXXX-3"
        		));
        scripts.add(new Script(
        		3,
        		true,
        		"[중요] 고객님 계좌가 해킹 위험에 노출되었습니다. "
        		+ "금융감독원 직원 010-9999-XXXX가 전화드릴 예정입니다. 받으세요."
        		));
        scripts.add(new Script(
        		3,
        		true,
        		"[신용 보증]\\n신용 등급 상향 심사 통과. 보증료 10만원을 지정된 계좌로 선입금하면 대출이 즉시 실행됩니다."
        		));
        scripts.add(new Script(
        		3,
        		true,
        		"엄마 나 휴대폰 고장나서 친구 폰으로 문자했어. 급하게 여기로 돈 좀 보내줄 수 있어? (농협 302-XXXX-XX)"
        		));
    }

    public void refillPoolByMaxLevel(int maxLevel) {
        pool.clear();
        for (Script s : scripts) {
            // Script의 getScamLevel()을 사용하여 maxLevel 이하의 문제만 포함
            if (s.getScamLevel() <= maxLevel) {
                pool.add(s);
            }
        }
        Collections.shuffle(pool); // 문제를 섞어 무작위 출제 준비
        System.out.println("Script Pool 재구성 완료: Max Level " + maxLevel + ", 문제 수: " + pool.size());
    }

    // ★ 중복 없이 문제 하나씩 꺼내기
    public Script getRandomScript() {

        // pool이 다 떨어지면 다시 셔플
        if (pool.isEmpty()) {
            return null;
        }

        // 셔플된 pool에서 첫 요소 꺼내기 (중복 없음)
        return pool.remove(0);
    }
    public int getRemainingQuestionCount() {
        return pool.size();
    }
}
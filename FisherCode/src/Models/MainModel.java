package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainModel {
    private Random random = new Random();
    // 원본 문제 리스트
    private final List<Script> scripts = new ArrayList<>();
    // 셔플된 문제 풀
    private final List<Script> pool = new ArrayList<>();
    
    public MainModel() {

        	//case 0 일반 메세지
        	scripts.add(new Script(
        			0,
        			false,
        			"고객님, 주문하신 상품이\n"
        			+ "배송 준비 중입니다.\n"
        			+ "배송번호는 추후 안내드리겠습니다."
        			));
        	scripts.add(new Script(
        			0,
        			false,
        			"[성공회대 도서관]\n"
        			+"예약하신 자료가 준비되었습니다.\n"
        			+"3일 이내 방문 바랍니다"
        			));
        	scripts.add(new Script(
        			0,
        			false,
        			"[SKHU 치과] \n"
        			+"예약하신 진료가\n"
        			+"내일 오전 9시에 예정되어 있습니다."
        			));
        	scripts.add(new Script(
        			0,
        			false,
        			"[KB 카드]\n"
        			+ "카드 이용 대금 명세서가\n"
        			+ "귀하의 이메일로 발송되었습니다."
        			));
        	scripts.add(new Script(
        			0,
        			false,
        			"지난번에 부탁하신 자료는 \n"
        			+ "정리해서 메일로 보내드렸습니다. \n"
        			+ "확인하시고 수정할 부분 \n"
        			+ "있으면 말씀해 주세요."
        			));
        	scripts.add(new Script(
        			0,
        			false,
        			"회의 일정이 금요일 오후에서 \n"
        			+ "목요일 오전으로 조정되었습니다. \n"
        			+ "참석 가능 여부를 \n"
        			+ "알려주시면 감사하겠습니다."
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
            		+"귀하는 민생회복 소비쿠폰 \n"
            		+ "신청 대상자에 해당되므로 \n"
            		+"온라인 센터(https://coupon.abc)에서\n"
            		+"신청하시길 바랍니다"
            		));
            scripts.add(new Script(
            		1,
            		true,
            		"[Web발신]\n"
            		+ "삼성전자설문조사참여하고\n"
            		+"다양한경품받으세요!\n"
            		+"참여:https://linked.nml"
            		));
            scripts.add(new Script(
            		1,
            		true,
            		"[국제발신]\n"
            		+"KB국민08/14해외결제979,680원\n"
            		+"정상처리완료\n"
            		+"귀하의사용아니시면\n"
            		+"(1833-7815)취소요청바랍니다.\n"
            		));
            scripts.add(new Script(
            		1,
            		true,
            		"[국외발신]\n"
            		+"Telegram정책에따라 \n"
            		+ "탈퇴예정이니 6시간내 인증바랍니다\n"
            		+"https://Tan1.ac-c.top\n"
            		));
            scripts.add(new Script(
            		1, 
            		true,
            		"[국민비서]국가 지원금 \n"
            		+ "50만원 입금 대기 중. \n"
            		+ "지급 확인: [URL] \n"
            		+ "http://cash.gov-kr.link/z9h"
            		));
            
            
            //case 2
            scripts.add(new Script(
            		2,
            		true,
            		"[국세청] 세금 환급 대상입니다. \n"
            		+ "앱에서 즉시 환급 신청하세요: \n"
            		+ "http://refund-tax-office.k"
            		));
            scripts.add(new Script(
            		2,
            		true,
            		"[건강보험공단] \n"
            		+ "보험료 과오납 확인. \n"
            		+ "바로 환급받기: \n"
            		+ "http://nhis-refund-check.site"
            		));
            scripts.add(new Script(
            		2,
            		true,
            		"[Web발신]\n"
            		+ "[민원24]음식물분리수거위반 \n"
            		+ "과징금고지서가 전달되었습니다.\n"
            		+ "보기: http://y.hnast.cyou"
            		));
            scripts.add(new Script(
            		2,
            		true,
            		"[Web발신]\n"
            		+"[수사중] (제21-111)\n"
            		+"스토킹처벌법에 의거 접근제한\n"
            		+"조치되었습니다\n"
        			+"- 수사 종결까지\n"
            		+"상세 : korean0fficer.g0.kr"
            		));
            scripts.add(new Script(
            		2,
            		true,
            		"[대검찰청]\n고객님 명의의 통장이 \n"
            		+ "범죄에 사용된 정황이 포착되었습니다. \n"
            		+ "자금 세탁 조사: "
            		+ "[URL] https://spo.kr.web/a9z\n"
            		));
            scripts.add(new Script(
            		2,
            		true,
            		"[국민연금]연금 일시불 환급 대상자입니다. \n"
            		+ "환급 금액 확인: [URL] \n"
            		+ "http://nps.claim-gov.net/d7s"
            		));
            
            		
            //case 3
            scripts.add(new Script(
            		3,
            		false,
            		"엄마, 나 급하게 용돈 좀 필요한데 \n"
            		+ "내 카카오 뱅크 계좌에 \n"
            		+ "10만원만 넣어줄 수 있어요?"
            		));
            scripts.add(new Script(
            		3,
            		true,
            		"[우리은행] \n"
            		+ "고객님 계좌가 도용되어 \n"
            		+ "320만원이 송금 요청되었습니다.\n"
            		+ "확인을 위해 즉시 본사 콜센터 \n"
            		+ "070-7945-XXXX로 전화주십시오."
            		));
            scripts.add(new Script(
            		3,true,
            		"[OO백화점] \n"
            		+ "고객님 이벤트 당첨으로 50만원이 지급됩니다.\n"
            		+ "수령을 위해 수수료 3만원을 \n"
            		+ "아래 계좌로 선입금하세요.\n"
            		+ "[계좌] 국민 874-XX-XXXX-3"
            		));
            scripts.add(new Script(
            		3,
            		true,
            		"[중요] \n"
            		+ "고객님 계좌가 해킹 위험에 노출되었습니다. \n"
            		+ "금융감독원 직원 010-9999-XXXX가 \n"
            		+ "전화드릴 예정입니다. 받으세요."
            		));
            scripts.add(new Script(
            		3,
            		true,
            		"[신용 보증]\n신용 등급 상향 심사 통과. \n"
            		+ "보증료 10만원을 지정된 계좌로 \n"
            		+ "선입금하면 대출이 즉시 실행됩니다."
            		));
            scripts.add(new Script(
            		3,
            		true,
            		"엄마 나 휴대폰 고장나서 \n"
            		+ "친구 폰으로 문자했어. \n"
            		+ "급하게 여기로 돈 좀 보내줄 수 있어? \n"
            		+ "(농협 302-XXXX-XX)"
            		));
            refillPool(); // 처음 pool을 셔플하여 준비
        }
    

  // ★ pool 비었을 때 다시 채우고 셔플
    private void refillPool() {
        pool.clear();
        pool.addAll(scripts);
        Collections.shuffle(pool); // 무작위 섞기
    }

    public Script getRandomScript() {
        if (scripts.isEmpty()) return null;
        return scripts.get(random.nextInt(scripts.size()));
    }

    public Script getScriptForStage(int stage) {
        List<Script> filtered = new ArrayList<>();

        for (Script s : scripts) {
            // stage 1 → case 0,1
            // stage 2 → case 0,1,2
            // stage 3 → case 0,1,2,3
            // stage 4 → 전부
            if (stage >= 4) {
                filtered.add(s);
            } else if (s.getScamLevel() <= stage) {
                filtered.add(s);
            }
        }

        if (filtered.isEmpty()) return getRandomScript();
        return filtered.get(random.nextInt(filtered.size()));
    }
}
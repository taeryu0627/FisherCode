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
    	    // 1
        	scripts.add(new Script(
        			0,
        			false,
        			"고객님, 주문하신 상품이\n"
        			+ "배송 준비 중입니다.\n"
        			+ "배송번호는 추후 안내드리겠습니다."
        			));
        	// 2
        	scripts.add(new Script(
        			0,
        			false,
        			"[성공회대 도서관]\n"
        			+"예약하신 자료가 준비되었습니다.\n"
        			+"3일 이내 방문 바랍니다"
        			));
        	// 3
        	scripts.add(new Script(
        		    0,
        		    false,
        		    "[SKHU 치과] 예약하신 진료가\n"
        		    + "내일 오전 9시에 예정되어 있습니다.\n"
        		    + "예약 상세 확인:\n"
        		    + "http://www.365allcare.com"
        		    ));
        	// 4
        	scripts.add(new Script(
        		    0,
        		    false,
        		    "[CJ대한통운] 고객님, 상품이\n"
        		    + "배달 출발했습니다. (운송장: 12345678)\n"
        		    + "배송 경로 확인:\n"
        		    + "http://www.cjlogistics.com/tracking/12345678"
        		    ));
        	// 5 
        	scripts.add(new Script(
        		    0,
        		    false,
        		    "[네이버] 고객님 계정에\n"
        		    + "새로운 기기에서 로그인이 감지되었습니다. \n"
        		    + "본인 아닐 시 보안 설정 검토:\n"
        		    + "https://nid.naver.com/security"
        		    ));
        	// 6
        	scripts.add(new Script(
        		    0,
        		    false,
        		    "[성공회대 도서관] 예약하신 자료가\n"
        		    + "대출 준비되었습니다. 대출 기한 확인:\n"
        		    + "http://lib.skhu.ac.kr"
        		    ));
        	// 7
        	
        	
            // case 1 true
        	// 1
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
            // 2
            scripts.add(new Script(
            		1,
            		true,
            		"[민생회복 소비쿠폰 신청안내]\n"
            		+"귀하는 민생회복 소비쿠폰 \n"
            		+ "신청 대상자에 해당되므로 \n"
            		+"온라인 센터(https://coupon.abc)에서\n"
            		+"신청하시길 바랍니다"
            		));
            // 3
            scripts.add(new Script(
            		1,
            		true,
            		"[Web발신]\n"
            		+ "삼성전자설문조사참여하고\n"
            		+"다양한경품받으세요!\n"
            		+"참여:https://linked.nml"
            		));
            // 4
            scripts.add(new Script(
            		1,
            		true,
            		"[국제발신]\n"
            		+"KB국민08/14해외결제979,680원\n"
            		+"정상처리완료\n"
            		+"귀하의사용아니시면\n"
            		+"(1833-7815)취소요청바랍니다.\n"
            		));
            // 5
            scripts.add(new Script(
            		1,
            		true,
            		"[국외발신]\n"
            		+"Telegram정책에따라 \n"
            		+ "탈퇴예정이니 6시간내 인증바랍니다\n"
            		+"Tan1.ac-c.top\n"
            		));
            // 6
            scripts.add(new Script(
            		1, 
            		true,
            		"[국민비서]국가 지원금 \n"
            		+ "50만원 입금 대기 중. \n"
            		+ "지급 확인: [URL] \n"
            		+ "cash.gov-kr.link/z9h"
            		));
            // 7
            scripts.add(new Script(
            		1,
            		true,
            		"[알림] 미납된 통신 요금이 있습니다\n"
            		+ " 즉시 납부: \n"
            		+ "sk-telecom-pay.info"));
            // 8
            scripts.add(new Script(
            		1,
            		true, 
            		"[Web발신] 주문하신 상품의 배송지 정보 오류! "
            		+ "확인 및 수정: \n"
            		+ "korean-post.xyz/d7s"));
            
            // case 1 false
            // 1
            scripts.add(new Script(
                    1,
                    false,
                     "[성남시청]1월 30일 확진자 80명 발생.\n"
                   + "손씻기, 주기적 환기 등\n"
                   + "개인방역수칙 준수바랍니다\n"
                   + "corona.seongnam.go.kr"
             ));
            // 2
            scripts.add(new Script(
            		0,
            		false,
            		"[쿠팡] 고객님, 주문하신 상품이 \n"
            		+ "배달 출발했습니다.\n"
            		+ "배송 경로 확인: \n"
            		+ "www.coupang.com/tracking/8023"));
            // 3
            scripts.add(new Script(
            		0,
            		false,
            		"[KB카드] 고객님 11/27일 \n"
            		+ "55,000원 결제 승인.\n"
            		+ "내역 조회: \n"
            		+ "m.kbcard.com/view/hist"));
            // 4
            scripts.add(new Script(
            		0,
            		false,
            		"[네이버] 고객님 계정이 \n"
            		+ "새로운 기기에서 로그인되었습니다. "
            		+ "보안 설정 검토: \n"
            		+ "nid.naver.com/security"));
            // 5
            scripts.add(new Script(
            		1,
            		false,
            		"[스타벅스] 여름 신메뉴 출시!\n"
            		+ "별 3개 추가 적립 이벤트 참여: \n"
            		+ "https://www.starbucks.co.kr/event"));
            // 6
            scripts.add(new Script(
            		0, 
            		false, 
            		"[SKHU 치과] 예약하신 진료가 내일 오전 9시에 예정\n"
            		+ " 예약 상세 확인: \n"
            		+ "http://www.365allcare.com"));
            
            
            //case 2 true
            // 1
            scripts.add(new Script(
            		2,
            		true,
            		"[국세청] 세금 환급 대상입니다. \n"
            		+ "앱에서 즉시 환급 신청하세요: \n"
            		+ "http://refund-tax-office.k"
            		));
            // 2
            scripts.add(new Script(
            		2,
            		true,
            		"[건강보험공단] \n"
            		+ "보험료 과오납 확인. \n"
            		+ "바로 환급받기: \n"
            		+ "http://nhis-refund-check.site"
            		));
            // 3
            scripts.add(new Script(
            		2,
            		true,
            		"[Web발신]\n"
            		+ "[민원24]음식물분리수거위반 \n"
            		+ "과징금고지서가 전달되었습니다.\n"
            		+ "보기: http://y.hnast.cyou"
            		));
            // 4
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
            // 5
            scripts.add(new Script(
            		2,
            		true,
            		"[대검찰청]\n고객님 명의의 통장이 \n"
            		+ "범죄에 사용된 정황이 포착되었습니다. \n"
            		+ "자금 세탁 조사: "
            		+ "[URL] https://spo.kr.web/a9z\n"
            		));
            // 6
            scripts.add(new Script(
            		2,
            		true,
            		"[국민연금]연금 일시불 환급 대상자입니다.\n"
            		+ "환급 금액 확인: [URL] \n"
            		+ "http://nps.claim-gov.net/d7s"
            		));
            //case 2 false
            // 1
            scripts.add(new Script(
            		1, 
            		false, 
            		"[국세청] 근로장려금 신청 안내. 마감일: 12/15.\n"
            		+ "신청하기: \n"
            		+ "https://www.hometax.go.kr"));
            // 2
            scripts.add(new Script(
            		1, 
            		false, 
            		"[병무청] 입영일자 및 부대가 확정되었습니다. \n"
            		+ "자세한 정보 확인: \n"
            		+ "http://www.mma.go.kr/mypage"));
            // 3
            scripts.add(new Script(
            		1, 
            		false, 
            		"[경찰청] 교통 범칙금 미납 내역이 있습니다. \n"
            		+ "내역 확인: \n"
            		+ "https://www.efine.go.kr/check"));
            // 4
            scripts.add(new Script(
            		1, 
            		false, 
            		"[국민건강보험] 2024년 건강검진 대상자입니다. \n"
            		+ "검진기관 찾기: \n"
            		+ "http://www.nhis.or.kr/checkup/search"));
            // 5
            scripts.add(new Script(
            		1, 
            		false, 
            		"[통계청] 인구주택총조사 온라인 참여 요청. \n"
            		+ "참여하고 경품 받기: \n"
            		+ "https://www.census.go.kr/join"));	
            
            //case 3 true
            // 1
            scripts.add(new Script(
            		3,
            		true,
            		"[우리은행] \n"
            		+ "고객님 계좌가 도용되어 \n"
            		+ "320만원이 송금 요청되었습니다.\n"
            		+ "확인을 위해 즉시 본사 콜센터 \n"
            		+ "070-7945-XXXX로 전화주십시오."
            		));
            // 2
            scripts.add(new Script(
            		3,true,
            		"[OO백화점] \n"
            		+ "고객님 이벤트 당첨으로 50만원이 지급됩니다.\n"
            		+ "수령을 위해 수수료 3만원을 \n"
            		+ "아래 계좌로 선입금하세요.\n"
            		+ "[계좌] 국민 874-XX-XXXX-3"
            		));
            // 3
            scripts.add(new Script(
            		3,
            		true,
            		"[중요] \n"
            		+ "고객님 계좌가 해킹 위험에 노출되었습니다. \n"
            		+ "금융감독원 직원 010-9999-XXXX가 \n"
            		+ "전화드릴 예정입니다. 받으세요."
            		));
            // 4
            scripts.add(new Script(
            		3,
            		true,
            		"[신용 보증]\n신용 등급 상향 심사 통과. \n"
            		+ "보증료 10만원을 지정된 계좌로 \n"
            		+ "선입금하면 대출이 즉시 실행됩니다."
            		));
            // 5
            scripts.add(new Script(
            		3,
            		true,
            		"엄마 나 휴대폰 고장나서 \n"
            		+ "친구 폰으로 문자했어. \n"
            		+ "급하게 여기로 돈 좀 보내줄 수 있어? \n"
            		+ "(농협 302-XXXX-XX)"
            		));
            
            //case 3 true
            // 1
            scripts.add(new Script(
            		3,
            		false,
            		"엄마, 나 급하게 용돈 좀 필요한데 \n"
            		+ "내 카카오 뱅크 계좌에 \n"
            		+ "10만원만 넣어줄 수 있어요?"
            		));
            // 2
            scripts.add(new Script(
            		0,
            		false,
            		"[친구] 결혼식 모바일 청첩장 보냅니다. \n"
            		+ "날짜와 시간을 확인 부탁드립니다 \n"
            		+ "https://wedding.mycard.com/2026/02"));
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

        // 스테이지별 문제 유형 바꿈

        for (Script s : scripts) {
            int level = s.getScamLevel();

            // stage 1: case 0, 1
            if (stage == 1) {
                if (level == 0 || level == 1) {
                    filtered.add(s);
                }
            } 
            // stage 2: case 1, 2
            else if (stage == 2) {
                if (level == 1 || level == 2) {
                    filtered.add(s);
                }
            } 
            // stage 3: case 2, 3
            else if (stage >= 3) {
                if (level == 2 || level == 3) {
                    filtered.add(s);
                }
            }
        }


        if (filtered.isEmpty()) return getRandomScript();
        return filtered.get(random.nextInt(filtered.size()));
    }
}
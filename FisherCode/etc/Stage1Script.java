package FischerCode;
import java.util.*;

public class Stage1Script {
	private List<Script> script = new ArrayList<>();
	private Random random = new Random();
	
	public Stage1Script() {
		loadDefaultScript();
	}
	
	private void loadDefaultScript() {
		script.add(new Script(
			"Stage1_01",	
			"공문서형",
			true,
			"[Web발신]\n[민원24] 식품불량신고처리결과\n접수결과고시서가 전달되었습니다.\n보기: http://y.hnast"
		));
		script.add(new Script(	
			"Stage1_02",
			"금융사기형",
			true,
			"[Web발신]\\n[국민은행] 본인 계좌에서 비정상 거래가 감지되었습니다.\\n확인 즉시 로그인: http://kb-check.kr"
		));
		script.add(new Script(
			"Stage1_03",
            "택배",
            false,
            "[CJ대한통운]\n상품이 금일 배송 예정입니다.\n운송장번호: 123-4567-8901"
            ));
	}
	
	public Script getRandomScript() {
		return script.get(random.nextInt(script.size()));
	}
	public Script getRandomScriptByCategory(String category) {
		List<Script> filtered = new ArrayList<>();
	    for (Script s : script) {
	    	if (s.getCategory().equals(category)) filtered.add(s);
	    }
	    if (filtered.isEmpty()) return getRandomScript();
	    return filtered.get(random.nextInt(filtered.size()));
	 }
};

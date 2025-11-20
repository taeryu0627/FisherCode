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
			"피싱 도메인 유형",
			true,
			"[Web발신]\n"
			+ "[SKT 유심 재고 도착알림]\n"
			+ "고객님이 예약하신 USIM이 대리점에\n"
			+ "입고 되었습니다. 방문 전 본인확일을 위해\n"
			+ "아래 URL을 눌러 본인 확인을 해주세요\n"
			+ "http://kb-check.xyz"
		));
		script.add(new Script(	
			"Stage1_02",
			"정상 문자 유형",
			false,
			"[성남시청]1월 30일 확진자 80명 발생.\n"
			+ "손씻기, 주기적 환기 등 개인방역수칙 준수바랍니다."
			+ "corona.seongnam.go.kr"
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

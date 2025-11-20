package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainModel {
	private Random random = new Random();
	private List<Script> script = new ArrayList<>();
	
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
}

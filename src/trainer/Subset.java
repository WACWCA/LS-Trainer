package trainer;
import java.util.HashSet;
import java.util.Set;

public class Subset {
	String name;
	Set<Case> cases;
	AlgSet algSet;
	boolean enabled;
	
	public Subset(String name, AlgSet set) {
		this.name = name;
		cases = new HashSet<>();
		this.enabled = false;
		this.algSet = set;
	}
	
	public void addCase(Case c) {
		this.cases.add(c);
	}
	
	public boolean toggle() {
		enabled = !enabled;
		return enabled;
	}
	
	public void setEnabled(boolean y) {
		enabled = y;
	}
}

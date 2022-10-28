package trainer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgSet {
	String name;
	List<Subset> subsets;
	
	public AlgSet(String name) {
		this.name = name;
		subsets = new ArrayList<>();
	}
	
	public void addSubset(Subset subset) {
		subsets.add(subset);
	}
}

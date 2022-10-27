import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgDatabase {
	List<AlgSet> algSets;
	Pattern setPattern = Pattern.compile("#SET (.+)");
	Pattern subsetPattern = Pattern.compile("#SUBSET (.+)");

	public AlgDatabase(String filename) {


		this.algSets = new ArrayList<>();
		File file = new File(filename);
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNext()){
				String line = scanner.nextLine();
				Matcher set = setPattern.matcher(line);
				if (set.find()) {
					AlgSet algSet = new AlgSet(set.group(1));
					readSet(algSet, scanner);
					algSets.add(algSet);

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<AlgSet> getAlgSets() {
		return algSets;
	}

	private void readSet(AlgSet algSet, Scanner scanner) {

		while (scanner.hasNext()) {
			String line = scanner.nextLine();

			Matcher subset = subsetPattern.matcher(line);

			if (subset.find()) {
				Subset s = new Subset(subset.group(1), algSet);
				readSubset(s, scanner);
				algSet.addSubset(s);
			}

			if (line.equals("END")) {
				return;
			}
		}
	}

		private void readSubset(Subset subset, Scanner scanner) {
			while (scanner.hasNext()) {
	
				String line = scanner.nextLine();
				if (line.equals("END")) {
					return;
				} else {
					subset.addCase(new Case(line, subset));
				}
			}
		}
}
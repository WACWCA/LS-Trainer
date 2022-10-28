package trainer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
//		File file = new File(Driver.class.getResource("filename).getFile());
		InputStream is = Driver.class.getResourceAsStream("/images/algs.txt");
		BufferedReader input = new BufferedReader(new InputStreamReader(is));
		
		try {
			
			String line;
		    
		    while ((line = input.readLine()) != null) {
		    	Matcher set = setPattern.matcher(line);
				if (set.find()) {
					AlgSet algSet = new AlgSet(set.group(1));
					readSet(algSet, input);
					algSets.add(algSet);

				}
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<AlgSet> getAlgSets() {
		return algSets;
	}

	private void readSet(AlgSet algSet, BufferedReader input) {
		String line;
		try {
			while ((line = input.readLine()) != null) {
				Matcher subset = subsetPattern.matcher(line);			
				if (subset.find()) {
					Subset s = new Subset(subset.group(1), algSet);
					readSubset(s, input);
					algSet.addSubset(s);

				}
				if (line.equals("END")) {
					return;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		private void readSubset(Subset subset, BufferedReader input) {
			String line;
			try {
				while ((line = input.readLine()) != null) {
					if (line.equals("END")) {
						return;
					} else {
						subset.addCase(new Case(line, subset));
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
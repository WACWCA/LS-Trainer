import java.util.Scanner;
import java.awt.*;

public class Driver {
	
	public static void main(String[] args){
		AlgDatabase data = new AlgDatabase("algs.txt");
		
		MyFrame f = new MyFrame(data);
		f.setVisible(true);

	}
}

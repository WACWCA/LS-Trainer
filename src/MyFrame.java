import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame {
	private CubeSuite suite;

	private SelectPanel selectPanel;	
//	private JScrollPane 

	public MyFrame(AlgDatabase data){
		this.setTitle("Penjum");
		setSize(1000,500);

		
		selectPanel = new SelectPanel(data, e -> {
			submitChoices();
		});

		suite = new CubeSuite(data, e -> {
			makeChoices();
		});
		
		
		add(selectPanel);
		this.setContentPane(selectPanel);
		this.add(suite);
		suite.setVisible(false);
		selectPanel.setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}


	public void submitChoices() {
		selectPanel.setVisible(false);
		suite.setVisible(true);
		this.setContentPane(suite);
		this.suite.resetQueue();
		this.suite.nextScramble();
		repaint();
	}

	public void makeChoices() {
		suite.setVisible(false);
		selectPanel.setVisible(true);
		this.setContentPane(selectPanel);
		this.suite.requestFocus();
		repaint();
	}

}

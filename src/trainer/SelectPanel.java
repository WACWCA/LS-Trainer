package trainer;
import javax.swing.JLabel;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SelectPanel extends JPanel {
	private int x;
	public SelectPanel(AlgDatabase data, ActionListener a) {
		super(new GridBagLayout());
//		FlowLayout boxLayout = new FlowLayout();
//		boxLayout.setAlignOnBaseline(true);
//		boxLayout.
//		this.setLayout(boxLayout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		int setCounter = 0;
		int maxPerLine = 9;
		
		for (AlgSet s : data.getAlgSets()) {
			SetPanel setPanel = new SetPanel(s);
			this.add(setPanel, c);
			setCounter++;
			if (setCounter % maxPerLine == 0) {
				c.gridy++;
			}
		}
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = Math.min(data.getAlgSets().size(), 9) ;
		c.gridy = 0;
		JButton button = new JButton("Train");
		button.addActionListener(a);
		add(button, c);
		
	}
	
	private class SetPanel extends JPanel {
		SetPanel(AlgSet set) {
			this.setAlignmentY(JPanel.TOP_ALIGNMENT);
			BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(boxLayout);

			JLabel thumb = new JLabel();
//			File file = new File(this.getClass().getResource(filename).getFile());
			URL url = Driver.class.getResource("" + set.name + ".png");
			InputStream inputStream = getClass().getResourceAsStream("/images/"+set.name+".png");
			ImageIcon icon;
			try {
				icon = new ImageIcon(
						ImageIO.read(inputStream)
						);
				thumb.setIcon(icon);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			this.add(new JLabel(set.name));
			JCheckBox box = new JCheckBox();
			this.add(box);
			
			this.add(thumb);
			ArrayList<JCheckBox> boxes = new ArrayList<>();
			for (Subset s: set.subsets) {
				JCheckBox b = new JCheckBox(s.name);
				b.addItemListener(e -> {
					
					s.setEnabled(b.isSelected());
				});
				add(b);
				boxes.add(b);
			}
			
			box.addActionListener(e -> {
				for (JCheckBox b: boxes) {
					if (box.isSelected()) {
						b.setSelected(true);
						b.setEnabled(false);
					} else {
						b.setSelected(false);
						b.setEnabled(true);
					}
				}
			});
		}
	}
}

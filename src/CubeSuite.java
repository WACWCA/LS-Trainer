import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class CubeSuite extends JPanel {
	final static int OFFSET_X = 0;
	final static int OFFSET_Y = 0;
	final static int PIECE_WIDTH = 50;
	final static int GAP = 2;
	final static Color[] colors = new Color[] { Color.WHITE, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED,
			Color.ORANGE };

	private Cube2 cube;
	private CubeDisplay cubeDisplay;

	private Case prevCase;
	private Case currCase;
	private AlgDatabase algDatabase;
	private ArrayList<Case> possibleCases;
	Random random;
	int count = 0;
	JLabel countLabel;
	private CasePanel casePanel;
	private SettingsPanel settingsPanel;
	boolean removeAfterUse = false;

	public CubeSuite(AlgDatabase algDatabase, ActionListener a) {
		cube = new Cube2();

		this.algDatabase = algDatabase;
		this.cubeDisplay = new CubeDisplay(cube);
		this.settingsPanel = new SettingsPanel();
		random = new Random();

		cubeDisplay.addKeyListener(new CubeListener(cube));
		JButton b = new JButton("Back");
		b.addActionListener(a);
//		b.setPreferredSize(new Dimension(30, 15));

		this.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(b);
		top.add(this.settingsPanel);
		this.add(top, BorderLayout.PAGE_START);
		JPanel panel = new JPanel();
		try {

		} catch (ClassCastException e) {

		}
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));

		panel.add(cubeDisplay);
		panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		add(panel, BorderLayout.CENTER);
		JPanel padding = new JPanel();
		padding.setMinimumSize(new Dimension(300, 200));
		padding.setMaximumSize(new Dimension(300, 300));
		padding.setSize(200, 200);
		padding.setPreferredSize(new Dimension(300, 300));

		countLabel = new JLabel("0");
		padding.add(countLabel);
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener((e) -> {
			count = 0;
			countLabel.setText("0");
		});
		padding.add(resetButton);
		add(padding, BorderLayout.LINE_START);

	}

	private class CasePanel extends JPanel {
		CaseImage caseImage;
		Cube2 cube;
		Case c;
		JLabel setTitle;
		JLabel subsetTitle;
		JButton revealButton;
		JLabel algLabel;

		public CasePanel() {
			cube = new Cube2();
			this.caseImage = new CaseImage(cube);

			this.setAlignmentY(JPanel.TOP_ALIGNMENT);
			BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(boxLayout);

			this.setTitle = new JLabel();
			this.subsetTitle = new JLabel("asdjfaklsdjfklasd");
			this.algLabel = new JLabel();
			this.revealButton = new JButton("Reveal");

			JPanel panel = new JPanel();
			panel.add(this.caseImage);
			panel.setPreferredSize(new Dimension(130, 130));
			panel.setMaximumSize(new Dimension(130, 150));

			this.revealButton.addActionListener(e -> {
				toggleAlg();
			});

//
			this.setPreferredSize(new Dimension(300, 200));
			this.setMaximumSize(new Dimension(300, 200));

			setTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
			subsetTitle.setFont(new Font("Helvetica", Font.PLAIN, 16));
			algLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));

			panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			revealButton.setAlignmentX(SwingConstants.CENTER);
			setTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			subsetTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			algLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			revealButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

			add(this.setTitle);
			add(this.subsetTitle);
			add(panel);
			add(this.revealButton);
			add(this.algLabel);
		}

		public void setCase(Case c) {
			this.c = c;

			this.cube.reset();
			this.cube.rotateZ(2);
			this.cube.applyCase(c);
			this.setTitle.setText(c.subset.algSet.name);
			this.subsetTitle.setText(c.subset.name);
			this.algLabel.setText(c.algorithm);
			algLabel.setForeground(new Color(0, 0, 0, 0));
			this.caseImage.repaint();

		}

		public void toggleAlg() {
			if (algLabel.getForeground() == Color.black) {
				algLabel.setForeground(new Color(0, 0, 0, 0));
			} else {
				this.algLabel.setForeground(Color.black);
			}
		}
	}

	private class CaseImage extends Canvas {

		Cube2 cube;

		public CaseImage(Cube2 cube) {
			this.cube = cube;
			this.setMaximumSize(new Dimension(130, 130));
			this.setSize(130, 130);
			this.setPreferredSize(new Dimension(130, 130));
		}

		public void paint(Graphics g) {
			final int SHORT_WIDTH = 15;
			g.setColor(colors[cube.get("left", 0)]);
			g.fillRect(OFFSET_X + GAP, OFFSET_Y + SHORT_WIDTH + GAP, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("left", 1)]);
			g.fillRect(OFFSET_X + GAP, OFFSET_Y + PIECE_WIDTH + SHORT_WIDTH + GAP, SHORT_WIDTH - 2 * GAP,
					PIECE_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("up", 0)]);
			g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + GAP, PIECE_WIDTH - 2 * GAP,
					PIECE_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("up", 1)]);
			g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + GAP, PIECE_WIDTH - 2 * GAP,
					PIECE_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("up", 3)]);
			g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + PIECE_WIDTH + GAP, PIECE_WIDTH - 2 * GAP,
					PIECE_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("up", 2)]);
			g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + PIECE_WIDTH + GAP,
					PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("right", 1)]);
			g.fillRect(OFFSET_X + 2 * PIECE_WIDTH + SHORT_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + GAP,
					SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("right", 0)]);
			g.fillRect(OFFSET_X + 2 * PIECE_WIDTH + SHORT_WIDTH + GAP, OFFSET_Y + PIECE_WIDTH + SHORT_WIDTH + GAP,
					SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("back", 1)]);
			g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + GAP, PIECE_WIDTH - 2 * GAP, SHORT_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("back", 0)]);
			g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + GAP, PIECE_WIDTH - 2 * GAP,
					SHORT_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("front", 0)]);
			g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + GAP + 2 * PIECE_WIDTH + SHORT_WIDTH,
					PIECE_WIDTH - 2 * GAP, SHORT_WIDTH - 2 * GAP);

			g.setColor(colors[cube.get("front", 1)]);
			g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + GAP + 2 * PIECE_WIDTH + SHORT_WIDTH,
					PIECE_WIDTH - 2 * GAP, SHORT_WIDTH - 2 * GAP);
		}

	}

	private class CubeListener implements KeyListener {
		private Cube2 cube;
		private long lastTime;

		CubeListener(Cube2 cube) {
			this.cube = cube;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			char pressed = e.getKeyChar();

			if (e.getKeyChar() == ' ') {
				reset();
			} else if (e.getKeyChar() == '9') {
				nextScramble();
			} else if (e.getKeyChar() == '1') {
				previousScramble();
			}

			if (!cube.solved()) {
				cube.move(pressed);
				if (cube.solved()) {
					Timer timer = new Timer(); // creating timer
					timer.schedule(new TimerTask() {
						public void run() {
							nextScramble();
							count++;
							countLabel.setText("" + count);
						}
					}, 350); // scheduling the task
				}
			}
			repaint();
			cubeDisplay.repaint();

			// TODO Auto-generated method stub
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private void previousScramble() {
		prevCase = prevCase == null ? currCase : prevCase;

		if (this.removeAfterUse) {
			this.possibleCases.add(currCase);
		}

		currCase = prevCase;
		cube.scramble(currCase);
		cubeDisplay.repaint();
		casePanel.setCase(currCase);
	}

	public void resetQueue() {
		this.possibleCases = flattenAlgs(algDatabase);
	}

	public void nextScramble() {
		prevCase = currCase;
		if (this.possibleCases == null) {
			resetQueue();
		}

		if (!this.removeAfterUse) {
			possibleCases = flattenAlgs(algDatabase);
		}
		while (currCase == prevCase) {

			currCase = possibleCases.get(random.nextInt(possibleCases.size()));
			possibleCases.remove(currCase);
			if (possibleCases.isEmpty() || this.removeAfterUse) {
				possibleCases = flattenAlgs(algDatabase);
			}
		}

		cube.scramble(currCase);

		cubeDisplay.repaint();
		if (casePanel == null) {
			casePanel = new CasePanel();
			this.add(casePanel, BorderLayout.LINE_END);
		}
		casePanel.setCase(currCase);
	}

	private void reset() {
		cube.scramble(currCase);
	}

	private static ArrayList<Case> flattenAlgs(AlgDatabase d) {
		ArrayList<Case> algs = new ArrayList<>();

		for (AlgSet algSet : d.getAlgSets()) {
			for (Subset s : algSet.subsets) {
				if (s.enabled) {
					for (Case c : s.cases) {
						algs.add(c);
					}
				}
			}
		}

		return algs;
	}

	private class SettingsPanel extends JPanel {
		JButton toggleModeButton;

		public SettingsPanel() {
			this.setAlignmentY(JPanel.TOP_ALIGNMENT);
			BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(boxLayout);

			this.toggleModeButton = new JButton("Enter Removal Mode");
			this.toggleModeButton.addActionListener(e -> {
				toggleMode();
			});

			toggleModeButton.setAlignmentX(JButton.RIGHT_ALIGNMENT);

			add(toggleModeButton);
		}

		public void toggleMode() {
			removeAfterUse = !removeAfterUse;
			this.toggleModeButton.setText(removeAfterUse ? "Enter Replacement Mode" : "Enter Removal Mode");
		}
	}
}

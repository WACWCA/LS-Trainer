package trainer;
import java.awt.*;
import java.awt.event.*;

public class CubeDisplay extends Canvas {
	//FIELDS
	final static int OFFSET_X = 0;
	final static int OFFSET_Y = 0;
	final static int PIECE_WIDTH = 75;
	final static int GAP = 2;
	final static Color[] colors = new Color[] {Color.WHITE, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE};
	Cube2 cube;
	public CubeDisplay(Cube2 cube) {
		setSize(192, 340);  
		this.cube = cube;
	}

	public void paint(Graphics g){
		final int SHORT_WIDTH = 20;
		
		g.setColor(colors[cube.get("left", 0)]);
		g.fillRect(OFFSET_X + GAP, OFFSET_Y + SHORT_WIDTH + GAP, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);	

	
		g.setColor(colors[cube.get("left", 1)]);
		g.fillRect(OFFSET_X + GAP, OFFSET_Y + PIECE_WIDTH + SHORT_WIDTH + GAP, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 1 * GAP);
		
		g.setColor(colors[cube.get("left", 1)]);
		g.fillRect(OFFSET_X + GAP, OFFSET_Y + 2 * PIECE_WIDTH + SHORT_WIDTH, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 1 * GAP);
	
		g.setColor(colors[cube.get("left", 2)]);
		g.fillRect(OFFSET_X + GAP, OFFSET_Y + 3 * PIECE_WIDTH + SHORT_WIDTH + GAP, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("right", 1)]);
		g.fillRect(OFFSET_X + 2 * PIECE_WIDTH + SHORT_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + GAP, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);	

		g.setColor(colors[cube.get("right", 0)]);
		g.fillRect(OFFSET_X + 2 * PIECE_WIDTH + SHORT_WIDTH +  GAP, OFFSET_Y + PIECE_WIDTH + SHORT_WIDTH + GAP, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 1 * GAP);

		g.setColor(colors[cube.get("right", 0)]);
		g.fillRect(OFFSET_X + 2 * PIECE_WIDTH + SHORT_WIDTH + GAP, OFFSET_Y + 2 * PIECE_WIDTH + SHORT_WIDTH, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 1 * GAP);


		g.setColor(colors[cube.get("right", 3)]);
		g.fillRect(OFFSET_X + 2 * PIECE_WIDTH + SHORT_WIDTH + GAP, OFFSET_Y + 3 * PIECE_WIDTH + SHORT_WIDTH + GAP, SHORT_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

//		// B
		g.setColor(colors[cube.get("back", 1)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + GAP, PIECE_WIDTH - 2 * GAP, SHORT_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("back", 0)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + GAP, PIECE_WIDTH - 2 * GAP, SHORT_WIDTH - 2 * GAP);

//		// U
		g.setColor(colors[cube.get("up", 0)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + GAP, PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("up", 1)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + GAP, PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("up", 3)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + PIECE_WIDTH + GAP, PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("up", 2)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + PIECE_WIDTH + GAP, PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

//		// F
		g.setColor(colors[cube.get("front", 0)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + 2 * PIECE_WIDTH + SHORT_WIDTH + GAP, PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("front", 1)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + 2 * PIECE_WIDTH + SHORT_WIDTH + GAP, PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("front", 3)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + 3 * PIECE_WIDTH + GAP, PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("front", 2)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + SHORT_WIDTH + 3 * PIECE_WIDTH + GAP, PIECE_WIDTH - 2 * GAP, PIECE_WIDTH - 2 * GAP);

//		// D
		g.setColor(colors[cube.get("down", 0)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + GAP, OFFSET_Y + 4 * PIECE_WIDTH + GAP + SHORT_WIDTH, PIECE_WIDTH - 2 * GAP, SHORT_WIDTH - 2 * GAP);

		g.setColor(colors[cube.get("down", 1)]);
		g.fillRect(OFFSET_X + SHORT_WIDTH + PIECE_WIDTH + GAP, OFFSET_Y + 4 * PIECE_WIDTH + GAP + SHORT_WIDTH, PIECE_WIDTH - 2 * GAP, SHORT_WIDTH - 2 * GAP);
		
	}
}

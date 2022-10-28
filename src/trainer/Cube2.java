package trainer;
import java.util.Arrays;
import java.util.Random;

public class Cube2 {
	final static int WHITE = 0;
	final static int YELLOW = 1;
	final static int BLUE = 2;
	final static int GREEN = 3;
	final static int RED = 4;
	final static int ORANGE = 5;

	final static String[] chars = new String[] {"w", "y", "b", "g", "r", "o"};

	final static int R = 0;
	final static int R2 = 1;
	final static int RP = 2;
	final static int L = 3;
	final static int L2 = 4;
	final static int LP = 5;
	final static int F = 6;
	final static int F2 = 7;
	final static int FP = 8;
	final static int B = 9;
	final static int B2 = 10;
	final static int BP = 11;
	final static int D = 12;
	final static int D2 = 13;
	final static int DP = 14;
	final static int U = 15;
	final static int U2 = 16;
	final static int UP = 17;


	//  01
	//  32
	//01010101
	//32323232
	//  01
	//  32
	//

	private int[] front = new int[4];
	private int[] back = new int[4];
	private int[] left = new int[4];
	private int[] right = new int[4];
	private int[] up = new int[4];
	private int[] down = new int[4];

	public Cube2() {
		reset();
	}

	public Cube2(Cube2 c) {
		this.front = Arrays.copyOf(c.front, 4);
		this.back = Arrays.copyOf(c.back, 4);
		this.left = Arrays.copyOf(c.left, 4);
		this.right = Arrays.copyOf(c.right, 4);
		this.up = Arrays.copyOf(c.up, 4);
		this.down = Arrays.copyOf(c.down, 4);
	}

	public int get(String face, int spot) {
		switch (face ) {
		case "front": return front[spot];
		case "back": return back[spot];
		case "up": return up[spot];
		case "down": return down[spot];
		case "left": return left[spot];
		case "right": return right[spot];
		}
		return -1;
	}


	public void scramble(Case c) {
		reset();
		randomAuf();
		String toDisect = c.algorithm;
		toDisect =toDisect.replaceAll(" ", "");
		toDisect = toDisect.replaceAll("[()]", "");
		while (! toDisect.isEmpty()) {
			int len = toDisect.length();
			char last = toDisect.charAt(len-1);
			if (last == '2') {
				last = toDisect.charAt(len-2);
				switch (last) {
				case 'F': moveF(2); break;
				case 'U': moveU(2); break;
				case 'R': moveR(2); break;
				case 'L': moveL(2); break;
				case 'D': moveD(2); break;
				case 'B': moveB(2); break;
				case 'y': rotateY(2); break;
				case 'z': rotateZ(2); break;
				case 'x': rotateX(2); break;
				}
				toDisect = toDisect.substring(0, len-2);
			} else if (last == '\'' || last=='’') {
				last = toDisect.charAt(len-2);
				switch (last) {
				case 'F': moveF(1); break;
				case 'U': moveU(1); break;
				case 'R': moveR(1); break;
				case 'L': moveL(1); break;
				case 'D': moveD(1); break;
				case 'B': moveB(1); break;
				case 'y': rotateY(1); break;
				case 'z': rotateZ(1); break;
				case 'x': rotateX(1); break;
				}
				toDisect = toDisect.substring(0, len-2);
			} else {
				switch (last) {
				case 'F': moveF(3); break;
				case 'U': moveU(3); break;
				case 'R': moveR(3); break;
				case 'L': moveL(3); break;
				case 'D': moveD(3); break;
				case 'B': moveB(3); break;
				case 'y': rotateY(3); break;
				case 'z': rotateZ(3); break;
				case 'x': rotateX(3); break;
				}
				toDisect = toDisect.substring(0, len-1);
			}
		}
		randomAuf();
	}

	public void applyCase(Case c) {
		String toDisect = c.algorithm;
		toDisect =toDisect.replaceAll(" ", "");
		toDisect = toDisect.replaceAll("[()]", "");
		while (! toDisect.isEmpty()) {
			int len = toDisect.length();
			char last = toDisect.charAt(len-1);
			if (last == '2') {
				last = toDisect.charAt(len-2);
				switch (last) {
				case 'F': moveF(2); break;
				case 'U': moveU(2); break;
				case 'R': moveR(2); break;
				case 'L': moveL(2); break;
				case 'D': moveD(2); break;
				case 'B': moveB(2); break;
				case 'y': rotateY(2); break;
				case 'z': rotateZ(2); break;
				case 'x': rotateX(2); break;
				}
				toDisect = toDisect.substring(0, len-2);
			} else if (last == '\'' || last=='’') {
				last = toDisect.charAt(len-2);
				switch (last) {
				case 'F': moveF(1); break;
				case 'U': moveU(1); break;
				case 'R': moveR(1); break;
				case 'L': moveL(1); break;
				case 'D': moveD(1); break;
				case 'B': moveB(1); break;
				case 'y': rotateY(1); break;
				case 'z': rotateZ(1); break;
				case 'x': rotateX(1); break;
				}
				toDisect = toDisect.substring(0, len-2);
			} else {
				switch (last) {
				case 'F': moveF(3); break;
				case 'U': moveU(3); break;
				case 'R': moveR(3); break;
				case 'L': moveL(3); break;
				case 'D': moveD(3); break;
				case 'B': moveB(3); break;
				case 'y': rotateY(3); break;
				case 'z': rotateZ(3); break;
				case 'x': rotateX(3); break;
				}
				toDisect = toDisect.substring(0, len-1);
			}
		}
	}

	public void move(char move) {
		if (! solved()) {
			switch (move) {
			case 'j': moveU(1); break;
			case 'f': moveU(3); break;
			case 'h': moveF(1); break;
			case 'g': moveF(3); break;
			case 'i': moveR(1); break;
			case 'k': moveR(3); break;
			case 'e': moveL(3); break;
			case 'd': moveL(1); break;
			case 'r':
			case 't':
			case 'y':
			case 'u': rotateX(1); break;
			case 'c':
			case 'b':
			case 'n':
			case 'm': rotateX(3); break;
			case 'a': rotateY(3); break;
			case ';': rotateY(1); break;
			case 'q': rotateZ(3); break;
			case 'p': rotateZ(1); break;
			case ' ': 
			} 
		}
	}

	private void moveU(int times) {
		for (int i = 0; i < times; i++) {
			int temp0 = front[0];
			int temp1 = front[1];
			front[0] = right[0];
			front[1] = right[1];
			right[0] = back[0];
			right[1] = back[1];
			back[0] = left[0];
			back[1] = left[1];
			left[0] = temp0;
			left[1] = temp1;

			temp0 = up[0];
			up[0] = up[3];
			up[3] = up[2];
			up[2] = up[1];
			up[1] = temp0;
		}
	}
	private void moveF(int times) {
		for (int i = 0; i < times; i++) {
			int temp2 = up[2];
			int temp3 = up[3];
			up[2] = left[1];
			up[3] = left[2];
			left[1] = down[0];
			left[2] = down[1];
			down[0] = right[3];
			down[1] = right[0];
			right[3] = temp2;
			right[0] = temp3;

			temp2 = front[0];
			front[0] = front[3];
			front[3] = front[2];
			front[2] = front[1];
			front[1] = temp2;
		}
	}
	private void moveR(int times) {
		for (int i = 0; i < times; i++) {
			int temp1 = up[1];
			int temp2 = up[2];
			up[1] = front[1];
			up[2] = front[2];
			front[1] = down[1];
			front[2] = down[2];
			down[1] = back[3];
			down[2] = back[0];
			back[0] = temp2;
			back[3] = temp1;

			temp2 = right[0];
			right[0] = right[3];
			right[3] = right[2];
			right[2] = right[1];
			right[1] = temp2;
		}
	}
	private void moveL(int times) {
		for (int i = 0; i < times; i++) {
			moveR(1);
			rotateX(3);
		}
	}

	private void moveB(int times) {
		for (int i = 0; i < times; i++) {
			moveF(1);
			rotateZ(3);
		}
	}

	private void moveD(int times) {
		for (int i = 0; i < times; i++) {
			moveU(1);
			rotateY(3);
		}
	}
	private void rotateX(int times) {
		for (int i = 0; i < times; i++) {
			int[] temp = up;
			up = front;
			front = down;
			//			down = back;
			down = new int[4];
			down[0] = back[2];
			down[1] = back[3];
			down[2] = back[0];
			down[3] = back[1];
			//			back = temp;
			back = new int[4];
			back[0] = temp[2];
			back[1] = temp[3];
			back[2] = temp[0];
			back[3] = temp[1];
			int temp0 = left[0];
			left[0] = left[1];
			left[1] = left[2];
			left[2] = left[3];
			left[3] = temp0;
			temp0 = right[0];
			right[0] = right[3];
			right[3] = right[2];
			right[2] = right[1];
			right[1] = temp0;
		}
	}
	private void rotateY(int times) {
		for (int i = 0; i < times; i++) {
			int[] temp = front;
			front = right;
			right = back;
			back = left;
			left = temp;
			int temp0 = down[0];
			down[0] = down[1];
			down[1] = down[2];
			down[2] = down[3];
			down[3] = temp0;
			temp0 = up[0];
			up[0] = up[3];
			up[3] = up[2];
			up[2] = up[1];
			up[1] = temp0;
		}
	}
	public void rotateZ(int times) {
		for (int i = 0; i < times; i++) {
			int[] temp = up;
			//			up = left;
			up = new int[4];
			up[0] = left[3];
			up[1] = left[0];
			up[2] = left[1];
			up[3] = left[2];
			//			left = down;
			left = new int[4];
			left[0] = down[3];
			left[1] = down[0];
			left[2] = down[1];
			left[3] = down[2];
			//			down = right;
			down = new int[4];
			down[0] = right[3];
			down[1] = right[0];
			down[2] = right[1];
			down[3] = right[2];
			//			right = temp;
			right = new int[4];
			right[0] = temp[3];
			right[1] = temp[0];
			right[2] = temp[1];
			right[3] = temp[2];
			int temp0 = front[0];
			front[0] = front[3];
			front[3] = front[2];
			front[2] = front[1];
			front[1] = temp0;
			temp0 = back[0];
			back[0] = back[1];
			back[1] = back[2];
			back[2] = back[3];
			back[3] = temp0;
		}
	}

	public void randomAuf() {
		moveU((new Random()).nextInt(4));
	}
	public void reset() {
		front = new int[] {GREEN, GREEN, GREEN, GREEN};
		back = new int[] {BLUE, BLUE, BLUE, BLUE};
		left = new int[] {ORANGE, ORANGE, ORANGE, ORANGE};
		right = new int[] {RED, RED, RED, RED};
		up = new int[] {WHITE, WHITE, WHITE, WHITE};
		down = new int[] {YELLOW, YELLOW, YELLOW, YELLOW};
	}

	public void print() {
		System.out.println("  " + chars[up[0]] + chars[up[1]] + "  ");
		System.out.println("  " + chars[up[3]] + chars[up[2]] + "  ");
		System.out.println(chars[left[0]] + chars[left[1]] + chars[front[0]] + chars[front[1]] + chars[right[0]] + chars[right[1]] + chars[back[0]] + chars[back[1]]);
		System.out.println(chars[left[3]] + chars[left[2]] + chars[front[3]] + chars[front[2]] + chars[right[3]] + chars[right[2]] + chars[back[3]] + chars[back[2]]);
		System.out.println("  " + chars[down[0]] + chars[down[1]] + "  ");
		System.out.println("  " + chars[down[3]] + chars[down[2]] + "  ");
	}

	public boolean solved() {
		return (up[0] == up[1] && up[1] == up[2] && up[2] == up[3]
				&& front[0] == front[1] && front[1] == front[2] && front[2] == front[3]
						&& down[0] == down[1] && down[1] == down[2] && down[2] == down[3]
								&& back[0] == back[1] && back[1] == back[2] && back[2] == back[3]
										&& left[0] == left[1] && left[1] == left[2] && left[2] == left[3]
												&& right[0] == right[1] && right[1] == right[2] && right[2] == right[3]);
	}

}

import java.util.ArrayList;
import java.util.Observable;


public class Model extends Observable {	
	// the data in the model, just a counter
	private int counter;	
	// all views of this model
	private int turn, thisStep;
	public boolean gaming,illegal,tie,tourView;
	public int[] fields = new int[9];
	public int w1,w2,w3;
	public int owin, xwin;	
	
	Model() {
		for (int i = 0; i < 9; i++) {
			fields[i] = 0;
		}
		turn = 0;
		w1 = -1;w2 = -1;w3 = -1;
		owin = 0;
		xwin = 0;
	}

	public void start() {
		gaming = true;
		thisStep = 0;
		tie = false;
		w1 = -1;w2 = -1;w3 = -1;
		setChanged();
		notifyObservers();
	}

	public void restart() {
		for (int i = 0; i < 9; i++) {
			fields[i] = 0;
		}
		if (turn == -1 && tie == false) xwin++;
		if (turn == 1 && tie == false) owin++;

		turn = 0;
		gaming = false;
		thisStep = 0;
		tie = false;
		w1 = -1;w2 = -1;w3 = -1;
		setChanged();
		notifyObservers();
	}	

	public void nextButton(int i) {
		if (fields[i] == 0) {
			fields[i] = turn;
			turn = turn * -1;
			thisStep++;
		}
		else illegal = true;

		if (isWin() == 0 && thisStep == 9) {
			tie = true;
		}
		setChanged();
		notifyObservers();
	}

	public int isWin() {
		if (fields[0] == fields[1] && fields[1] == fields[2] && fields[0] != 0) {
			w1 = 0;w2 = 1;w3 = 2;
			return fields[0];
		}
		if (fields[3] == fields[4] && fields[4] == fields[5] && fields[3] != 0) {
			w1 = 3;w2 = 4;w3 = 5;
			return fields[3];
		}
		if (fields[6] == fields[7] && fields[7] == fields[8] && fields[6] != 0) {
			w1 = 6;w2 = 7;w3 = 8;
			return fields[6];
		}
		if (fields[0] == fields[3] && fields[3] == fields[6] && fields[0] != 0) {
			w1 = 0;w2 = 3;w3 = 6;
			return fields[0];
		}
		if (fields[1] == fields[4] && fields[4] == fields[7] && fields[1] != 0) {
			w1 = 1;w2 = 4;w3 = 7;
			return fields[1];
		}
		if (fields[2] == fields[5] && fields[5] == fields[8] && fields[2] != 0) {
			w1 = 2;w2 = 5;w3 = 8;
			return fields[2];
		}
		if (fields[0] == fields[4] && fields[4] == fields[8] && fields[0] != 0) {
			w1 = 0;w2 = 4;w3 = 8;
			return fields[0];
		}
		if (fields[2] == fields[4] && fields[4] == fields[6] && fields[2] != 0) {
			w1 = 2;w2 = 4;w3 = 6;
			return fields[2];
		}
		else return 0;
	}

	public int getTurn() {
		return turn;
	}

	public int getThisStep() {
		return thisStep;
	}

	public void xWantToStart() {
		turn = 1;
		start();
	}

	public void oWantToStart() {
		turn = -1;
		start();
	}

	public void displayTourView(boolean b) {
		tourView = b;
		setChanged();
		notifyObservers();
	}

}

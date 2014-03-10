// HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;	

public class Main{

	public static void main(String[] args){	
		JFrame frame = new JFrame("Tic-Tac-Toe");
		
		// create Model and initialize it
		Model model = new Model();
		
		// create View, tell it about model and controller
		ToolbarView view = new ToolbarView(model);
		// tell Model about View. 
		model.addObserver(view);

		PlayerView view3 = new PlayerView(model);
		// tell Model about View. 
		model.addObserver(view3);
		
		// create second view ...
		BoardView view2 = new BoardView(model);
		model.addObserver(view2);

		TournamentView view4 = new TournamentView(model);
		model.addObserver(view4);

		model.notifyObservers();
		
		// create the window
		JPanel p = new JPanel();//new GridLayout(2,1));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		frame.getContentPane().add(p);
		p.add(view);
		p.add(view2);
		p.add(view3);
		p.add(view4);
		
	//	frame.setPreferredSize(new Dimension(400,400));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} 
}


import java.lang.String;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;	
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class BoardView extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	private ArrayList<JButton> buttons = new ArrayList<JButton>(9);

	BoardView(Model model_) {
		// create UI
		//setBackground(Color.WHITE);
		//setLayout(new FlowLayout(FlowLayout.LEFT));
		super();
		// set the model
		model = model_;
		// setup the event to go to the controller
//		addMouseListener(new MouseClickedListener());
//		addActionListener(controller);

		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new FlowLayout());
		
		Container box = new Container();

		// Set the layout strategy to a grid with 3 rows and 3 columns.
		GridLayout strategy = new GridLayout(3,3,3,3);
		
		box.setLayout(strategy);
		box.setMaximumSize(new Dimension(240, 240));	
		box.setPreferredSize(new Dimension(200, 200));
		// Add the components 
		// (always added in left-to-right, top-to-bottom reading order )
		for (int i = 0; i < 9; i++ ) {
			JButton button = new JButton("");
			button.setFont(new Font("SansSerif", Font.PLAIN, 35));
			button.setBackground(Color.WHITE);
			box.add(button);
			buttons.add(button);
			button.setEnabled(false);
			button.addActionListener(new indexActionListener(i) {
				public void actionPerformed(ActionEvent e) {
					model.nextButton(index);
				}
			});
		}

		this.add(box);
		this.setMinimumSize(new Dimension(240, 220));	
		this.setPreferredSize(new Dimension(240, 260));


		
	}


	class indexActionListener implements ActionListener{
   		public final int index;

    		indexActionListener(int i){
        		super();
        		this.index = i;
   		}
		public void actionPerformed(ActionEvent e) {
		}
	}


	// IView interface
	@Override
	public void update(Observable arg0, Object arg1) {
		String s = "";
		if (model.gaming == false ||  model.tie == true) {
			for (int i = 0; i < 9; i++) {
				int f = model.fields[i];				
				if (f == 1) s = "X";
				if (f == -1) s = "O";
				if (f == 0) s = "";
				buttons.get(i).setText(s);
				buttons.get(i).setEnabled(false);
				buttons.get(i).setFont(new Font("SansSerif", Font.PLAIN, 35));
			}
		}
		else if (model.isWin() != 0) {
			for (int i = 0; i < 9; i++) {
				int f = model.fields[i];				
				if (f == 1) s = "X";
				if (f == -1) s = "O";
				if (f == 0) s = "";
				buttons.get(i).setText(s);
				buttons.get(i).setEnabled(false);
				buttons.get(i).setFont(new Font("SansSerif", Font.PLAIN, 35));
			}
			buttons.get(model.w1).setFont(new Font("SansSerif", Font.BOLD, 35));
			buttons.get(model.w2).setFont(new Font("SansSerif", Font.BOLD, 35));
			buttons.get(model.w3).setFont(new Font("SansSerif", Font.BOLD, 35));
		}
		else {
			for (int i = 0; i < 9; i++) {
				int f = model.fields[i];				
				if (f == 1) s = "X";
				if (f == -1) s = "O";
				if (f == 0) s = "";
				buttons.get(i).setText(s);
				buttons.get(i).setEnabled(true);
				buttons.get(i).setFont(new Font("SansSerif", Font.PLAIN, 35));
			}

		}
	}
}


import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;	
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;


class ToolbarView extends JPanel implements Observer {

	// the view's main user interface
	private JButton button; 
	private JButton viewButton; 
	private JLabel label;
	
	// the model that this view is showing
	private Model model;
	
	ToolbarView(Model model_) {
		// create the view UI
		button = new JButton("New");
		button.setMaximumSize(new Dimension(120, 20));
		button.setPreferredSize(new Dimension(80, 20));
		button.setFocusPainted(false);
	
		label = new JLabel("Please select player");
		label.setPreferredSize(new Dimension(150, 20));
		label.setHorizontalAlignment( SwingConstants.CENTER );
		label.setBackground(Color.WHITE);		
		label.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		viewButton = new JButton("+");
		viewButton.setMaximumSize(new Dimension(50, 20));
		viewButton.setPreferredSize(new Dimension(50, 20));
		viewButton.setFocusPainted(false);

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.setPreferredSize(new Dimension(300, 40));
		this.setMinimumSize(new Dimension(300, 40));

		this.add(Box.createRigidArea(new Dimension(20, 30)));
		this.add(button);

		this.add(label);
		this.add(Box.createVerticalStrut(30));	
		this.add(viewButton);
		this.add(Box.createRigidArea(new Dimension(20, 30)));	
		// set the model 
		model = model_;
		// setup the event to go to the controller

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.restart();
			}
		});	

		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				model.displayTourView(! model.tourView);
			}
		});

	} 

	@Override
	public void update(Observable arg0, Object arg1) {
		viewButton.setText((model.tourView)?"-":"+");
		if (model.gaming == false) {
			label.setText( "Please select player" );
		}
		else if (model.isWin() != 0 ) {
			label.setText( ( (model.getTurn() == 1)?"O":"X" )+ " wons");
		}
		else if (model.illegal == true) {
			label.setText( "Illegal move");
			model.illegal = false;
		}
		else if (model.tie == true) {
			label.setText( "Game over, no winner");
		}
		else if (model.getThisStep() == 0 ) {
			label.setText( "Make first move");
		}	
		else if (model.getThisStep() > 0 ) {
			label.setText( "Move "+ model.getThisStep());
		}
	}
} 

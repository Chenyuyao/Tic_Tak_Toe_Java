
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


class PlayerView extends JPanel implements Observer {

	// the view's main user interface
	private JButton buttonX;
	private JButton buttonO;
	
	// the model that this view is showing
	private Model model;
	
	PlayerView(Model model_) {
		
	

		buttonX = new JButton("X");
		buttonX.setPreferredSize(new Dimension(32, 32));
		buttonX.setHorizontalAlignment( SwingConstants.CENTER );
		buttonX.setBackground(Color.LIGHT_GRAY);		
		buttonX.setFont(new Font("SansSerif", Font.PLAIN, 30));

		buttonO = new JButton("O");
		buttonO.setPreferredSize(new Dimension(32, 32));
		buttonO.setHorizontalAlignment( SwingConstants.CENTER );
		buttonO.setBackground(Color.LIGHT_GRAY);		
		buttonO.setFont(new Font("SansSerif", Font.PLAIN, 30));
		
		buttonX.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY ));
		buttonO.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY ));
		buttonX.setFocusPainted(false);
		buttonO.setFocusPainted(false);

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(Color.LIGHT_GRAY );
		this.setPreferredSize(new Dimension(300, 40));
		this.setMinimumSize(new Dimension(300, 40));

		this.add(Box.createVerticalStrut(30));	
		this.add(buttonX);
		this.add(Box.createRigidArea(new Dimension(70, 30)));
		this.add(buttonO);
		this.add(Box.createVerticalStrut(30));	

		// set the model 
		model = model_;
		// setup the event to go to the controller
		buttonX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.xWantToStart();
			}
		});	
		buttonO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.oWantToStart();
			}
		});	
	} 

	@Override
	public void update(Observable arg0, Object arg1) {
		buttonX.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY ));
		buttonO.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY ));
		if (model.getThisStep() == 0) {
			buttonX.setEnabled(true);
			buttonO.setEnabled(true);
			int turn = model.getTurn();
			if (turn == 1) {
				buttonX.setBorder(BorderFactory.createLineBorder(Color.black));
			} else if (turn == -1) {
				buttonO.setBorder(BorderFactory.createLineBorder(Color.black));
			}	
		}
		
		else if ( model.isWin() != 0 || model.tie == true ) {
			buttonX.setEnabled(false);
			buttonO.setEnabled(false);
		}

		else {
			int turn = model.getTurn();
			buttonX.setEnabled(false);
			buttonO.setEnabled(false);

			if (turn == 1) {
				buttonX.setBorder(BorderFactory.createLineBorder(Color.black));
			} else {
				buttonO.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		}

	}
} 

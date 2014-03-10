
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


class TournamentView extends JPanel implements Observer {

	// the view's main user interface
	private JLabel numX;
	private JLabel numO;
	private JLabel labelV;
	
	// the model that this view is showing
	private Model model;
	
	TournamentView(Model model_) {
		labelV = new JLabel(":");
		labelV.setPreferredSize(new Dimension(35, 35));
		labelV.setHorizontalAlignment( SwingConstants.CENTER );
		labelV.setBackground(Color.GRAY);		
		labelV.setFont(new Font("SansSerif", Font.PLAIN, 30));

		numX = new JLabel("0");
		numX.setPreferredSize(new Dimension(65, 30));
		numX.setHorizontalAlignment( SwingConstants.CENTER );
		numX.setBackground(Color.GRAY);		
		numX.setFont(new Font("SansSerif", Font.PLAIN, 30));

		numO = new JLabel("0");
		numO.setPreferredSize(new Dimension(65, 30));
		numO.setHorizontalAlignment( SwingConstants.CENTER );
		numO.setBackground(Color.GRAY);		
		numO.setFont(new Font("SansSerif", Font.PLAIN, 30));

		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.setPreferredSize(new Dimension(300, 40));
		this.setMinimumSize(new Dimension(300, 40));

		this.add(Box.createVerticalStrut(30));	
		this.add(numX);
		this.add(Box.createRigidArea(new Dimension(10, 10)));
		this.add(labelV);
		this.add(Box.createRigidArea(new Dimension(10, 10)));
		this.add(numO);
		this.add(Box.createVerticalStrut(30));	
		this.setVisible(false);

		// set the model 
		model = model_;

	} 


	@Override
	public void update(Observable arg0, Object arg1) {
		if (model.tourView == true ) {
			this.setVisible(true);
			numO.setText(Integer.toString(model.owin));
			numX.setText(Integer.toString(model.xwin));
		}
		else {
			this.setVisible(false);
		}
	}
} 

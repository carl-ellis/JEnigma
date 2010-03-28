package jenigma.ui;

import javax.swing.*;
import java.awt.*;

/** Defines a keyboard for a JEnigma GUI */
public class Keyboard extends JPanel {

	/** JEnigma GUI */
	private MachineGUI enigma = null;

	/** Layout manager */
	private GridBagLayout gbl = null;

	/** Gridbag constraints */
	private GridBagConstraints gbc = null;

	/** Key array */
	private Key[] keys = null;

	private static final String[] upperkeys 	= {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
	private static final String[] middlekeys 	= {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
	private static final String[] lowerkeys 	= {"Z", "X", "C", "V", "B", "N", "M"};


	/** Constructor for building the keyboard.
	  *
		* @param		engima		JEngima machine GUI
		*/
	public Keyboard(MachineGUI enigma){
		super();

		this.enigma = enigma;

		buildInterface();
	}

	/** Builds the interface */
	private void buildInterface(){

		gbl = new GridBagLayout();
  	gbc = new GridBagConstraints();

		keys = new Key[26];

		this.setLayout(gbl);

		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 30;
		gbc.weighty = 30;
		gbc.fill = GridBagConstraints.NONE;

		/* Upper key building */
		gbc.gridy = 0;
		for(int i=0; i<upperkeys.length; i++){

			gbc.gridx = i;
			keys[i] = new Key(upperkeys[i]);
			keys[i].addActionListener(enigma);
			keys[i].setActionCommand(upperkeys[i]);
			this.add(keys[i], gbc);
		}

		/** filler button */
		gbc.gridy = 1;
    gbc.gridx = 0;
		this.add(new Key(" "), gbc);

		/* Middle key building */
		for(int i=0; i<middlekeys.length; i++){

			gbc.gridx = i+1;
			keys[10+i] = new Key(middlekeys[i]);
			keys[10+i].addActionListener(enigma);
			keys[10+i].setActionCommand(middlekeys[i]);
			this.add(keys[10+i], gbc);
		}

		/** filler button */
		gbc.gridy = 2;
    gbc.gridx = 0;
		this.add(new Key(" "), gbc);
		for(int i=0; i<lowerkeys.length; i++){

			gbc.gridx = i+1;
			keys[19+i] = new Key(lowerkeys[i]);
			keys[19+i].addActionListener(enigma);
			keys[19+i].setActionCommand(lowerkeys[i]);
			this.add(keys[19+i], gbc);
		}
		gbc.gridx = lowerkeys.length + 1;
		this.add(new Key(" "), gbc);
		gbc.gridx = lowerkeys.length + 2;
		this.add(new Key(" "), gbc);
	}

}

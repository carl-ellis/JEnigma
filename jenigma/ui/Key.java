package jenigma.ui;

import javax.swing.*;
import java.awt.*;

/** Describes a key for a Keyboard in the enigma machine */
public class Key extends JButton {

	/** Value of the key */
	public String key = "";

	/** Constructor which builds a key.
    ( If the given key is " ", the key is disabled.
	  *
		* @param	key		Key of keyboard
		*/
	public Key(String key){
		super(key);
		this.key = key;
    this.setPreferredSize(new Dimension(48,40));

    if(key.equals(" ")){
      this.setText("");
      this.setEnabled(false);
    }
    
	}

	/** Returns the Key of this button.
		*
		* @return			Key of the keyboard.
		*/
	public String getKey(){
		return this.key;
	}
}

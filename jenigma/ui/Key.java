/*
Copyright 2010 Carl Ellis

This file is part of JEnigma.

Foobar is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

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

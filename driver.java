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

import jenigma.*;
import jenigma.ui.*;

public class driver {

	public static void setUIFont (javax.swing.plaf.FontUIResource f){
		//
		// sets the default font for all Swing components.
		// ex. 
		//  setUIFont (new javax.swing.plaf.FontUIResource
		//   ("Serif",Font.ITALIC,12));
		//
		java.util.Enumeration keys = javax.swing.UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = javax.swing.UIManager.get (key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				javax.swing.UIManager.put (key, f);
		}
	}


	public static void main(String args[]){

		setUIFont(new javax.swing.plaf.FontUIResource("Serif", java.awt.Font.PLAIN, 12));


		
		Enigma e = new Enigma(5, 4, 3, 1, 2, 3);
	/*	
		String plain = "hello world";
		
		String cipher = e.encode(plain);



		e = new Enigma(5, 4, 3, 1, 2, 3);
		String aplain = e.encode(cipher);

		System.out.println("PT: " + plain);
		System.out.println("CT: " + cipher);
		System.out.println("APT: " + aplain);
		/*
		Rotor r = new Rotor(5, 1, 5);
		System.out.println(r);
		*/

    new MachineGUI(e);

		}
}

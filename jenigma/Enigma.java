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

package jenigma;

/** Descibes an Enigma machine with 3 rotors and a reflector
  */
public class Enigma {

	/** Right rotor */
	public Rotor right = null;

	/** Middle rotor */
	public Rotor middle = null;

	/** Left rotor */
	public Rotor left = null;

	/** Reflector */
	public Reflector ref = null;

	/** Constructor for the enigma machine.
	  *
		* @param 	lRotor	Left rotor number
		* @param	mRotor 	Middle rotor number
		* @param  rRotor	Right rotor number
		* @param	lPos		Left rotor Key
		* @param	mPos		Middle rotor Key
		* @param	rPos		Right rotor Key
		*/
	public Enigma(int lRotor, int mRotor, int rRotor, int lPos, int mPos, int rPos){
		this.left = new Rotor(lRotor, 13, lPos);
		this.middle = new Rotor(mRotor, 5, mPos);
		this.right = new Rotor(rRotor, 1, rPos);
		this.ref = new Reflector();
	}

	/** Encodes one index through all three rotors and back again.
	  *
		* @param		index			index to be encoded
		*
		* @returns						encoded index
		*/
	private int cEncode(int index){

		/* One way */
		index = this.right.encode(index); 
		index = this.middle.encode(index); 
		index = this.left.encode(index); 

		/* Reflection */
		index = this.ref.encode(index); 

		/* Reverse direction */
		index = this.left.reverseEncode(index);
		index = this.middle.reverseEncode(index);
		index = this.right.reverseEncode(index);

		return index;
	}

	/** Encodes a message via the engima machine.
	  *
		* @param		plain`				Message String
		*
		* @return									Cipher text
		*/
	public String encode(String plain){

		/* Capitalise and remove spaces*/
		plain = plain.toUpperCase();
		String[] temp = plain.split(" ");
		plain = "";
		for(String t : temp){
			plain+=t;
		}

		/* encode each character */
    char[] plaindata = plain.toCharArray();
		char[] cipher = new char[plaindata.length];

		for(int i=0; i<plaindata.length; i++){
			cipher[i] = (char)(this.cEncode((int)(plaindata[i] -65)) + 65);
		}

		return new String(cipher);
	}

	public String toString(){

		return "R:" + right.getName() + ":" + right.getPosition() + " M:" + middle.getName() + ":" + middle.getPosition()
      + " L:" + left.getName() + ":" + left.getPosition();
	}

  public void reset(){
    right.reset();
    middle.reset();
    left.reset();
  }

  public void setPositions(int lp, int mp, int rp){
    right.setPosition(lp);
    middle.setPosition(mp);
    left.setPosition(rp);
  }
    
}



package jenigma;

/** Descibes an Enigma machine with 3 rotors and a reflector
  */
public class Enigma {

	/** Right rotor */
	Rotor right = null;

	/** Middle rotor */
	Rotor middle = null;

	/** Left rotor */
	Rotor left = null;

	/** Reflector */
	Reflector ref = null;

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

		return "R:" + right.getPosition() + " M: " + middle.getPosition() + " L: " + left.getPosition();
	}
}



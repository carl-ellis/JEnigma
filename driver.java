import jenigma.*;

public class driver {

	public static void main(String args[]){
		Enigma e = new Enigma(5, 4, 3, 1, 2, 3);
		
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
		}
}

package jenigma;

/** Describes a reflector rotor in the enigma machine.
  */
public class Reflector extends Rotor{

	/** Constructor */
	public Reflector(){
		super();

		this.buildPermutations();
	}

	/** Builds the reflector permutations */
	private void buildPermutations(){

		for(int i=0;i<NUMBER_OF_LETTERS;i++){
			this.permutations[i] = NUMBER_OF_LETTERS - (i+1);
		}
	}

	/** Reflects the data
	  *
		* @param	index		Index of value to be reflected
		*
		* @return					Encoded index
	  */
	@Override
	public int encode(int index){
		return this.permutations[index];
	}

}

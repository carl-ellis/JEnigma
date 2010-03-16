package jenigma;

/** Describes a Rotor in an engima machine */
public class Rotor {

	/** Number of letters */
	protected static final int NUMBER_OF_LETTERS = 26;

	/** Decribes the transformations of the cog */
	protected int[] permutations = null;

	/** Decribes the reverse transformations of the cog */
	private int[] rPermutations = null;

	/** The current position of the cog */
	private int position = 0;

	/** The number of uses before a spin */
	private int spin = 1;

	/** The current number of entries */
	private int cEntries = 0;

	/** The random seed used to create the permutations */
	private int seed = 1;

	/** Hidden constructor for subclasses */
	protected Rotor(){
		this.permutations = new int[NUMBER_OF_LETTERS];
		this.rPermutations = new int[NUMBER_OF_LETTERS];
	}

	/** Constructor which builds the rotor and its properties.
	  *
	  * @param		seed						Seed to initialise the permutations
		* @param		spin						Number of translations before a spin
		* @param		position				Initial position of the rotor
		*/
	public Rotor(int seed, int spin, int position){

		this.seed = seed;
		this.spin = spin;
		this.position = position;
		this.permutations = new int[NUMBER_OF_LETTERS];
		this.rPermutations = new int[NUMBER_OF_LETTERS];

		this.buildPermutations();
	}

	/** Builds permutations of the rotor.
	  */
  private void buildPermutations(){

		java.util.Random r = new java.util.Random();
		r.setSeed(this.seed);

		/*temp set to keep track of chosen links*/
		int[] temp = new int[NUMBER_OF_LETTERS];

		for(int i=0;i<NUMBER_OF_LETTERS;i++){
			
			/* Find a spare permutation */
			int delta = (int)(r.nextDouble() * NUMBER_OF_LETTERS);
			while(temp[(delta + i)%NUMBER_OF_LETTERS] != 0){
				delta = (int)(r.nextDouble() * NUMBER_OF_LETTERS);
			}

			/* Assign permutation */
			this.permutations[i] = (delta + i)%NUMBER_OF_LETTERS;
			temp[(delta+i)%NUMBER_OF_LETTERS] = 1;
		}

		for(int i=0;i<NUMBER_OF_LETTERS;i++){
			int a = this.permutations[i];
			int b = i;
			this.rPermutations[a] = b;
		}
	}

	/** Outputs the encoded integer
	  *
		* @param		index			index to be encoded
		*
		* @return							transformed index
		*/
	private int transform(int index){
		return this.permutations[(position + index)%NUMBER_OF_LETTERS];
	}

	/** Encodes and spins the wheel if needs be.
	  *
		* @param		index			index to be encoded
		*
		* @return							encoded index
		*/
	public int encode(int index){
		if(++this.cEntries >= this.spin){
			this.cEntries = 0;
			this.position+=1;
			this.position%=NUMBER_OF_LETTERS;
		}
		int in = this.transform(index);
		return in;
	}

	/** Gives the reverse routing encoding.
	  * Does not spin the cogs.
		*
		* @param	index				index to be encoded.
		*
		* @return							encoded index
		*/
	public int reverseEncode(int index){
		return (this.rPermutations[index] - position < 0) ? NUMBER_OF_LETTERS + (this.rPermutations[index] - position) : this.rPermutations[index] - position;
	}

	public String toString(){
		String output = "";
		for(int i=0;i<NUMBER_OF_LETTERS;i++){
			output += i + " -> " + this.transform(i) + " || " + i + " -> " + this.reverseEncode(i) + '\n';
		}
		return output;
	}

	public int getPosition(){
		return this.position;
	}
}




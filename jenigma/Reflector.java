/*
Copyright 2010 Carl Ellis

This file is part of JEnigma.

JEnigma is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

JEnigma is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with JEnigma.  If not, see <http://www.gnu.org/licenses/>.
*/

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

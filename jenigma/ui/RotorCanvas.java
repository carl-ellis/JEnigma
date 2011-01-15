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

import java.awt.*;
import java.awt.geom.*;
import jenigma.*;
import java.util.*;

/** Gives a graphical view of the rotors */
public class RotorCanvas extends Canvas {

	/** Enigma Machine describing */
	private Enigma enig = null;

	private Rotor right = null;
	private Rotor middle = null;
	private Rotor left = null;

	private static final double MAGIC = Rotor.NUMBER_OF_LETTERS / (Math.PI * 2); 

	/** Constructor.
	 * Takes an enigma machine and represents it.
	 *
	 * @param  enig      Enigma machine
	 */
	public RotorCanvas(Enigma enig){
		super();
		this.enig = enig;
		this.right = enig.right;
		this.middle = enig.middle;
		this.left = enig.left;
		this.setPreferredSize(new Dimension(228, 120));
		this.setVisible(true);
	}

	/** Meat of the painting 
	 *
	 * @param   g     Graphics
	 */
	public void paint(Graphics g){

		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
																RenderingHints.VALUE_INTERPOLATION_BICUBIC);


		double canvasw = this.getWidth();
		double canvash = this.getHeight();
		double cogw = canvasw / 9.5;
		double cogh = canvash * 0.875;
		double cogcanvasgapy = canvash / 30;
		double cogcanvasgapx = cogw;
		double coglinkspacery = cogh / 52.5;
		double linkspacer = (cogh - (2 * coglinkspacery)) / Rotor.NUMBER_OF_LETTERS;
		double reflectorgap = cogw / (Rotor.NUMBER_OF_LETTERS + 1);

		
		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle2D.Double(0.0, 0.0, canvasw, canvash));
		g2d.setColor(Color.BLACK);

		/* Reflector */
		drawRotor(g2d, this.enig.ref, cogw, cogh, cogcanvasgapy, cogcanvasgapx, coglinkspacery, linkspacer);
		/* rotors */
		drawRotor(g2d, this.enig.left, cogw, cogh, cogcanvasgapy, 3*cogcanvasgapx, coglinkspacery, linkspacer);
		drawRotor(g2d, this.enig.middle, cogw, cogh, cogcanvasgapy, 5*cogcanvasgapx, coglinkspacery, linkspacer);
		drawRotor(g2d, this.enig.right, cogw, cogh, cogcanvasgapy, 7*cogcanvasgapx, coglinkspacery, linkspacer);
	 // System.out.println(this.enig.right.getPosition());

		for(int i=0; i<Rotor.NUMBER_OF_LETTERS; i++){
			double y1 = cogcanvasgapy + coglinkspacery + (cogh - 2 * coglinkspacery) * magicSin((i * linkspacer));
			g2d.setColor((magicTan(i * 4) > 0) ? Color.LIGHT_GRAY : Color.BLACK);
			g2d.draw(new Line2D.Double( cogw*2, y1, cogw*3, y1));
			g2d.draw(new Line2D.Double( cogw*4, y1, cogw*5, y1));
			g2d.draw(new Line2D.Double( cogw*6, y1, cogw*7, y1));
			g2d.draw(new Line2D.Double( cogw*8, y1, cogw*9, y1));
		}
	}


	/** Works out the location of the coordinate on the circular rotor.
	 * 
	 * @param	i						position on 2d plane
	 *
	 * @return							position on 3d projection
	 */
	public double magicSin(double i){
		double sdk = ((Math.sin(i/ MAGIC) + 1) / 2);
		return sdk;
	}

	/** Used for z buffer calculation.
	 * 
	 * @param	i						position on 2d plane
	 *
	 * @param							if >= 0 then on viewable side of cog
	 */
	public double magicTan(double i){
		double mag = Math.tan(i / MAGIC);
		return mag;
	}

	private void drawRotor(Graphics2D g2d, Rotor r, double cogw, double cogh, double cogcanvasgapy, double cogcanvasgapx, double coglinkspacery, double linkspacer){
		if(r instanceof Reflector){
			drawReflector(g2d, r, cogw, cogh, cogcanvasgapy, cogcanvasgapx, coglinkspacery, linkspacer);
		} else {

			double y1 = 0;
			double y2 = 0;
			int ind = 0;
			int val = 0;

			int [] rperm = r.getPermutations();
			int rpos = r.getPosition();

			Integer[] rpermO = new Integer[Rotor.NUMBER_OF_LETTERS];
			for(int i=0;i<Rotor.NUMBER_OF_LETTERS;i++){rpermO[i] = i;}
			Arrays.sort(rpermO, 0, Rotor.NUMBER_OF_LETTERS, new ArrayComp(rperm, MAGIC)); 


			for(int i : rpermO){
				ind = i;
				val = (rperm[ind]);
				y1 = cogcanvasgapy + coglinkspacery + (cogh - 2*coglinkspacery) * ( magicSin(((ind + rpos)%rperm.length) * linkspacer));
				y2 = cogcanvasgapy + (cogh * magicSin(( ((val + rpos)%rperm.length) * linkspacer)));
				g2d.setColor((magicTan(val) <= 0) ? Color.LIGHT_GRAY : Color.BLACK);
				if(i == 1 && r == this.enig.right){
					g2d.setStroke(new BasicStroke(1.5f)); 
				} else {
					g2d.setStroke(new BasicStroke(1.0f)); 
				}
				g2d.draw(new Line2D.Double(cogcanvasgapx + cogw, y1 , cogcanvasgapx, y2));
			}

			g2d.setColor(Color.BLACK);
			g2d.draw(new Rectangle2D.Double(cogcanvasgapx, cogcanvasgapy, cogw, cogh));
		}
	}

				 private void drawReflector(Graphics2D g2d, Rotor r, double cogw, double cogh, double cogcanvasgapy, double cogcanvasgapx, double coglinkspacery, double linkspacer){
				 double reflectorgap = (cogw * 0.90) / (Rotor.NUMBER_OF_LETTERS);

			/* Get the permutations and the position of the rotor */
			int [] yperm = r.getPermutations();
			int ypos = r.getPosition();

			/* Sort the permutations for drawing */
			Integer[] ypermO = new Integer[Rotor.NUMBER_OF_LETTERS];
			for(int i=0;i<Rotor.NUMBER_OF_LETTERS;i++){ypermO[i] = i;}
			Arrays.sort(ypermO, 0, Rotor.NUMBER_OF_LETTERS, new ArrayComp(yperm, MAGIC)); 
			/* Drawing variable */
			double x1 = cogcanvasgapx;
			double x2 = cogcanvasgapx + cogw;
			int ind = 0;
			int val = 0;
			double x3 = 0;
			double y1 = 0;
			double y2 = 0;

			/* Draw the permutations */
			for(int i : ypermO){
				ind = ((i + ypos)%yperm.length);
				val = (yperm[(i + ypos)%yperm.length]);
				y1 = cogcanvasgapy + coglinkspacery + ((cogh - 2*coglinkspacery) * magicSin( ind * linkspacer));
				y2 = cogcanvasgapy + (cogh * magicSin(( val * linkspacer)));
				x3 = (cogcanvasgapx + (i * reflectorgap));
				//g2d.setColor((magicTan(val) <= 0) ? Color.LIGHT_GRAY : Color.BLACK);
				g2d.draw( new Line2D.Double(x2, y1, x3, y1));
				g2d.draw( new Line2D.Double(x3, y1, x3, y2));
				g2d.draw( new Line2D.Double(x3, y2, x2, y2));
			}

			/* Draw the rotor */
			g2d.setColor(Color.BLACK);
			g2d.draw(new Rectangle2D.Double(cogcanvasgapx, cogcanvasgapy, cogw, cogh));


	}

	private class ArrayComp implements Comparator {

		private int[] original = null;
		private double magic = 0;

		public ArrayComp(int[] original, double magic){
			this.original = original;
			this.magic = magic;
		}

		public int compare(Object o1, Object o2){
			double a=0.0, b =0.0;
			if(o1 instanceof Integer && o2 instanceof Integer){
				a = magicTan(original[(Integer)o1]);
				b = magicTan(original[(Integer)o2]);
			}
			return (a < b) ? -1 : (a == b) ? 0 : 1;
		}

		private double magicTan(int i){
			double mag = Math.tan(i / MAGIC);
			return mag;
		}

	}

}

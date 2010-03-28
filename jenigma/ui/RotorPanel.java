package jenigma.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import jenigma.*;

/** Describes a Rotor panel for the JEnigma GUI */
public class RotorPanel extends JPanel implements ActionListener {

  /** Reference to its owning Machine GUI */
  private MachineGUI machine = null;

  /** UI Stuff */
  private JLabel reflectorName = null;
  private JLabel leftName = null;
  private JLabel middleName = null;
  private JLabel rightName = null;

  private JButton setButton = null;
  private JTextField leftPos = null;
  private JTextField middlePos = null;
  private JTextField rightPos = null;

  private GridBagLayout gbl = null;
  private GridBagConstraints gbc = null;

	private RotorCanvas rot = null;

  /** Default constuctor.
   *
   * @param  machine       MachineGUI which rotor panel is in.
   */
  public RotorPanel(MachineGUI machine){
    super();

    this.machine = machine;
    buildInterface();
  }

  /** Builds the interface */
  private void buildInterface(){

    gbl = new GridBagLayout();
    gbc = new GridBagConstraints();

    reflectorName = new JLabel("R");
    reflectorName.setPreferredSize(new Dimension(24, 120));
    reflectorName.setBorder(BorderFactory.createLineBorder(Color.black));
    leftName = new JLabel("1");
    leftName.setPreferredSize(new Dimension(24, 120));
    leftName.setBorder(BorderFactory.createLineBorder(Color.black));
    middleName = new JLabel("1");
    middleName.setPreferredSize(new Dimension(24, 120));
    middleName.setBorder(BorderFactory.createLineBorder(Color.black));
    rightName = new JLabel("1");
    rightName.setPreferredSize(new Dimension(24, 120));
    rightName.setBorder(BorderFactory.createLineBorder(Color.black));

    setButton = new JButton("Set");
    setButton.addActionListener(this);
    leftPos = new JTextField(2);
    middlePos = new JTextField(2);
    rightPos = new JTextField(2);

    JLabel spacer1 = new JLabel(" ");
    spacer1.setPreferredSize(new Dimension(24, 120));
    JLabel spacer2 = new JLabel(" ");
    spacer2.setPreferredSize(new Dimension(24, 120));
    JLabel spacer3 = new JLabel(" ");
    spacer3.setPreferredSize(new Dimension(24, 120));
    JLabel spacer4 = new JLabel(" ");
    spacer4.setPreferredSize(new Dimension(24, 120));

		this.rot = new RotorCanvas(this.machine.enig);


    this.setLayout(gbl);

		/*
		//Static View
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.weightx = 30;
		gbc.weighty = 30;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;

    gbc.gridy = 0;
    gbc.gridx = 0;
    this.add(reflectorName, gbc);
    gbc.gridx = 1;
    this.add(spacer1, gbc);
    gbc.gridx = 2;
    this.add(leftName, gbc);
    gbc.gridx = 3;
    this.add(spacer2, gbc);
    gbc.gridx = 4;
    this.add(middleName, gbc);
    gbc.gridx = 5;
    this.add(spacer3, gbc);
    gbc.gridx = 6;
    this.add(rightName, gbc);
    gbc.gridx = 7;
    this.add(spacer4, gbc);
		*/

		//Dynamic View
		gbc.gridwidth = 6;
		gbc.gridheight = 3;
		gbc.weightx = 30;
		gbc.weighty = 30;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
    gbc.gridy = 0;
    gbc.gridx = 0;
    this.add(this.rot, gbc);

		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
    gbc.gridy = 3;
    gbc.gridx = 0;
    this.add(setButton, gbc);
		gbc.gridwidth = 1;
    gbc.gridx = 2;
    this.add(leftPos, gbc);
    gbc.gridx = 4;
    this.add(middlePos, gbc);
    gbc.gridx = 6;
    this.add(rightPos, gbc);
  }

  /** Updates the Rotor Panel information. */
  public void updateUI(Enigma enig){
    String data = enig.toString();
    String[] adata = data.split(" ");

    String[] l = adata[2].split(":");
    String[] m = adata[1].split(":");
    String[] r = adata[0].split(":");

    String ln = l[1]; 
    String lp = String.valueOf((char)(Integer.parseInt(l[2]) + 65));
    String mn = m[1];
    String mp = String.valueOf((char)(Integer.parseInt(m[2]) + 65));
    String rn = r[1];
    String rp = String.valueOf((char)(Integer.parseInt(r[2]) + 65));

    this.leftName.setText(ln);
    this.middleName.setText(mn);
    this.rightName.setText(rn);

    this.leftPos.setText(lp);
    this.middlePos.setText(mp);
    this.rightPos.setText(rp);

		this.rot.repaint();
  }

	public void actionPerformed(ActionEvent ae){
    int rp = (int)(this.rightPos.getText().toUpperCase().charAt(0) - 65);
    int mp = (int)(this.middlePos.getText().toUpperCase().charAt(0) - 65);
    int lp = (int)(this.leftPos.getText().toUpperCase().charAt(0) - 65);

    System.out.println(rp);

    this.machine.enig.setPositions(rp, mp, lp);
    updateUI(this.machine.enig);
	}


}

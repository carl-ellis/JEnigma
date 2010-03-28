package jenigma.ui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import jenigma.*;

/** Main machine GUI */
public class MachineGUI extends JFrame implements ActionListener, DocumentListener {

	/** Keyboard UI */
	private Keyboard kb = null;

  /** Rotor Panel */
  private RotorPanel rp = null;

  /** Output Panel */
  private JTextArea out = null;
  
  /** Input Panel */
  private JTextArea in = null;

  /** Upper panel */
  private JPanel upperPanel = null;

  public Enigma enig = null;

	/** Constructor */
	public MachineGUI(Enigma enig){

		super("JEnigma");

    this.enig = enig;

		buildInterface();
	}

	/** Builds the interface */
	private void buildInterface(){

    
		kb = new Keyboard(this);
    kb.setBorder(BorderFactory.createLineBorder(Color.black));

    upperPanel = new JPanel();
    upperPanel.setLayout(new BorderLayout());

    out = new JTextArea(10, 25);
    out.setBorder(BorderFactory.createLineBorder(Color.black));
    out.setLineWrap(true);
    in = new JTextArea(4, 42);
    in.getDocument().addDocumentListener(this);
    in.setLineWrap(true);

    rp = new RotorPanel(this);
    rp.setBorder(BorderFactory.createLineBorder(Color.black));
    rp.updateUI(this.enig);

    upperPanel.add(rp, BorderLayout.WEST);
    upperPanel.add(out, BorderLayout.EAST);

    this.setLayout(new BorderLayout());

    this.add(upperPanel, BorderLayout.NORTH);
    this.add(in, BorderLayout.CENTER);
    this.add(kb, BorderLayout.SOUTH);
    
    // These lines will kill it
		/*
    JPanel blah = new JPanel();
		blah.setLayout(new BorderLayout());
    blah.add(new RotorCanvas(this.enig), BorderLayout.CENTER);
    this.setContentPane(blah);
    */

  
		this.setVisible(true);
		this.pack();
	}

  public Enigma getEnigma(){
    return this.enig;
  }

  @Override
	public void actionPerformed(ActionEvent ae){
      this.in.setText(this.in.getText() + ae.getActionCommand());
	}

  @Override
  public void insertUpdate(DocumentEvent de){
    
    /* Reset enigma */
    this.enig.reset();
    this.out.setText("");

    String k = this.in.getText();
    k = k.toUpperCase();
    k = k.replaceAll("[^A-Z]+", "");
    String cipher = this.enig.encode(k);
    this.out.setText(this.out.getText() + cipher);
    this.rp.updateUI(this.enig);
  }

  @Override
  public void changedUpdate(DocumentEvent de){
    insertUpdate(de);
  }

  @Override
  public void removeUpdate(DocumentEvent de){
    insertUpdate(de);
  }
}

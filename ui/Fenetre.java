package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Fenetre extends JFrame{
	 
	public Fenetre(String title){
	    this.setTitle("One For All : "+title);
	    this.setBounds(10, 10, 370, 600);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true); 
	    this.setResizable(false);
	}
}

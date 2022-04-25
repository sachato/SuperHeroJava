package ui;

import java.awt.Container;

import javax.swing.JProgressBar;

public class Loader extends Fenetre{
	JProgressBar prog = new JProgressBar();
	Container container = getRootPane();
	
	
	Thread t = new Thread() {
		public void run() {
	  		System.out.println("dans le run");
	  		for (int i = 0; i <100; i++) {
	  			prog.setValue(i);
	  			//System.out.println(i);
	  			try {
	  				this.sleep(100);
	  			} catch (InterruptedException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
	  		}
	  	}

	};
	
	
	
	
	public Loader(String title) {
		super(title);
		placer();
		this.setVisible(true);
		t.start();
		
	}
	private void placer() {
		prog.setBounds(100, 100, 200, 10);
		container.add(prog);
	}
}

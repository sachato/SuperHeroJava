package luncher;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import dao.SuperHeroDao;
import dao.VilleDao;
import model.SuperHero;
import model.Ville;
import test.progress;
import ui.Fenetre;
import ui.LoginFrame;

public class Luncher {
	boolean login = false;
	
	

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}
	
	public void afterLogin(SuperHero superhero) {
		
	}

	public static void main(String[] args) {
		Luncher luncher = new Luncher();
		}
		
		public Luncher() {
			LoginFrame frame = new LoginFrame();
//		    frame.setTitle("Login Form");
		    frame.setVisible(true);
		    frame.setBounds(10, 10, 370, 600);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setResizable(false);
		}
		
//		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
//		LoginFrame connexion = new LoginFrame();
//		connexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		connexion.setSize(size);
//		connexion.setVisible(true);
//	    System.out.println("bien dans le luncher");
	
}

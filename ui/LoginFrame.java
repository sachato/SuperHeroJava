package ui;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;

import dao.SuperHeroDao;
import model.SuperHero;
import services.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class LoginFrame extends Fenetre implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("Utilisateur");
    JLabel passwordLabel = new JLabel("Mot de passe");
    JLabel imageLab = new JLabel("");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Connection");
    JButton resetButton = new JButton("Reset");
    JButton changeMode = new JButton("Ville");
    JButton pasDeCompte = new JButton("Pas de Compte ?");
    JCheckBox showPassword = new JCheckBox("Montrer le mot de passe");
    JLabel titlefancy = new JLabel("SuperHero");
    Font fancy = new Font("verdana" ,Font.BOLD | Font.ITALIC,28);
    ImageIcon image = new ImageIcon("logoConnexion3.jpg");
    


    public LoginFrame() {
    	super("Connection");
    	this.setVisible(true);
    	this.setBounds(10, 10, 370, 600);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 120, 30);
        resetButton.setBounds(200, 300, 100, 30);
        changeMode.setBounds(200, 350, 100, 30);
        pasDeCompte.setBounds(50, 350, 120, 30);
        
        titlefancy.setFont(fancy);
        titlefancy.setBounds(50, 100, 200, 30);
        imageLab.setIcon(image);
        imageLab.setBounds(43, 0, 370, 100);

    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(titlefancy);
        container.add(changeMode);
        container.add(pasDeCompte);
        container.add(imageLab);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        changeMode.addActionListener(this);
        pasDeCompte.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            SuperHeroDao bdd = new SuperHeroDao();
            SuperHero hero = bdd.findByName(userText);
            if (hero != null) {
	            try {
					if (userText.equalsIgnoreCase(hero.getNom()) && Utils.validatePassword(pwdText, hero.getPassword())) {
					    SuperheroFrame superHeroFrame = new SuperheroFrame(hero);
					    this.setVisible(false);
					} else {
					    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
					}
				} catch (HeadlessException | NoSuchAlgorithmException | InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

            
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
        if(e.getSource() == changeMode) {
        	LoginFrameVille villeConnectionFrame = new LoginFrameVille();
            this.setVisible(false);
        }
        if(e.getSource() == pasDeCompte) {
        	CreateAccountFrameSuperHero createAccount = new CreateAccountFrameSuperHero();
        	this.setVisible(false);
        }
    }

}

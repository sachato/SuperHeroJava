package ui;
import javax.swing.*;

import dao.SuperHeroDao;
import dao.VilleDao;
import model.SuperHero;
import model.Ville;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrameVille extends Fenetre implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("Ville");
    JLabel imageLab = new JLabel("");
    JTextField userTextField = new JTextField();
    JButton loginButton = new JButton("Connection");
    JButton resetButton = new JButton("Reset");
    JButton pasDeCompte = new JButton("Ville inconnue?");
    JLabel titlefancy = new JLabel("Ville");
    Font fancy = new Font("verdana" ,Font.BOLD | Font.ITALIC,28);
    JButton changeMode = new JButton("SuperHero");
    ImageIcon image = new ImageIcon("logoConnexion3.jpg");
    


    public LoginFrameVille() {
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
        userTextField.setBounds(150, 150, 150, 30);
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
        container.add(userTextField);
        container.add(loginButton);
        container.add(resetButton);
        container.add(titlefancy);
        container.add(changeMode);
        container.add(imageLab);
        container.add(pasDeCompte);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
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
            VilleDao bdd = new VilleDao();
            Ville ville = bdd.findByName(userText);
            if (ville != null) {
            	VilleFrame superHeroFrame = new VilleFrame(ville);
                this.setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(this, "Ville inconue");
            }

        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        
        if(e.getSource() == changeMode) {
        	LoginFrame superHeroConnectionFrame = new LoginFrame();
            this.setVisible(false);
        }
        
        if(e.getSource() == pasDeCompte) {
        	CreateAccountFrameVille createAccount = new CreateAccountFrameVille();
        	this.setVisible(false);
        }
    }

}

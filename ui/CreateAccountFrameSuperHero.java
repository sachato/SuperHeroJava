package ui;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import dao.IncidentDao;
import dao.MaitriseDao;
import dao.PrefectureDao;
import dao.SuperHeroDao;
import dao.VilleDao;
import model.Incident;
import model.Maitrise;
import model.Prefecture;
import model.SuperHero;
import model.Ville;
import services.Geolocation;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class CreateAccountFrameSuperHero extends Fenetre implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("Utilisateur");
    JLabel passwordLabel = new JLabel("Mot de passe");
    JLabel villeLabel = new JLabel("Ville");
    JLabel telLabel = new JLabel("Téléphone");
    JTextField userTextField = new JTextField();
    JComboBox<String> villeTextField = null;
    JTextField telTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Inscription");
    JButton resetButton = new JButton("Reset");
    ImageIcon image = new ImageIcon("icon.png");
    JButton addIncident = new JButton("+");
    JCheckBox showPassword = new JCheckBox("Montrer le mot de passe");
    JComboBox incident1 = null;
    JComboBox incident2 = null;
    JComboBox incident3 = null;
    int nbIncident = 1;


    public CreateAccountFrameSuperHero() {
    	super("Creation de compte");
    	this.setVisible(true);
    	this.setBounds(10, 10, 400, 600);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	setComboBoxIncident();
    	setComboBoxPrefecture();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setVisibility();
    }

    public void setVisibility() {
    	incident1.setVisible(true);
    	incident2.setVisible(false);
    	incident3.setVisible(false);
    }
    
    public void setComboBoxIncident() {
    	IncidentDao bdd = new IncidentDao();
    	List<Incident> allIncident = bdd.findAll();
    	List<String> allIncidentName = new ArrayList<String>();
    	for (Incident incidento : allIncident) {
    		allIncidentName.add(incidento.getNom());
    	}
    	incident1 = new JComboBox(allIncidentName.toArray());
    	incident2 = new JComboBox(allIncidentName.toArray());
    	incident3 = new JComboBox(allIncidentName.toArray());
    }
    
    public void setComboBoxPrefecture() {
    	PrefectureDao bddPrefecture = new PrefectureDao();
    	List<Prefecture> allPrefectue = bddPrefecture.findAll();
    	List<String> allPrefectureName = new ArrayList<String>();
    	for (Prefecture prefecture : allPrefectue) {
    		allPrefectureName.add(prefecture.getNom());
    	}
    	villeTextField = new JComboBox(allPrefectureName.toArray());
    }
    
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 200, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 200, 150, 30);
        showPassword.setBounds(150, 230, 150, 30);
        villeLabel.setBounds(50, 270, 100, 30);
        villeTextField.setBounds(150, 270, 150, 30);
        telLabel.setBounds(50, 320, 100, 30);
        telTextField.setBounds(150, 320, 150, 30);
        incident1.setBounds(50, 370, 100, 30);
        incident2.setBounds(160, 370, 100, 30);
        incident3.setBounds(270, 370, 100, 30);
        addIncident.setBounds(160, 370, 30, 30);
        loginButton.setBounds(50, 420, 100, 30);
        resetButton.setBounds(200, 420, 100, 30);
        
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(incident1);
        container.add(incident2);
        container.add(incident3);
        container.add(addIncident);
        container.add(villeTextField);
        container.add(telTextField);
        container.add(telLabel);
        container.add(villeLabel);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        addIncident.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
        	Matcher matchertel;
        	Matcher matchername;
        	Pattern regTel = Pattern.compile("0[0-9]{9}");
        	Pattern regname = Pattern.compile("[a-zA-Z]{3,30}");
            String userText;
            String pwdText;
            String villeText;
            String telText;
            Prefecture ville;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            String hash = null;
			try {
				hash = Utils.generateStorngPasswordHash(pwdText);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            
            villeText = villeTextField.getSelectedItem().toString();
            telText = telTextField.getText();
            matchertel = regTel.matcher(telText);
            matchername = regname.matcher(userText);
            if(matchertel.matches() && matchername.matches()) {
            	SuperHeroDao bddSuperHero = new SuperHeroDao();
                PrefectureDao bddPrefecture = new PrefectureDao();
                IncidentDao bddIncident = new IncidentDao();
                MaitriseDao bddMaitrise = new MaitriseDao();
//                Geolocation location = new Geolocation();
//                io.ipgeolocation.api.Geolocation locatedVille = location.getGeolocation();
//                ville = bddVille.findByName(locatedVille.getCity());
                
                ville = bddPrefecture.findByName(villeText);
                if(ville == null) {
                	JOptionPane.showMessageDialog(this, "Prefecture inconue");
                }
                else {
                	try {
                		
						SuperHero hero = new SuperHero(0, userText, telText, hash.toString(), ville.getId());
                		if (hero != null) {
            	            bddSuperHero.add(hero);
            	            
                        }
                        else {
                            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                        }
                	}
                	catch (Exception e1) {
						e1.printStackTrace();
					}
                	
                }
                SuperHero hero = bddSuperHero.findByName(userText);
                switch (nbIncident) {
    			case 1: {
    				Incident premier = bddIncident.findByName(incident1.getSelectedItem().toString());
    				Maitrise go = new Maitrise(0, hero.getId(), premier.getId());
    				bddMaitrise.add(go);
    				break;
    			}
    			case 2: {
    				Incident premier = bddIncident.findByName(incident1.getSelectedItem().toString());
    				Maitrise go = new Maitrise(0, hero.getId(), premier.getId());
    				bddMaitrise.add(go);
    				Incident second = bddIncident.findByName(incident2.getSelectedItem().toString());
    				Maitrise go2 = new Maitrise(0, hero.getId(), second.getId());
    				bddMaitrise.add(go2);
    				break;
    			}
    			case 3: {
    				Incident premier = bddIncident.findByName(incident1.getSelectedItem().toString());
    				Maitrise go = new Maitrise(0, hero.getId(), premier.getId());
    				bddMaitrise.add(go);
    				Incident second = bddIncident.findByName(incident2.getSelectedItem().toString());
    				Maitrise go2 = new Maitrise(0, hero.getId(), second.getId());
    				bddMaitrise.add(go2);
    				Incident troisieme = bddIncident.findByName(incident3.getSelectedItem().toString());
    				Maitrise go3 = new Maitrise(0, hero.getId(), troisieme.getId());
    				bddMaitrise.add(go3);
    				break;
    			}
    			default:
    				throw new IllegalArgumentException("Unexpected value: " + nbIncident);
    			}
                LoginFrame loginFrame = new LoginFrame();
                this.setVisible(false);

            }
            else {
            	JOptionPane.showMessageDialog(this, "Il y a des erreurs dans le formulaire");
            }
            
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
            villeTextField.setSelectedIndex(0);
            telTextField.setText("");
            incident1.setVisible(true);
            incident2.setVisible(false);
            incident3.setVisible(false);
            addIncident.setBounds(160, 370, 30, 30);
			addIncident.setVisible(true);
			nbIncident=1;
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
        if(e.getSource() == addIncident) {
        	if (nbIncident == 3) {
        		JOptionPane.showMessageDialog(this, "Vous ne pouvez pas choisir plus de 3 incidents.");
        	}
        	else {
        		nbIncident+=1;
        		if (incident2.isVisible()) {
        			incident3.setVisible(true);
        			addIncident.setVisible(false);
        		}
        		else {
        			incident2.setVisible(true);
        			addIncident.setBounds(270, 370, 30, 30);
        		}
        	}
        }
    }

}

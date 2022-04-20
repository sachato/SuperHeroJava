package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.IncidentDao;
import dao.PrefectureDao;
import dao.VilleDao;
import model.Incident;
import model.Prefecture;
import model.Ville;

public class CreateAccountFrameVille extends Fenetre implements ActionListener {
	
	Container container = getContentPane();
    JLabel userLabel = new JLabel("Ville");
    JLabel passwordLabel = new JLabel("Prefecture");
    JTextField villeTextField = new JTextField();
    JComboBox prefecture = null;
    JButton enregistrer   = new JButton("Enregistrer");
    JButton retour = new JButton("Retour");
    
	public CreateAccountFrameVille() {
		super("Creation de ville");
		this.setVisible(true);
    	this.setBounds(10, 10, 400, 600);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	setComboBoxIncident();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
	}
	
	public void setComboBoxIncident() {
    	PrefectureDao bdd = new PrefectureDao();
    	List<Prefecture> allPrefecture = bdd.findAll();
    	List<String> allPrefectureName = new ArrayList<String>();
    	for (Prefecture prefecture : allPrefecture) {
    		allPrefectureName.add(prefecture.getNom());
    	}
    	prefecture = new JComboBox(allPrefectureName.toArray());
    }
	
	public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 200, 100, 30);
        villeTextField.setBounds(150, 150, 150, 30);
        prefecture.setBounds(150, 200, 100, 30);
        enregistrer.setBounds(50, 250, 100, 30);
        retour.setBounds(200, 250, 100, 30);
        
    }
    
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(villeTextField);
        container.add(prefecture);
        container.add(enregistrer);
        container.add(retour);
    }
    public void addActionEvent() {
        enregistrer.addActionListener(this);
        retour.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == enregistrer) {
			PrefectureDao bddPrefecture = new PrefectureDao();
			VilleDao bddVille = new VilleDao();
			Prefecture prefectureObj = bddPrefecture.findByName(prefecture.getSelectedItem().toString());
			Ville ville = new Ville(0,villeTextField.getText(), prefectureObj.getId());
			bddVille.add(ville);
			LoginFrameVille loginFrameVille = new LoginFrameVille();
            this.setVisible(false);
		}
		if(e.getSource() == retour) {
			LoginFrameVille loginFrameVille = new LoginFrameVille();
            this.setVisible(false);
		}
	}

}

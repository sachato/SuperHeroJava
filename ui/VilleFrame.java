package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import dao.IncidentDao;
import dao.MaitriseDao;
import dao.PrefectureDao;
import dao.StatusIncidentDao;
import dao.SuperHeroDao;
import model.Incident;
import model.Maitrise;
import model.Prefecture;
import model.StatusIncident;
import model.SuperHero;
import model.Ville;
import services.Utils;

public class VilleFrame extends Fenetre implements ActionListener, ItemListener{
	
	Ville ville;
	List<StatusIncident> allIncidentResolu =  new ArrayList<>();
	int bestHero1 = 0;
	int nbBestHero1 = 0;
	int bestHero2 = 0;
	int nbBestHero2 = 0;
	int bestHero3 = 0;
	int nbBestHero3 = 0;
	
		
	Container container = getContentPane();
	JLabel aujourdhui = new JLabel("Incidents");
	JLabel historique = new JLabel("10 derniers incident resolu");
	JLabel declarationIncident = new JLabel("Déclaration Incident");
	JLabel meilleur = new JLabel("Meilleurs Heros");
	JLabel nbAttnte = new JLabel("0 En attente");
	JLabel nbEnCour = new JLabel("0 En cour");
	JLabel nbResolu = new JLabel("0 Résolu");
	JLabel un = new JLabel("1 : Aucun");
	JLabel deux = new JLabel("2 : Aucun");
	JLabel trois = new JLabel("3 : Aucun");
	JSeparator separator1 = new JSeparator();
	JSeparator separator2 = new JSeparator();
	JSeparator separator3 = new JSeparator(SwingConstants.VERTICAL);
    JLabel incidentLabel = new JLabel("Incident");
    JComboBox incident = null;
    JLabel superHeroLabel = new JLabel("SuperHero");
    JComboBox superHero = null;
    JLabel contactLabel = new JLabel("Contact");
    JLabel contact = new JLabel("");
    JCheckBox noHero = new JCheckBox("Ne pas affecter de superHero");
    JButton enregistrer = new JButton("Enregistrer");
    JButton logout = new JButton("logout");
    ImageIcon gif = new ImageIcon("spinnn.gif");
    JLabel loading = new JLabel();
    
	public VilleFrame(Ville ville) {
		super(ville.getNom());
		this.ville = ville;
		this.setVisible(false);
		this.setBounds(10, 10, 370, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	setComboBoxIncident();
    	setComboBoxSuperHero();
    	setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        checkMeilleurHero();
        setLabels();
        this.setVisible(true);
	}
	
	public void setComboBoxIncident() {
    	IncidentDao bdd = new IncidentDao();
    	List<Incident> allIncident = bdd.findAll();
    	List<String> allIncidentName = new ArrayList<String>();
    	for (Incident incident : allIncident) {
    		allIncidentName.add(incident.getNom());
    	}
    	incident = new JComboBox(allIncidentName.toArray());
    }
	
	public void setComboBoxSuperHero() {
		SuperHeroDao bddSuperhero = new SuperHeroDao();
		MaitriseDao bddMaitrise = new MaitriseDao();
		StatusIncidentDao bddStatisIncident = new StatusIncidentDao();
		List<SuperHero> heroPrefecture = bddSuperhero.findByIdPrefecture((int) ville.getPrefecture());
		List<SuperHero> maitriseIncident = new ArrayList<>();
		Incident incident = new Incident(1, "Incendie");
		for(SuperHero hero : heroPrefecture) {
			List<Maitrise> maitrise = bddMaitrise.findBySuperHero(hero.getId());
			for(Maitrise superMaitise : maitrise) {
				if(superMaitise.getIdIncident() == incident.getId()) {
					maitriseIncident.add(hero);
				}
			}
		}
		List<String> heroDispo = new ArrayList<>();
		for(SuperHero hero : maitriseIncident) {
			List<StatusIncident> statusIncident = bddStatisIncident.findByIdsuperheroEtStatus("En cour", hero.getId());
			if(statusIncident.isEmpty()) {
				heroDispo.add(hero.getNom() );
			}
		}
		superHero = new JComboBox(heroDispo.toArray());
		try {
			SuperHero toSend = bddSuperhero.findByName(heroDispo.get(0));
			affichageContact(toSend);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void setComboBoxSuperHero(Incident incident) {
		SuperHeroDao bddSuperhero = new SuperHeroDao();
		MaitriseDao bddMaitrise = new MaitriseDao();
		StatusIncidentDao bddStatisIncident = new StatusIncidentDao();
		List<SuperHero> heroPrefecture = bddSuperhero.findByIdPrefecture((int) ville.getPrefecture());
		List<SuperHero> maitriseIncident = new ArrayList<>();
		for(SuperHero hero : heroPrefecture) {
			List<Maitrise> maitrise = bddMaitrise.findBySuperHero(hero.getId());
			for(Maitrise superMaitise : maitrise) {
				if(superMaitise.getIdIncident() == incident.getId()) {
					maitriseIncident.add(hero);
				}
			}
		}
		List<String> heroDispo = new ArrayList<>();
		for(SuperHero hero : maitriseIncident) {
			List<StatusIncident> statusIncident = bddStatisIncident.findByIdsuperheroEtStatus("En cour", hero.getId());
			if(statusIncident.isEmpty()) {
				heroDispo.add(hero.getNom() );
			}
		}
		
		if(heroDispo.isEmpty()) {
			superHero.removeAllItems();
		}
		else {
			superHero.removeAllItems();
			for(String hero : heroDispo) {
				superHero.addItem(hero);
			}
			
		}
		SuperHero toSend = null;
		try {
			toSend = bddSuperhero.findByName(heroDispo.get(0));
		}
		catch (Exception e) {
			System.out.println(e);
		}
		affichageContact(toSend);
	}
	
	public void affichageContact(SuperHero hero) {
		if(hero != null) {
			this.contact.setText(hero.getTelephone());
		}
		else {
			this.contact.setText("");
		}
	}
	
	public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
    	aujourdhui.setBounds(15, 10, 100, 30);
    	nbAttnte.setBounds(20, 25, 100, 30);
    	nbEnCour.setBounds(20, 40, 100, 30);
    	nbResolu.setBounds(20, 55, 100, 30);
    	separator3.setBounds(185, 0, 3, 90);
    	meilleur.setBounds(205, 10, 100, 30);
    	un.setBounds(210, 25, 100, 30);
    	deux.setBounds(210, 40, 100, 30);
    	trois.setBounds(210, 55, 100, 30);
    	separator1.setBounds(0, 90, 1000, 3);
    	declarationIncident.setBounds(25, 110, 200, 30);
    	incidentLabel.setBounds(50, 150, 100, 30);
    	incident.setBounds(150, 150, 150, 30);
    	superHeroLabel.setBounds(50, 190, 100, 30);
    	superHero.setBounds(150, 190, 150, 30);
    	contactLabel.setBounds(50, 230, 100, 30);
    	contact.setBounds(150, 230, 100, 30);
    	noHero.setBounds(50, 270, 200, 30);
    	enregistrer.setBounds(50, 310, 100, 30);
    	logout.setBounds(220, 310, 100, 30);
    	separator2.setBounds(0, 350, 1000, 3);
    	loading.setIcon(gif);
		loading.setBounds(135, 400, 150, 150);
    }
    
    public void addComponentsToContainer() {
        
        container.add(aujourdhui);
        container.add(nbAttnte);
        container.add(nbEnCour);
        container.add(nbResolu);
        container.add(separator3);
        container.add(meilleur);
        container.add(un);
        container.add(deux);
        container.add(trois);
        container.add(separator1);
        container.add(incidentLabel);
        container.add(incident);
        container.add(superHeroLabel);
        container.add(superHero);
        container.add(contactLabel);
        container.add(contact);
        container.add(noHero);
        container.add(enregistrer);
        container.add(declarationIncident);
        container.add(separator2);
        container.add(logout);
        container.add(loading);
    }
    public void addActionEvent() {
    	incident.addItemListener(this);
    	superHero.addItemListener(this);
    	enregistrer.addActionListener(this);
    	noHero.addActionListener(this);
    	logout.addActionListener(this);
    }
    
    public void checkMeilleurHero() {
    	this.bestHero1 = 0;
    	this.nbBestHero1 = 0;
    	this.bestHero2 = 0;
    	this.nbBestHero2 = 0;
    	this.bestHero3 = 0;
    	this.nbBestHero3 = 0;
    	this.allIncidentResolu = null;
    	StatusIncidentDao bddIncident = new StatusIncidentDao();
    	this.allIncidentResolu = bddIncident.findByIdVilleEtStatus("Résolu", this.ville.getId());
    	HashMap<Integer, Integer> dic = new HashMap<Integer, Integer>();
    	for(StatusIncident compteSuperhero : this.allIncidentResolu) {
    		if(dic.containsKey(compteSuperhero.getIdSuperHero())){
    			int temp = dic.get(compteSuperhero.getIdSuperHero());
    			dic.put(compteSuperhero.getIdSuperHero(), temp+1);
    		}
    		else {
    			dic.put(compteSuperhero.getIdSuperHero(), 1);
    		}
    	}
    	Iterator it = dic.entrySet().iterator();
    	while(it.hasNext()) {
    		Map.Entry<Integer, Integer> entry = (Map.Entry)it.next();
    		if(entry.getValue() > this.nbBestHero3) {
    			this.bestHero3 = entry.getKey();
    			this.nbBestHero3 = entry.getValue();
    		}
    		if(entry.getValue() > this.nbBestHero2) {
    			this.bestHero3 = this.bestHero2;
    			this.nbBestHero3 = this.nbBestHero2;
    			this.bestHero2 = entry.getKey();
    			this.nbBestHero2 = entry.getValue();
    		}
    		if(entry.getValue() > this.nbBestHero1) {
    			this.bestHero2 = this.bestHero1;
    			this.nbBestHero2 = this.nbBestHero1;
    			this.bestHero1 = entry.getKey();
    			this.nbBestHero1 = entry.getValue();
    		}
    	}
    }
    


    public void setLabels() {
    	StatusIncidentDao bddIncident = new StatusIncidentDao();
    	SuperHeroDao bddSuperhero = new SuperHeroDao();
    	List<StatusIncident> enAttente = bddIncident.findByIdVilleEtStatus("En attente", ville.getId());
    	int nbIncidentEnAttente = 0;
    	for(StatusIncident incidento : enAttente) {
    		nbIncidentEnAttente = nbIncidentEnAttente+1;
    	}
    	nbAttnte.setText(""+nbIncidentEnAttente+" En attente");
    	
    	List<StatusIncident> enCour = bddIncident.findByIdVilleEtStatus("En cour", ville.getId());
    	int nbIncidentEnCour = 0;
    	for(StatusIncident incidento : enCour) {
    		nbIncidentEnCour = nbIncidentEnCour+1;
    	}
    	nbEnCour.setText(""+nbIncidentEnCour+" En cour");
    	List<StatusIncident> resolu = bddIncident.findByIdVilleEtStatus("Resolu", ville.getId());
    	int nbIncidentResolu = 0;
    	for(StatusIncident incidento : resolu) {
    		nbIncidentResolu = nbIncidentResolu+1;
    	}
    	nbResolu.setText(""+nbIncidentResolu+" Résolu");
    	SuperHero hero1 = bddSuperhero.findById(bestHero1);
    	SuperHero hero2 = bddSuperhero.findById(bestHero2);
    	SuperHero hero3 = bddSuperhero.findById(bestHero3);
    	try {
    		un.setText("1: "+hero1.getNom().toString());
    	}
    	catch (Exception e) {
			System.out.println(e);
		}
    	try {
    		deux.setText("2: "+hero2.getNom().toString());
    	}
    	catch (Exception e) {
			System.out.println(e);
		}
    	try {
    		trois.setText("3: "+hero3.getNom().toString());
    	}
    	catch (Exception e) {
			System.out.println(e);
		}
    	loading.setVisible(false);
    }
    
    public void enregistrerIncident(){
    	IncidentDao bddIncident = new IncidentDao();
		Incident incidento = bddIncident.findByName(incident.getSelectedItem().toString());
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		if(noHero.isSelected()) {
			StatusIncident status = new StatusIncident(0, 0, this.ville.getId(), incidento.getId(), "En attente", currentTime,(String) null);
			StatusIncidentDao bddStatusIncident = new StatusIncidentDao();
			bddStatusIncident.add(status);
		}
		else {
			SuperHeroDao bddSuperHero = new SuperHeroDao();
			SuperHero hero = bddSuperHero.findByName(superHero.getSelectedItem().toString());
			StatusIncident status = new StatusIncident(0, hero.getId(), this.ville.getId(), incidento.getId(), "En cour", currentTime,(String) null);
			StatusIncidentDao bddStatusIncident = new StatusIncidentDao();
			bddStatusIncident.add(status);
		}
    }
    
    
    public void afterLoading() {
    	this.checkMeilleurHero();
    	this.setLabels();
    	loading.setVisible(false);
    }

    public void updateSuperComboBox(String item) {
    	IncidentDao bddIncident = new IncidentDao();
		Incident incident = bddIncident.findByName(item);
		setComboBoxSuperHero(incident);
    }

    public void itemStateChanged(ItemEvent e) {
    	if(e.getSource() == incident) {
    		if ((e.getStateChange() == ItemEvent.SELECTED)) {
    			loading.setVisible(true);
    			Thread runningSim = new Thread() {
    	             public void run() {
    	             	try {
    	             		String item = e.getItem().toString();
    	             		updateSuperComboBox(item);
    	             	}
    	             	finally {
    	             		afterLoading();
    					}
    	             }
    			 };
    			 runningSim.start();
    			
    		}
    	}
	    
    	if(e.getSource() == superHero) {
    		if ((e.getStateChange() == ItemEvent.SELECTED)) {
    			SuperHeroDao bddSuperhero = new SuperHeroDao();
    			SuperHero hero = bddSuperhero.findByName(e.getItem().toString());
    			affichageContact(hero);
    		}
    	}   
	}
    
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == enregistrer) {
			loading.setVisible(true);
			Thread runningSim = new Thread() {
	             public void run() {
	             	try {
	             		enregistrerIncident();
	             	}
	             	finally {
	             		afterLoading();
					}
	             }
			 };
			 runningSim.start();
			
			
		}
		if(e.getSource() == noHero) {
			if(noHero.isSelected()) {
				superHero.setVisible(false);
				contact.setVisible(false);
			}
			else {
				superHero.setVisible(true);
				contact.setVisible(true);
			}
		}
		if(e.getSource() == logout) {
			LoginFrameVille loginVille = new LoginFrameVille();
        	this.setVisible(false);
		}
	}

}

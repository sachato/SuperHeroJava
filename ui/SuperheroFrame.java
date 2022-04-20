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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import dao.IncidentDao;
import dao.MaitriseDao;
import dao.StatusIncidentDao;
import dao.VilleDao;
import model.Incident;
import model.Maitrise;
import model.StatusIncident;
import model.SuperHero;
import model.Ville;
import services.Utils;

public class SuperheroFrame extends Fenetre implements ActionListener {
	
	SuperHero superhero;
	List<StatusIncident> lastFiveIncidentEnAttente = new ArrayList<>();
	List<Maitrise> maitrise = new ArrayList<>();
	StatusIncident aGerer = null;
	int nbMaitrise1 = 0;
	int nbMaitrise2 = 0;
	int nbMaitrise3 = 0;
	Container container = getContentPane();
	JLabel enAttente = new JLabel("Incident en attente");
	JButton acutaliser = new JButton("Actualiser");
	JLabel enAttente1 = new JLabel("");
	JLabel enAttenteVille1 = new JLabel("");
	JButton btnEnAttente1 = new JButton("J'y vais !");
	JLabel enAttente2 = new JLabel("");
	JLabel enAttenteVille2 = new JLabel("");
	JButton btnEnAttente2 = new JButton("J'y vais !");
	JLabel enAttente3 = new JLabel("");
	JLabel enAttenteVille3 = new JLabel("");
	JButton btnEnAttente3 = new JButton("J'y vais !");
	JLabel enAttente4 = new JLabel("");
	JLabel enAttenteVille4 = new JLabel("");
	JButton btnEnAttente4 = new JButton("J'y vais !");
	JLabel enAttente5 = new JLabel("");
	JLabel enAttenteVille5 = new JLabel("");
	JButton btnEnAttente5 = new JButton("J'y vais !");
	JSeparator separator1 = new JSeparator();
	JLabel enCour = new JLabel("Incident en cour");
	JLabel incidentEnCour = new JLabel("Aucun incident en cour");
	JButton termier = new JButton("Incident clos");
	JSeparator separator2 = new JSeparator();
	JLabel historique = new JLabel("Incident resolu");
	JLabel incidentMaitrise1 = new JLabel("");
	JLabel incidentMaitrise2 = new JLabel("");
	JLabel incidentMaitrise3 = new JLabel("");
	JLabel nbIncidentMaitrise1 = new JLabel("");
	JLabel nbIncidentMaitrise2 = new JLabel("");
	JLabel nbIncidentMaitrise3 = new JLabel("");

	public SuperheroFrame(SuperHero superhero) {
		super(superhero.getNom());
		this.superhero = superhero;
		this.setVisible(true);
		this.setBounds(10, 10, 370, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	dothespin();
    	setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        listMaitrise();
        checkIncidentEnCour();
        checkNbIncidentMaitrise();
        listIncidentEnAttente();
        checkIncidentEnCour();
        setLabels();
	}
	
	public void dothespin() {
		
	}
	
	
	
	public void setLayoutManager() {
        container.setLayout(null);
    }
	
	public void setLocationAndSize() {
		enAttente.setBounds(25, 10, 150, 30);
		acutaliser.setBounds(200, 10, 100, 30);
		enAttente1.setBounds(50, 50, 100, 30);
		enAttenteVille1.setBounds(150, 50, 100, 30);
		btnEnAttente1.setBounds(250, 50, 100, 30);
		enAttente2.setBounds(50, 90, 100, 30);
		enAttenteVille2.setBounds(150, 90, 100, 30);
		btnEnAttente2.setBounds(250, 90, 100, 30);
		enAttente3.setBounds(50, 130, 100, 30);
		enAttenteVille3.setBounds(150, 130, 100, 30);
		btnEnAttente3.setBounds(250, 130, 100, 30);
		enAttente4.setBounds(50, 170, 100, 30);
		enAttenteVille4.setBounds(150, 170, 100, 30);
		btnEnAttente4.setBounds(250, 170, 100, 30);
		enAttente5.setBounds(50, 210, 100, 30);
		enAttenteVille5.setBounds(150, 210, 100, 30);
		btnEnAttente5.setBounds(250, 210, 100, 30);
		separator1.setBounds(0, 250, 1000, 3);
		enCour.setBounds(25, 270, 100, 30);
		incidentEnCour.setBounds(150, 270, 1000, 30);
		termier.setBounds(75, 310, 200, 60);
		separator2.setBounds(0, 390, 1000, 3);
		historique.setBounds(25, 410, 100, 30);
		incidentMaitrise1.setBounds(50, 450, 100, 30);
		incidentMaitrise2.setBounds(50, 480, 100, 30);
		incidentMaitrise3.setBounds(50, 510, 100, 30);
		nbIncidentMaitrise1.setBounds(150, 450, 100, 30);
		nbIncidentMaitrise2.setBounds(150, 480, 100, 30);
		nbIncidentMaitrise3.setBounds(150, 510, 100, 30);
    }
	
	 public void addComponentsToContainer() {
		 	container.add(enAttente);
		 	container.add(acutaliser);
	        container.add(enAttente1);
	        container.add(enAttenteVille1);
	        container.add(btnEnAttente1);
	        container.add(enAttente2);
	        container.add(enAttenteVille2);
	        container.add(btnEnAttente2);
	        container.add(enAttente3);
	        container.add(enAttenteVille3);
	        container.add(btnEnAttente3);
	        container.add(enAttente4);
	        container.add(enAttenteVille4);
	        container.add(btnEnAttente4);
	        container.add(enAttente5);
	        container.add(enAttenteVille5);
	        container.add(btnEnAttente5);
	        container.add(separator1);
	        container.add(enCour);
	        container.add(incidentEnCour);
	        container.add(termier);
	        container.add(separator2);
	        container.add(historique);
	        container.add(incidentMaitrise1);
	        container.add(incidentMaitrise2);
	        container.add(incidentMaitrise3);
	        container.add(nbIncidentMaitrise1);
	        container.add(nbIncidentMaitrise2);
	        container.add(nbIncidentMaitrise3);
	 }
	 
	 public void addActionEvent() {
		 btnEnAttente1.addActionListener(this);
		 btnEnAttente2.addActionListener(this);
		 btnEnAttente3.addActionListener(this);
		 btnEnAttente4.addActionListener(this);
		 btnEnAttente5.addActionListener(this);
		 termier.addActionListener(this);
		 acutaliser.addActionListener(this);
	    }
	 
	 public void listMaitrise() {
		 MaitriseDao bddMaitrise = new MaitriseDao();
		 this.maitrise = bddMaitrise.findBySuperHero(this.superhero.getId());
	 }
	 
	 public void checkNbIncidentMaitrise() {
		 nbMaitrise1 = 0;
		 nbMaitrise2 = 0;
		 nbMaitrise3 = 0;
		 StatusIncidentDao bddIncident = new StatusIncidentDao();
		 List<StatusIncident> lstFull = bddIncident.findByIdSuperHeroEtStatus("Résolu", superhero.getId());
		 int quelleMaitrise = 0;
		 for(Maitrise maitrise : this.maitrise) {
			 quelleMaitrise += 1;
			 for(StatusIncident checkMe : lstFull) {
				 if(checkMe.getIdIncident() == maitrise.getIdIncident()) {
					 switch (quelleMaitrise) {
					case 1: {
						this.nbMaitrise1 += 1;
						break;
					}
					case 2: {
						this.nbMaitrise2 += 1;
						break;
					}
					case 3: {
						this.nbMaitrise3 += 1;
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + quelleMaitrise);
					}
				 }
			 }
		 }
	 }
	 
	 public void listIncidentEnAttente(){
		 VilleDao bddVille = new VilleDao();
		 StatusIncidentDao bddIncident = new StatusIncidentDao();
		 List<Ville> villeDansPrefecture = bddVille.findAllByPrefecture(this.superhero.getPrefecture());
		 List<StatusIncident> IncidentEnAttente = new ArrayList<>();
		 for(Ville ville : villeDansPrefecture) {
			 List<StatusIncident> lstFull = bddIncident.findByIdVilleEtStatus("En attente", ville.getId());
			 List<StatusIncident> lstToAdd = new ArrayList<>();
			 for(StatusIncident checkMe : lstFull) {
				 for(Maitrise maitrise : this.maitrise) {
					 if(checkMe.getIdIncident() == maitrise.getIdIncident()) {
						 lstToAdd.add(checkMe);
					 }
				 }
			 }
			 for(StatusIncident addMe : lstToAdd) {
				 IncidentEnAttente.add(addMe);
			 }
		 }
		 Utils utils = new Utils();
		 this.lastFiveIncidentEnAttente.removeAll(IncidentEnAttente);
		 this.lastFiveIncidentEnAttente = utils.findOldestIncidentEnAttente(IncidentEnAttente);
	 }
	 
	 public void checkIncidentEnCour() {
		 StatusIncidentDao bddIncident = new StatusIncidentDao();
		 try {
			 this.aGerer = bddIncident.findByIdsuperheroEtStatus("En cour", this.superhero.getId()).get(0);
		 }
		 catch (Exception e) {
			System.out.println(e);
		}
	 }
	 
	 public void setLabels() {
		 VilleDao bddVille = new VilleDao();
		 IncidentDao bddIncident = new IncidentDao();
		 int nbIncidentEnCour = lastFiveIncidentEnAttente.size();
		 switch (nbIncidentEnCour) {
		case 0: {
			enAttente1.setVisible(false);
			enAttenteVille1.setVisible(false);
			btnEnAttente1.setVisible(false);
			enAttente2.setVisible(false);
			enAttenteVille2.setVisible(false);
			btnEnAttente2.setVisible(false);
			enAttente3.setVisible(false);
			enAttenteVille3.setVisible(false);
			btnEnAttente3.setVisible(false);
			enAttente4.setVisible(false);
			enAttenteVille4.setVisible(false);
			btnEnAttente4.setVisible(false);
			enAttente5.setVisible(false);
			enAttenteVille5.setVisible(false);
			btnEnAttente5.setVisible(false);
			break;
		}
		case 1: {
			enAttente1.setVisible(true);
			enAttenteVille1.setVisible(true);
			btnEnAttente1.setVisible(true);
			enAttente1.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(0).getIdIncident()).getNom().toString());
			enAttenteVille1.setText(bddVille.findById(lastFiveIncidentEnAttente.get(0).getIdVille()).getNom().toString());
			enAttente2.setVisible(false);
			enAttenteVille2.setVisible(false);
			btnEnAttente2.setVisible(false);
			enAttente3.setVisible(false);
			enAttenteVille3.setVisible(false);
			btnEnAttente3.setVisible(false);
			enAttente4.setVisible(false);
			enAttenteVille4.setVisible(false);
			btnEnAttente4.setVisible(false);
			enAttente5.setVisible(false);
			enAttenteVille5.setVisible(false);
			btnEnAttente5.setVisible(false);
			break;
		}
		case 2: {
			enAttente1.setVisible(true);
			enAttenteVille1.setVisible(true);
			btnEnAttente1.setVisible(true);
			enAttente2.setVisible(true);
			enAttenteVille2.setVisible(true);
			btnEnAttente2.setVisible(true);
			enAttente1.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(0).getIdIncident()).getNom().toString());
			enAttenteVille1.setText(bddVille.findById(lastFiveIncidentEnAttente.get(0).getIdVille()).getNom().toString());
			enAttente2.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(1).getIdIncident()).getNom().toString());
			enAttenteVille2.setText(bddVille.findById(lastFiveIncidentEnAttente.get(1).getIdVille()).getNom().toString());
			enAttente3.setVisible(false);
			enAttenteVille3.setVisible(false);
			btnEnAttente3.setVisible(false);
			enAttente4.setVisible(false);
			enAttenteVille4.setVisible(false);
			btnEnAttente4.setVisible(false);
			enAttente5.setVisible(false);
			enAttenteVille5.setVisible(false);
			btnEnAttente5.setVisible(false);
			break;
		}
		case 3: {
			enAttente1.setVisible(true);
			enAttenteVille1.setVisible(true);
			btnEnAttente1.setVisible(true);
			enAttente2.setVisible(true);
			enAttenteVille2.setVisible(true);
			btnEnAttente2.setVisible(true);
			enAttente3.setVisible(true);
			enAttenteVille3.setVisible(true);
			btnEnAttente3.setVisible(true);
			enAttente1.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(0).getIdIncident()).getNom().toString());
			enAttenteVille1.setText(bddVille.findById(lastFiveIncidentEnAttente.get(0).getIdVille()).getNom().toString());
			enAttente2.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(1).getIdIncident()).getNom().toString());
			enAttenteVille2.setText(bddVille.findById(lastFiveIncidentEnAttente.get(1).getIdVille()).getNom().toString());
			enAttente3.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(2).getIdIncident()).getNom().toString());
			enAttenteVille3.setText(bddVille.findById(lastFiveIncidentEnAttente.get(2).getIdVille()).getNom().toString());
			enAttente4.setVisible(false);
			enAttenteVille4.setVisible(false);
			btnEnAttente4.setVisible(false);
			enAttente5.setVisible(false);
			enAttenteVille5.setVisible(false);
			btnEnAttente5.setVisible(false);
			break;
		}
		case 4: {
			enAttente1.setVisible(true);
			enAttenteVille1.setVisible(true);
			btnEnAttente1.setVisible(true);
			enAttente2.setVisible(true);
			enAttenteVille2.setVisible(true);
			btnEnAttente2.setVisible(true);
			enAttente3.setVisible(true);
			enAttenteVille3.setVisible(true);
			btnEnAttente3.setVisible(true);
			enAttente4.setVisible(true);
			enAttenteVille4.setVisible(true);
			btnEnAttente4.setVisible(true);
			enAttente1.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(0).getIdIncident()).getNom().toString());
			enAttenteVille2.setText(bddVille.findById(lastFiveIncidentEnAttente.get(0).getIdVille()).getNom().toString());
			enAttente2.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(1).getIdIncident()).getNom().toString());
			enAttenteVille2.setText(bddVille.findById(lastFiveIncidentEnAttente.get(1).getIdVille()).getNom().toString());
			enAttente3.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(2).getIdIncident()).getNom().toString());
			enAttenteVille3.setText(bddVille.findById(lastFiveIncidentEnAttente.get(2).getIdVille()).getNom().toString());
			enAttente4.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(3).getIdIncident()).getNom().toString());
			enAttenteVille4.setText(bddVille.findById(lastFiveIncidentEnAttente.get(3).getIdVille()).getNom().toString());
			enAttente5.setVisible(false);
			enAttenteVille5.setVisible(false);
			btnEnAttente5.setVisible(false);
			break;
		}
		case 5: {
			enAttente1.setVisible(true);
			enAttenteVille1.setVisible(true);
			btnEnAttente1.setVisible(true);
			enAttente2.setVisible(true);
			enAttenteVille2.setVisible(true);
			btnEnAttente2.setVisible(true);
			enAttente3.setVisible(true);
			enAttenteVille3.setVisible(true);
			btnEnAttente3.setVisible(true);
			enAttente4.setVisible(true);
			enAttenteVille4.setVisible(true);
			btnEnAttente4.setVisible(true);
			enAttente5.setVisible(true);
			enAttenteVille5.setVisible(true);
			btnEnAttente5.setVisible(true);
			enAttente1.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(0).getIdIncident()).getNom().toString());
			enAttenteVille1.setText(bddVille.findById(lastFiveIncidentEnAttente.get(0).getIdVille()).getNom().toString());
			enAttente2.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(1).getIdIncident()).getNom().toString());
			enAttenteVille2.setText(bddVille.findById(lastFiveIncidentEnAttente.get(1).getIdVille()).getNom().toString());
			enAttente3.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(2).getIdIncident()).getNom().toString());
			enAttenteVille3.setText(bddVille.findById(lastFiveIncidentEnAttente.get(2).getIdVille()).getNom().toString());
			enAttente4.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(3).getIdIncident()).getNom().toString());
			enAttenteVille4.setText(bddVille.findById(lastFiveIncidentEnAttente.get(3).getIdVille()).getNom().toString());
			enAttente5.setText(bddIncident.findById(lastFiveIncidentEnAttente.get(4).getIdIncident()).getNom().toString());
			enAttenteVille5.setText(bddVille.findById(lastFiveIncidentEnAttente.get(4).getIdVille()).getNom().toString());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + nbIncidentEnCour);
		}
		if(this.aGerer != null) {
			incidentEnCour.setText(""+bddIncident.findById(aGerer.getIdIncident()).getNom().toString()+" à "+bddVille.findById(aGerer.getIdVille()).getNom().toString());
		}
		else {
			incidentEnCour.setText("Aucun incident en cour");
		}
		
		switch (this.maitrise.size()){
		case 1: {
			incidentMaitrise1.setText(""+bddIncident.findById(maitrise.get(0).getIdIncident()).getNom().toString()+" :");
			nbIncidentMaitrise1.setText(""+ nbMaitrise1);
			incidentMaitrise2.setVisible(false);
			nbIncidentMaitrise2.setVisible(false);
			incidentMaitrise3.setVisible(false);
			nbIncidentMaitrise3.setVisible(false);
			break;
		}
		case 2: {
			incidentMaitrise1.setText(""+bddIncident.findById(maitrise.get(0).getIdIncident()).getNom().toString()+" :");
			nbIncidentMaitrise1.setText(""+ nbMaitrise1);
			incidentMaitrise2.setText(""+bddIncident.findById(maitrise.get(1).getIdIncident()).getNom().toString()+" :");
			nbIncidentMaitrise2.setText(""+ nbMaitrise2);
			incidentMaitrise3.setVisible(false);
			nbIncidentMaitrise3.setVisible(false);
			break;
		}
		case 3: {
			incidentMaitrise1.setText(""+bddIncident.findById(maitrise.get(0).getIdIncident()).getNom().toString()+" :");
			nbIncidentMaitrise1.setText(""+ nbMaitrise1);
			incidentMaitrise2.setText(""+bddIncident.findById(maitrise.get(1).getIdIncident()).getNom().toString()+" :");
			nbIncidentMaitrise2.setText(""+ nbMaitrise2);
			incidentMaitrise3.setText(""+bddIncident.findById(maitrise.get(2).getIdIncident()).getNom().toString()+" :");
			nbIncidentMaitrise3.setText(""+ nbMaitrise3);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.maitrise.size());
		}
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		StatusIncidentDao bddIncident = new StatusIncidentDao();
		if(e.getSource() == btnEnAttente1) {
			if(this.aGerer != null) {
				JOptionPane.showMessageDialog(this, "Incident deja en cour terminez le !");
			}
			else {
				this.aGerer = this.lastFiveIncidentEnAttente.get(0);
				bddIncident.update(aGerer, superhero, "En cour");
				this.listIncidentEnAttente();
				this.checkIncidentEnCour();
				this.setLabels();
			}
		}
		if(e.getSource() == btnEnAttente2) {
			this.aGerer = this.lastFiveIncidentEnAttente.get(1);
			bddIncident.update(aGerer, superhero, "En cour");
			this.listIncidentEnAttente();
			this.checkIncidentEnCour();
			this.setLabels();
		}
		if(e.getSource() == btnEnAttente3) {
			this.aGerer = this.lastFiveIncidentEnAttente.get(2);
			bddIncident.update(aGerer, superhero, "En cour");
			this.listIncidentEnAttente();
			this.checkIncidentEnCour();
			this.setLabels();
		}
		if(e.getSource() == btnEnAttente4) {
			this.aGerer = this.lastFiveIncidentEnAttente.get(3);
			bddIncident.update(aGerer, superhero, "En cour");
			this.listIncidentEnAttente();
			this.checkIncidentEnCour();
			this.setLabels();
		}
		if(e.getSource() == btnEnAttente5) {
			this.aGerer = this.lastFiveIncidentEnAttente.get(4);
			bddIncident.update(aGerer, superhero, "En cour");
			this.listIncidentEnAttente();
			this.checkIncidentEnCour();
			this.setLabels();
		}
		if(e.getSource() == termier) {
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			bddIncident.update(aGerer, "Résolu", currentTime);
			this.aGerer = null;
			this.listIncidentEnAttente();
			this.checkIncidentEnCour();
			this.checkNbIncidentMaitrise();
			this.setLabels();
		}
		if(e.getSource()==acutaliser) {
			listMaitrise();
	        checkNbIncidentMaitrise();
	        listIncidentEnAttente();
	        checkIncidentEnCour();
	        setLabels();
		}
	}

}

package model;

import dao.MaitriseDao;

public class Maitrise {

	private int id;
	private int idSuperHero;
	private int idIncident;
	
	public Maitrise(int id, int idSuperHero, int idIncident) {
		super();
		this.id = id;
		this.idSuperHero = idSuperHero;
		this.idIncident = idIncident;
	}

	public int getId() {
		return id;
	}

	public int getIdSuperHero() {
		return idSuperHero;
	}

	public int getIdIncident() {
		return idIncident;
	}
	
	public void enregistrer(SuperHero superhero, Incident incident) {
		MaitriseDao bdd = new MaitriseDao();
		Maitrise go = new Maitrise(0, superhero.getId(), incident.getId());
		bdd.add(go);
	}
	
}

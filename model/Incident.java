package model;

public class Incident {
	private int id;
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	private String nom;
	
	public Incident(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
		
	
}

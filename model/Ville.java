package model;

public class Ville {

	private int id;
	private String nom;
	private int prefecture;
	
	public Ville(int Id, String Nom, int Prefecture) {
		this.id = Id;
		this.nom = Nom;
		this.prefecture = Prefecture;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public double getPrefecture() {
		return prefecture;
	}

	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+this.id+"   "+this.nom+"   "+this.prefecture;
	}
}

package model;

public class SuperHero {

	private int id;
	private String nom;
	private String telephone;
	private String password;
	private int prefecture;
	
	public SuperHero(int id, String nom, String telephone, String password, int prefecture) {
		this.id = id;
		this.nom = nom;
		this.prefecture = prefecture;
		this.telephone = telephone;
		this.password = password;
	}
	
	public String getNom() {
		return nom;
	}

	public int getPrefecture() {
		return prefecture;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getPassword() {
		return password;
	}

	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		
		return ""+this.id+", Nom: "+this.nom+", telephone:"+this.telephone;
	}
}

package model;

public class StatusIncident {
	
	private int id;
	private int idSuperHero ;
	private int idVille;
	private int idIncident;
	private String status;
	private String DateTimeOuverture;
	private String DateTimeResolution;
	
	
	public StatusIncident(int id, int idSuperHero, int idVille, int idIncident, String status, String DateTimeOuverture, String DateTimeResolution) {
		super();
		this.id = id;
		if(idSuperHero != 0) {
			this.idSuperHero = idSuperHero;
		}
		this.idVille = idVille;
		this.idIncident = idIncident;
		this.status = status;
		this.DateTimeOuverture = DateTimeOuverture;
		this.DateTimeResolution = this.DateTimeResolution;
	}

	public int getId() {
		return id;
	}

	public int getIdSuperHero() {
		return idSuperHero;
	}

	public int getIdVille() {
		return idVille;
	}

	public int getIdIncident() {
		return idIncident;
	}
	
	public String getStatus() {
		return status;
	}

	public String getDateTimeOuverture() {
		return DateTimeOuverture;
	}

	public String getDateTimeResolution() {
		return DateTimeResolution;
	}
	
	
}

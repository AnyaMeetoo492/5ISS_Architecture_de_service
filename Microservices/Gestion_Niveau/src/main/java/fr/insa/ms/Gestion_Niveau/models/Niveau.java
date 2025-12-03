package fr.insa.ms.Gestion_Niveau.models;

import java.util.*;

public class Niveau {
	private Date datetime;
	private float valeur;
	
	public Niveau () {}
	
	public Niveau(Date datetime, float valeur) {
		super();
		this.datetime = datetime;
		this.valeur = valeur;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public float getValeur() {
		return valeur;
	}

	public void setValeur(float valeur) {
		this.valeur = valeur;
	}
}

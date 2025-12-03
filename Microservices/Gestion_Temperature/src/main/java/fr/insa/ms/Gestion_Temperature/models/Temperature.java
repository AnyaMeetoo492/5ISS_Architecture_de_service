package fr.insa.ms.Gestion_Temperature.models;

import java.util.*;

public class Temperature {
	private Date datetime;
	private float valeur;
	
	public Temperature() {};
	
	public Temperature(Date datetime, float valeur) {
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

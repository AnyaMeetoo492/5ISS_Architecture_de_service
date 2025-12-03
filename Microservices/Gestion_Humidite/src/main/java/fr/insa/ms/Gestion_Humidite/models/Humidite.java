package fr.insa.ms.Gestion_Humidite.models;

import java.util.*;

public class Humidite {
	private Date datetime;
	private float date;
	
	public Humidite() {};
	
	public Humidite(Date datetime, float date) {
		super();
		this.datetime = datetime;
		this.date = date;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public float getDate() {
		return date;
	}

	public void setDate(float date) {
		this.date = date;
	}
}

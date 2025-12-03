package fr.insa.ms.CaveAVin.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Humidite {
	
	private Date date;
	private float value;
	
	public Humidite() {
	}
	
	public Humidite(Date date, float value) {
		this.date = date;
		this.value = value;
	}
	
	public Date getDate() {
		return this.date;
	}
	public float getValue() {
		return this.value;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setValue(float value) {
		this.value = value;
	}

}

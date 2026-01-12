package fr.insa.ms.Refroidissement.models;

public class Refroidissement {
	
	private boolean active;
	private int limitTemperature = 30;
	
	public Refroidissement() {}
	
	public Refroidissement(boolean active) {
		this.active = active;
	}
	
	public Refroidissement(boolean active, int limitTemperature) {
		this.active = active;
		this.limitTemperature = limitTemperature;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void verifActivation(int temperature) {
		if (temperature >= this.limitTemperature) {
			this.setActive(true);
		}
		else{
			this.setActive(false);
		}
	}
}
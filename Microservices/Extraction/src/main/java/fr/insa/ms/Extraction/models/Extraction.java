package fr.insa.ms.Extraction.models;

public class Extraction {
	private boolean active;
	private int citerneID;
	
	public Extraction(boolean active, int citerneID) {
		super();
		this.active = active;
		this.citerneID = citerneID;
	}
	
	public Extraction() {}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getCiterneID() {
		return citerneID;
	}
	
	public void setCiterneID(int citerneID) {
		this.citerneID = citerneID;
	}
}

package fr.insa.ms.Orchestrateur.models;

public class Refroidissement {
	
	private boolean active;
	private int citerneID;
	
	public Refroidissement() {}
	
	public Refroidissement(int citerneID, boolean active) {
		this.active = active;
		this.citerneID = citerneID;
	}
	
	public int getCiterneID() {
		return citerneID;
	}

	public void setCiterneID(int citerneID) {
		this.citerneID = citerneID;
	}

	public boolean isActive() {
		return this.active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}

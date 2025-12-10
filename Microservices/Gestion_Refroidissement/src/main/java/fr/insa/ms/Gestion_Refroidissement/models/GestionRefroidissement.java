package fr.insa.ms.Gestion_Refroidissement.models;

public class GestionRefroidissement {
	private boolean actionRefroidissement;

	public GestionRefroidissement(boolean actionRefroidissement) {
		super();
		this.actionRefroidissement = actionRefroidissement;
	}
	
	public GestionRefroidissement() {}

	public boolean isActionRefroidissement() {
		return actionRefroidissement;
	}

	public void setActionRefroidissement(boolean actionRefroidissement) {
		this.actionRefroidissement = actionRefroidissement;
	}
}

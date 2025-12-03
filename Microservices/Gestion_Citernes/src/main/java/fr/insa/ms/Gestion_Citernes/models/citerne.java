package fr.insa.ms.Gestion_Citernes.models;

import java.util.Date;

public class citerne {
	
	public int citernID;
	public Date dateDeDebut;
	public Date dateDeFin;
	public boolean contientLiquide;
	
	
	public citerne() {
	}
	
	public citerne(int citernID, Date dateDeDebut, Date dateDeFin, boolean contientLiquide) {
		this.citernID = citernID;
		this.dateDeDebut = dateDeDebut;
		this.dateDeFin = dateDeFin;
		this.contientLiquide = contientLiquide;
	}
	
	public int getCiternID() {
		return citernID;
	}
	public void setCiternID(int citernID) {
		this.citernID = citernID;
	}
	public Date getDateDeDebut() {
		return dateDeDebut;
	}
	public void setDateDeDebut(Date dateDeDebut) {
		this.dateDeDebut = dateDeDebut;
	}
	public Date getDateDeFin() {
		return dateDeFin;
	}
	public void setDateDeFin(Date dateDeFin) {
		this.dateDeFin = dateDeFin;
	}
	public boolean isContientLiquide() {
		return contientLiquide;
	}
	public void setContientLiquide(boolean contientLiquide) {
		this.contientLiquide = contientLiquide;
	} 

}

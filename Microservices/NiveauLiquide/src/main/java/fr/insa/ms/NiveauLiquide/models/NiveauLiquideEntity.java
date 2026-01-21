
package fr.insa.ms.NiveauLiquide.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "niveauLiquide")
public class NiveauLiquideEntity {
	
	@Id
    @Column(name = "Date")
    private LocalDateTime date;

	@Column(name = "Valeur")
    private float valeur;
	
	@Column(name = "CiterneID")
    private int citerneID;

	public NiveauLiquideEntity() {}
	
	public NiveauLiquideEntity(LocalDateTime date, float valeur, int citerneID) {
		super();
		this.date = date;
		this.valeur = valeur;
		this.citerneID = citerneID;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public float getValeur() {
		return valeur;
	}

	public void setValeur(float valeur) {
		this.valeur = valeur;
	}

	public int getCiterneID() {
		return citerneID;
	}

	public void setCiterneID(int citerneID) {
		this.citerneID = citerneID;
	}
}

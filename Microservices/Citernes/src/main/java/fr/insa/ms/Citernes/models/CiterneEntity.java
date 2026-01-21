package fr.insa.ms.Citernes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "citerne")
public class CiterneEntity {

	@Id
	@Column(name = "CiterneID")
	private int citerneID;
	
	@Column(name = "CiterneName")
	private String citerneName;
	
	@Column(name = "DateDeDebut")
	private LocalDate startDate;
	
	@Column(name = "DateDeFin")
	private LocalDate endDate;
	
	@Column(name = "ContientLiquide")
	private Boolean contientLiquide;
	
	public CiterneEntity() {};
	
	public CiterneEntity(int citerneID, String citerneName, LocalDate startDate, LocalDate endDate, Boolean contientLiquide) {
		super();
		this.citerneID = citerneID;
		this.citerneName = citerneName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.contientLiquide = contientLiquide;
	}
	
	public boolean isContientLiquide() {
		return contientLiquide;
	}

	public void setContientLiquide(Boolean contientLiquide) {
		this.contientLiquide = contientLiquide;
	}

	public int getCiterneID() {
		return citerneID;
	}
	public void setCiterneID(int citerneID) {
		this.citerneID = citerneID;
	}
	public String getCiterneName() {
		return citerneName;
	}
	public void setCiterneName(String citerneName) {
		this.citerneName = citerneName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}

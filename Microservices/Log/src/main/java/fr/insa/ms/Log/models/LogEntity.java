package fr.insa.ms.Log.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "log")
public class LogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logID;
	
	@Column(name = "ActionType")
	private String ActionType;
	
	@Column(name = "Date")
	private LocalDateTime Date;
	
	@Column(name = "Observation")
	private String Observation;
	
    @Column(name = "CiterneID")
    private int CiterneID;
	
	public LogEntity() {}
	
	public LogEntity(int citerneID, String actionType, LocalDateTime date, String observation) {
		super();
		CiterneID = citerneID;
		ActionType = actionType;
		Date = date;
		Observation = observation;
	}

	public int getCiterneID() {
		return CiterneID;
	}

	public void setCiterneID(int citerneID) {
		CiterneID = citerneID;
	}

	public String getActionType() {
		return ActionType;
	}

	public void setActionType(String actionType) {
		ActionType = actionType;
	}

	public LocalDateTime getDate() {
		return Date;
	}

	public void setDate(LocalDateTime date) {
		Date = date;
	}

	public String getObservation() {
		return Observation;
	}

	public void setObservation(String observation) {
		Observation = observation;
	}
}

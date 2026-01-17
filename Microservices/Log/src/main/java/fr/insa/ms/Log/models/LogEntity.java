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
	private String actionType;
	
	@Column(name = "Date")
	private LocalDateTime date;
	
	@Column(name = "Observation")
	private String observation;
	
    @Column(name = "CiterneID")
    private int citerneID;
	
	public LogEntity() {}
	
	public LogEntity(int citerneID, String actionType, LocalDateTime date, String observation) {
		super();
		this.citerneID = citerneID;
		this.actionType = actionType;
		this.date = date;
		this.observation = observation;
	}
	
	public LogEntity(int citerneID, String actionType, String observation) {
		super();
		this.citerneID = citerneID;
		this.actionType = actionType;
		this.observation = observation;
	}

	public int getCiterneID() {
		return citerneID;
	}

	public void setCiterneID(int citerneID) {
		this.citerneID = citerneID;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}

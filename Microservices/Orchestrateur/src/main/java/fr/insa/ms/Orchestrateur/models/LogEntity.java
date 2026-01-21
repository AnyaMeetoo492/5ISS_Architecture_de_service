package fr.insa.ms.Orchestrateur.models;

import java.time.LocalDateTime;

public class LogEntity {
	
	private Integer logID;
	private String actionType;
	private LocalDateTime date;
	private String observation;
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

	public Integer getLogID() {
		return logID;
	}

	public void setLogID(Integer logID) {
		this.logID = logID;
	}
}

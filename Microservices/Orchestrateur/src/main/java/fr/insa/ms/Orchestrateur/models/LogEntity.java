package fr.insa.ms.Orchestrateur.models;

import java.time.LocalDateTime;

public class LogEntity {
	
	private Integer logID;
	private String ActionType;
	private LocalDateTime Date;
	private String Observation;
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

	public Integer getLogID() {
		return logID;
	}

	public void setLogID(Integer logID) {
		this.logID = logID;
	}
}

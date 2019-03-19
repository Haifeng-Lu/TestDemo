package com.datamaster.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "eegrecord")
public class EegRecord extends Auditable<String> {

	@Column(name = "recordId")
    private String recordId;
    
	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	@Column(name = "collectedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date collectedOn;
	
	public Date getCollectedOn() {
		return this.collectedOn;
	}
	
	public void setCollectedOn(Date collectedOn) {
		this.collectedOn = collectedOn;
	}
	
	@Column(name = "sleepOrNot")
	private Boolean sleepOrNot;
	
	public Boolean getSleepOrNot() {
		return this.sleepOrNot;
	}
	
	public void setSleepOrNot(Boolean sleepOrNot) {
		this.sleepOrNot = sleepOrNot;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    private Person person;
	
	public Person getPerson() {
		return this.person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
}

package com.datamaster.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends Auditable<String> {

	@Column(name = "name")
    private String name;
    
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "age")
	private int age;
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Column(name = "sex")
	private String sex;
	
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name="phone")
	private String phone;
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "eduLevel")
	private String eduLevel;
	
	public String getEduLevel() {
		return this.eduLevel;
	}
	
	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}
	
	@Column(name = "diagnosis")
	private String diagnosis;
	
	public String getDiagnosis() {
		return this.diagnosis;
	}
	
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	@Column(name = "medicalRecordNum")
	private String medicalRecordNum;
	
	public String getMedicalRecordNum() {
		return this.medicalRecordNum;
	}
	
	public void setMedicalRecordNum(String medicalRecordNum) {
		this.medicalRecordNum = medicalRecordNum;
	}
	
	@OneToMany(mappedBy="person")
	private Set<EegRecord> eegRecords = new HashSet<EegRecord>();
	
	public Set<EegRecord> getEegRecords() {
		return this.eegRecords;
	}
	
	public void setEegRecords(Set<EegRecord> eegRecords) {
		this.eegRecords = eegRecords;
	}
}

package com.myproject.employeeMS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Assessments {
	@Id
	@GeneratedValue
	private int uniq_id;
	
	private long userId;
	private String assessment;
	private String date;
	private String type;
	
	
	public Assessments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Assessments(long userId, String assessment, String date, String type) {
		super();
		this.userId = userId;
		this.assessment = assessment;
		this.date = date;
		this.type = type;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getAssessment() {
		return assessment;
	}
	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Assessments [userId=" + userId + ", assessment=" + assessment + ", date=" + date + ", type=" + type
				+ "]";
	}
	
	
	

}

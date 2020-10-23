package web;

import java.time.LocalDate;

import common.Education;
import common.Gender;

public class Record {
	private String nameSurnameStr;
	private String name;
	private String surname;
	private String email;
	private String dateStr;
	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	private LocalDate date;
	private Gender gender;
	private String[] interests;
	private Education education;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNameSurnameStr(String str) {
		this.nameSurnameStr = str;
	}
	
	public String getNameSurnameStr() {
		return this.nameSurnameStr;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setDate(LocalDate d) {
		this.date = d;
	}
	
	public void setGender(String gender) {
		switch (gender) {
		case "male":
			this.gender = Gender.MALE;
			return;
		case "female":
			this.gender = Gender.FEMALE;
			return;
		default:			
		}
	}
	
	public void setInterests(String[] interests) {
		this.interests = interests;
	}
	
	public void setEducation(String education) {
		Education ed = Education.findByName(education);
		this.education = ed;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public String[] getInterests() {
		return interests;
	}
	
	public Education getEducation() {
		return education;
	}
	
	

}

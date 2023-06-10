package com.thi.bt.knn;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idPatient;
	@Column(name = "gender")
	private String gender;
	private String polyuria;
	private String polydipsia;
	private String wLoss;
	private String weakness;
	private String polyphagia;
	private String genital;
	private String visualBlurring;
	private String itching;
	private String irritability;
	private String delayedHealing;
	private String partial;
	private String muscleStiffness;
	private String alopecia;
	private String obesity;
	@Column(name = "age")
	private int age;
	@Column(name = "date_")
	private Date date;

	@Column(name = "name")
	private String name;
	@Column(name = "class")
	private String classDT;
	@Column(name = "requestby")
	private int requestBy;

	public Patient() {
		super();
	}

	public Patient(String gender, String polyuria, String polydipsia, String wLoss, String weakness, String polyphagia,
				   String genital, String visualBlurring, String itching, String irritability, String delayedHealing,
				   String partial, String muscleStiffness, String alopecia, String obesity, int idPatient, int age,
				   Date date, String name, String classDT) {
		super();
		this.gender = gender;
		this.polyuria = polyuria;
		this.polydipsia = polydipsia;
		this.wLoss = wLoss;
		this.weakness = weakness;
		this.polyphagia = polyphagia;
		this.genital = genital;
		this.visualBlurring = visualBlurring;
		this.itching = itching;
		this.irritability = irritability;
		this.delayedHealing = delayedHealing;
		this.partial = partial;
		this.muscleStiffness = muscleStiffness;
		this.alopecia = alopecia;
		this.obesity = obesity;
		this.idPatient = idPatient;
		this.age = age;
		this.date = date;
		this.name = name;
		this.classDT = classDT;
	}



	public String getClassDT() {
		return classDT;
	}

	public void setClassDT(String classDT) {
		this.classDT = classDT;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPolyuria() {
		return polyuria;
	}

	public void setPolyuria(String polyuria) {
		this.polyuria = polyuria;
	}

	public String getPolydipsia() {
		return polydipsia;
	}

	public void setPolydipsia(String polydipsia) {
		this.polydipsia = polydipsia;
	}

	public String getwLoss() {
		return wLoss;
	}

	public void setwLoss(String wLoss) {
		this.wLoss = wLoss;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	public String getPolyphagia() {
		return polyphagia;
	}

	public void setPolyphagia(String polyphagia) {
		this.polyphagia = polyphagia;
	}

	public String getGenital() {
		return genital;
	}

	public void setGenital(String genital) {
		this.genital = genital;
	}

	public String getVisualBlurring() {
		return visualBlurring;
	}

	public void setVisualBlurring(String visualBlurring) {
		this.visualBlurring = visualBlurring;
	}

	public String getItching() {
		return itching;
	}

	public void setItching(String itching) {
		this.itching = itching;
	}

	public String getIrritability() {
		return irritability;
	}

	public void setIrritability(String irritability) {
		this.irritability = irritability;
	}

	public String getDelayedHealing() {
		return delayedHealing;
	}

	public void setDelayedHealing(String delayedHealing) {
		this.delayedHealing = delayedHealing;
	}

	public String getPartial() {
		return partial;
	}

	public void setPartial(String partial) {
		this.partial = partial;
	}

	public String getMuscleStiffness() {
		return muscleStiffness;
	}

	public void setMuscleStiffness(String muscleStiffness) {
		this.muscleStiffness = muscleStiffness;
	}

	public String getAlopecia() {
		return alopecia;
	}

	public void setAlopecia(String alopecia) {
		this.alopecia = alopecia;
	}

	public String getObesity() {
		return obesity;
	}

	public void setObesity(String obesity) {
		this.obesity = obesity;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(int requestBy) {
		this.requestBy = requestBy;
	}
}
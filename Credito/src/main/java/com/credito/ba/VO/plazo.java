package com.credito.ba.VO;

public class plazo
{
	private int id;
	private int semanas;
	private double tasaNormal;
	private double tasaPuntual;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSemanas() {
		return semanas;
	}
	public void setSemanas(int semanas) {
		this.semanas = semanas;
	}
	public double getTasaNormal() {
		return tasaNormal;
	}
	public void setTasaNormal(double tasaNormal) {
		this.tasaNormal = tasaNormal;
	}
	public double getTasaPuntual() {
		return tasaPuntual;
	}
	public void setTasaPuntual(double tasaPuntual) {
		this.tasaPuntual = tasaPuntual;
	}
	public plazo(int id, int semanas, double tasaNormal, double tasaPuntual) {
		super();
		this.id = id;
		this.semanas = semanas;
		this.tasaNormal = tasaNormal;
		this.tasaPuntual = tasaPuntual;
	}
	public plazo() {
		super();
	}
	
	
	
}

package com.credito.ba.VO;

public class Cotizacion
{
	private int precio;
	private int plazo;
	private double tasaNormal;
	private double tasaPuntual;
	private double abonoNormal;
	private double abonoPuntual;
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getPlazo() {
		return plazo;
	}
	public void setPlazo(int plazo) {
		this.plazo = plazo;
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
	public double getAbonoNormal() {
		return abonoNormal;
	}
	public void setAbonoNormal(double abonoNormal) {
		this.abonoNormal = abonoNormal;
	}
	public double getAbonoPuntual() {
		return abonoPuntual;
	}
	public void setAbonoPuntual(double abonoPuntual) {
		this.abonoPuntual = abonoPuntual;
	}
	public Cotizacion(int precio, int plazo, double tasaNormal, double tasaPuntual, double abonoNormal,
			double abonoPuntual) {
		super();
		this.precio = precio;
		this.plazo = plazo;
		this.tasaNormal = tasaNormal;
		this.tasaPuntual = tasaPuntual;
		this.abonoNormal = abonoNormal;
		this.abonoPuntual = abonoPuntual;
	}
	public Cotizacion() {
		super();
	}
}

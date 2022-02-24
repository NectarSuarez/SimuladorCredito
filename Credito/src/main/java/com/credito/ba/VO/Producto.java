package com.credito.ba.VO;

public class Producto
{
	private int id;
	private String sku;
	private String nombre;
	private String descripcion;
	private int precio;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Producto(int id, String sku, String nombre, String descripcion, int precio) {
		super();
		this.id = id;
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	public Producto() {
		super();
	}
}

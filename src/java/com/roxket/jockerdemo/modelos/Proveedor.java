/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roxket.jockerdemo.modelos;

/**
 *
 * @author Oxker Studio
 */
public class Proveedor {
	private long proveedorId;
	private String nombreProv;
	private String contacto;
	private String celuProv;
	private String fijoProv;

	public Proveedor(){
	}
	
	public Proveedor(long proveedorId){
	this.proveedorId = proveedorId;
	}

	public Proveedor(long proveedorId, String nombreProv, String contacto, String celuProv, String fijoProv) {
		this.proveedorId = proveedorId;
		this.nombreProv = nombreProv;
		this.contacto = contacto;
		this.celuProv = celuProv;
		this.fijoProv = fijoProv;
	}
	
	public long getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(long proveedorId) {
		this.proveedorId = proveedorId;
	}

	public String getNombreProv() {
		return nombreProv;
	}

	public void setNombreProv(String nombreProv) {
		this.nombreProv = nombreProv;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getCeluProv() {
		return celuProv;
	}

	public void setCeluProv(String celuProv) {
		this.celuProv = celuProv;
	}

	public String getFijoProv() {
		return fijoProv;
	}

	public void setFijoProv(String fijoProv) {
		this.fijoProv = fijoProv;
	}
	
	
}

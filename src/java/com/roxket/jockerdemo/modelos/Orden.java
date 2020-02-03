/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roxket.jockerdemo.modelos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ox_h4
 */
public class Orden {
	private long ordenId;
	private Cliente cliente;
	private Empleado empleado;
	private Date fechaOrden;
	private int descuento;
	private double importe;
	private String importeRedondeado;
	private List<DetalleOrden> detalles = new ArrayList<>();
	
	public Orden(){
	}

	public Orden(long ordenId,  Cliente cliente, Empleado empleado, Date fechaOrden, int descuento, double importe) {
		this.ordenId = ordenId;
		this.cliente = cliente;
		this.empleado = empleado;
		this.fechaOrden = fechaOrden;
		this.descuento = descuento;
		this.importe = importe;
	}

	public long getOrdenId() {
		return ordenId;
	}

	public void setOrdenId(long ordenId) {
		this.ordenId = ordenId;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getImporteRedondeado() {
		return new DecimalFormat("#.##").format(importe);
	}

	public void setImporteRedondeado(String importeRedondeado) {
		this.importeRedondeado = importeRedondeado;
	}

	public List<DetalleOrden> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleOrden> detalles) {
		this.detalles = detalles;
	}

	
	
}

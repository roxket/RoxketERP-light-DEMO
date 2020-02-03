
package com.roxket.jockerdemo.modelos;

import java.util.Date;

/**
 *
 * @author Oxker Studio
 */
public class Empleado {
	private long empleadoId;
	private String nombre;
	private String apellido;
	private Date fechaNac;
	private int reportaA;
	private int extension;
	private String jefe;
	
	public Empleado(){
	}

	public Empleado(long empleadoId) {
		this.empleadoId = empleadoId;
	}

	public Empleado(long empleadoId, String nombre, String apellido, Date fechaNac, int reportaA, int extension) {
		this.empleadoId = empleadoId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.reportaA = reportaA;
		this.extension = extension;
	}
	
	public long getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(long empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public int getReportaA() {
		return reportaA;
	}

	public void setReportaA(int reportaA) {
		this.reportaA = reportaA;
	}

	public int getExtension() {
		return extension;
	}

	public void setExtension(int extension) {
		this.extension = extension;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}
	
	public String getNombreCompleto(){
		return nombre + " " + apellido;
	}
}

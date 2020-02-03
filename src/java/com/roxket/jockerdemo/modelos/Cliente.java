
package com.roxket.jockerdemo.modelos;

/**
 *
 * @author Oxker Studio
 */
public class Cliente {
	private long clienteId;
	private String cedulaRuc;
	private String nombreCia;
	private String nombreContacto;
	private String direccionCliente;
	private String fax;
	private String email;
	private String celular;
	private String fijo;

	public Cliente() {
	}
	
	public Cliente(long clienteId){
		this.clienteId = clienteId;
	}

	public Cliente(long clienteId, String cedulaRuc, String nombreCia, String nombreContacto, String direccionCliente, String fax, String email, String celular, String fijo) {
		this.clienteId = clienteId;
		this.cedulaRuc = cedulaRuc;
		this.nombreCia = nombreCia;
		this.nombreContacto = nombreContacto;
		this.direccionCliente = direccionCliente;
		this.fax = fax;
		this.email = email;
		this.celular = celular;
		this.fijo = fijo;
	}

	public long getClienteId() {
		return clienteId;
	}

	public void setClienteId(long clienteId) {
		this.clienteId = clienteId;
	}

	public String getCedulaRuc() {
		return cedulaRuc;
	}

	public void setCedulaRuc(String cedulaRuc) {
		this.cedulaRuc = cedulaRuc;
	}

	public String getNombreCia() {
		return nombreCia;
	}

	public void setNombreCia(String nombreCia) {
		this.nombreCia = nombreCia;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getDireccionCli() {
		return direccionCliente;
	}

	public void setDireccionCli(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}
}

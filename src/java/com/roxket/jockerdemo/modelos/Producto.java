
package com.roxket.jockerdemo.modelos;

/**
 *
 * @author Oxker Studio
 */
public class Producto {
	private long productoId;
	private Proveedor proveedor;
	private Categoria categoria;
	private String descripcion;
	private Double precioUnit;
	private int existencia;
	
	public Producto(){
	}
	
	public Producto(long productoid){
		this.productoId = productoid;
	}

	public Producto(long productoId, Proveedor proveedor, Categoria categoria, String descripcion, Double precioUnit, int existencia) {
		this.productoId = productoId;
		this.proveedor = proveedor;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precioUnit = precioUnit;
		this.existencia = existencia;
	}

	public long getProductoId() {
		return productoId;
	}

	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecioUnit() {
		return precioUnit;
	}

	public void setPrecioUnit(Double precioUnit) {
		this.precioUnit = precioUnit;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}
	
	
}

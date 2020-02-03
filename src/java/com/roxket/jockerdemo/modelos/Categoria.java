
package com.roxket.jockerdemo.modelos;

/**
 *
 * @author Oxker Studio
 */
public class Categoria {
	private long categoriaId;
	private String nombreCat; 
	
	public Categoria(){
	}
	
	public Categoria(long categoriaId){
		this.categoriaId = categoriaId;
	}
	
	public Categoria(long categoriaId, String nombreCat){
		this.categoriaId = categoriaId;
		this.nombreCat = nombreCat;
	}

	public long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNombreCat() {
		return nombreCat;
	}

	public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}
	
}

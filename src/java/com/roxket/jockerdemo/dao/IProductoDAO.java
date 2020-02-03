
package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.modelos.Categoria;
import com.roxket.jockerdemo.modelos.Producto;
import java.util.List;

/**
 *
 * @author Oxker Studio
 */
public interface IProductoDAO {
	public List<Producto> listAll();
	public String insert(Producto producto);
	public String update(Producto producto);
	public String delete(Producto producto);
	public Producto findById(long idProducto);
	public List<Producto> getProductosByCategorias(Categoria categoria);
	public List<Producto> busquedaPorCriterio(String param);	
}


package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.modelos.DetalleOrden;
import com.roxket.jockerdemo.modelos.Orden;
import java.util.List;

/**
 *
 * @author Oxker Studio
 */
public interface IOrdenJDBCDAO {
 
    List<Orden> listAll();
    List<DetalleOrden> detalles(Orden orden);
    Orden findById(long idOrden);
    Orden insert(Orden orden);
    Orden delete(Orden orden);
	
}

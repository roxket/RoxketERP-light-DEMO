
package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.modelos.DetalleOrden;
import com.roxket.jockerdemo.modelos.Orden;
import java.util.List;

/**
 *
 * @author Oxker Studio
 */
public interface IDetalleOrdenJDBCDAO {
    List<DetalleOrden> getDetalles(Orden orden);
    List<DetalleOrden> getDetalles(long idOrden);
    DetalleOrden insert(DetalleOrden detalle);
    DetalleOrden delete(DetalleOrden detalle);
}

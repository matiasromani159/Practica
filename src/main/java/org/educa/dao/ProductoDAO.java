package org.educa.dao;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;


import java.io.IOException;
import java.util.List;

/**
 * Interfaz DAO de Producto. Define las operaciones de acceso a datos:
 * lectura del XML, escritura por consola y exportación del resumen a txt.
 *
 * @author Marcos Casas
 * @author Matías Romani
 * @version 1.0
 * @since 05/10/2026
 */
public interface ProductoDAO
{
    //Le exigimos q sea lista para despues poder ordenar como va a salir
    List<ProductoEntity> readFile() throws JAXBException;

    //aqui mostramos con la lista q sacamos en el read
    void writeFile(List<ProductoEntity> productos);

    //para exportar a txt
    void exportSummary(List<ProductoEntity> productos, String path) throws IOException;


}

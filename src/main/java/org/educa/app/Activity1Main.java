package org.educa.app;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;
import org.educa.service.ProductoService;

import java.util.List;

/**
 * Actividad 1: lee el fichero XML del inventario, lo convierte en objetos Java
 * mediante JAXB y muestra por consola la información de cada producto
 * (precio final, costes y beneficio calculados).
 *
 * @author Marcos Casas
 * @author Matías Romani
 * @version 1.0
 * @since 05/10/2026
 */
public class Activity1Main {

    private static final String FILE_XML = "src/main/resources/xml/inventario_junio2026.xml";

    public static void main(String[] args)
    {
        ProductoService service = new ProductoService();
        try
        {
            List<ProductoEntity> productos = service.readFile(FILE_XML);
            service.writeFile(productos, FILE_XML);
        }
        catch (JAXBException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

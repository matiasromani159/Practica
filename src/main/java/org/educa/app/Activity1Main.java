package org.educa.app;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;
import org.educa.service.ProductoService;

import java.io.IOException;
import java.util.List;

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

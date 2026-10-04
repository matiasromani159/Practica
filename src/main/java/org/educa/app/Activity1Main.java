package org.educa.app;

import jakarta.xml.bind.JAXBException;
import org.educa.service.ProductoService;

import java.io.IOException;

public class Activity1Main {

    private static final String PATH_TXT = "src/main/resources/export/";
    private static final String FILE_XML = "src/main/resources/xml/inventario_junio2026.xml";

    public static void main(String[] args) {
        //Por recordar en el new habra q pasarle los parametros para q desps service
        //llame a dao y este funcione con esos datos

        String ruta = "src/main/resources/inventario_junio2025.xml";
        ProductoService service = new ProductoService();
    }
}

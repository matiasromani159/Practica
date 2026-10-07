package org.educa.app;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;
import org.educa.service.ProductoService;

import java.io.IOException;
import java.util.List;

public class Activity2Main {
    // Carpeta de salida (termina en / porque se le pega el nombre del fichero)
    private static final String PATH_TXT = "src/main/resources/export/";
    // XML de entrada
    private static final String FILE_XML = "src/main/resources/xml/inventario_junio2026.xml";

    public static void main(String[] args) {
        ProductoService service = new ProductoService();
        try {
            // Lee el XML, calcula el resumen y escribe el txt
            service.exportSummary(PATH_TXT, FILE_XML);
            System.out.println("Fichero creado correctamente");
        } catch (JAXBException e) {
            System.out.println("Error leyendo el XML: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error escribiendo el txt: " + e.getMessage());
        }
    }
}

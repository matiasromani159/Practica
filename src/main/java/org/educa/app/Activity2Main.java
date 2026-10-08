package org.educa.app;

import jakarta.xml.bind.JAXBException;
import org.educa.service.ProductoService;

import java.io.IOException;

/**
 * Actividad 2: lee el fichero XML del inventario y genera un fichero de texto
 * "result_<mes><anio>.txt" en src/main/resources/export con el resumen
 * (fecha, número de productos, beneficio total y datos del fichero XML).
 *
 * @author Marcos Casas
 * @author Matías Romani
 * @version 1.0
 * @since 06/10/2026
 */
public class Activity2Main {
    //Carpeta de salida (termina en / porque se le pega el nombre del fichero)
    private static final String PATH_TXT = "src/main/resources/export/";
    //XML de entrada
    private static final String FILE_XML = "src/main/resources/xml/inventario_junio2026.xml";

    public static void main(String[] args) {
        ProductoService service = new ProductoService();
        try {
            //Lee el XML, calcula el resumen y escribe el txt
            service.exportSummary(PATH_TXT, FILE_XML);
            System.out.println("Fichero creado correctamente");
        } catch (JAXBException e) {
            System.out.println("Error leyendo el XML: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error escribiendo el txt: " + e.getMessage());
        }
    }
}

package org.educa.app;

import jakarta.xml.bind.JAXBException;
import org.educa.service.ProductoService;

import java.io.IOException;
import java.text.ParseException;

/**
 * Actividad 3: lee el fichero XML del inventario y genera un Excel (.xlsx)
 * con Apache POI en src/main/resources/export, con formato de cabecera en
 * negrita, alineación de celdas y color de fondo alterno por filas.
 *
 * @author Marcos Casas
 * @author Matías Romani
 * @version 1.0
 * @since 07/10/2026
 */
public class Activity3Main {
    private static final String PATH = "src/main/resources/export/";
    private static final String FILE_XML = "src/main/resources/xml/inventario_junio2026.xml";

    public static void main(String[] args) {
        ProductoService service = new ProductoService();
        try {
            service.exportExcel(PATH, FILE_XML);
            System.out.println("Excel creado correctamente");
        } catch (JAXBException e) {
            System.out.println("Error leyendo el XML: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error escribiendo el Excel: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error de formato: " + e.getMessage());
        }
    }
}

package org.educa.service;

import jakarta.xml.bind.JAXBException;
import org.educa.dao.ProductoDAO;
import org.educa.dao.ProductoDAOImpl;
import org.educa.dao.ProductoExcelDAO;
import org.educa.entity.ProductoEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Capa de servicio de Producto. Organiza las operaciones entre la capa
 * de presentación (Main) y la capa de acceso a datos (DAO), separando
 * responsabilidades según la arquitectura por capas.
 *
 * @author Marcos Casas
 * @author Matías Romani
 * @version 1.0
 * @since 05/10/2026
 */
public class ProductoService {

    public List<ProductoEntity> readFile(String fileXml) throws JAXBException
    {
        ProductoDAO dao = new ProductoDAOImpl(fileXml);
        return dao.readFile();
    }
    public void writeFile(List<ProductoEntity> productos, String fileXml)
    {
        ProductoDAO dao = new ProductoDAOImpl(fileXml);
        dao.writeFile(productos);
    }
    public void exportSummary(String path, String fileXml) throws JAXBException, IOException {
        ProductoDAO dao = new ProductoDAOImpl(fileXml);
        List<ProductoEntity> productos = dao.readFile();
        dao.exportSummary(productos, path);
    }
    public void exportExcel(String path, String fileXml) throws JAXBException, IOException, ParseException {
        ProductoDAO dao = new ProductoDAOImpl(fileXml);
        List<ProductoEntity> productos = dao.readFile();
        ProductoExcelDAO excelDao = new ProductoExcelDAO(fileXml);
        excelDao.exportExcel(productos, path);
    }


}

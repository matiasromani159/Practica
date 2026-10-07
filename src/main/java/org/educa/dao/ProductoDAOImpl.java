package org.educa.dao;

import generated.Producto;
import generated.Productos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.educa.entity.CostesEntity;
import org.educa.entity.ProductoEntity;
import org.educa.entity.ProveedorEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
public class ProductoDAOImpl implements ProductoDAO
{
    //creamos constructor y añadimos parametro de ruta para pasarselo al read
    public final String ruta;

    public ProductoDAOImpl(String ruta)
    {
        this.ruta=ruta;
    }

    public List<ProductoEntity> readFile() throws JAXBException
    {
        List<ProductoEntity> lista;
        try {
            //la raiz del XML es Productos
            JAXBContext context = JAXBContext.newInstance(Productos.class);
            //pasamos de xml a objetos java
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //Lee el fichero de verdad y devuelve la raiz contodo dentro
            //(new File solo es la dirección, no lee nada)
            Productos productos = (Productos) unmarshaller.unmarshal(new File(ruta));

            //La lista para mostrar
            lista = new ArrayList<>();

            //Lista que coge del XML
            List<Producto> productosXML = productos.getProducto();

            //Añade todos los productos
            for (Producto producto : productosXML) {
                ProductoEntity productoEntity = new ProductoEntity();
                ProveedorEntity proveedorEntity = new ProveedorEntity();

                productoEntity.setCodigo(producto.getCodigo());
                productoEntity.setNumeroSerie(producto.getNumeroSerie());
                productoEntity.setMarca(producto.getMarca());
                productoEntity.setModelo(producto.getModelo());
                productoEntity.setCategoria(producto.getCategoria());
                productoEntity.setAnioLanzamiento(producto.getAnioLanzamiento());
                productoEntity.setGarantiaMeses(producto.getGarantiaMeses());
                productoEntity.setProveedor(proveedorEntity);
                productoEntity.setTipoConexion(producto.getTipoConexion());
                productoEntity.setPrecio(producto.getPrecio());

                //Rellenamos el proveedor
                proveedorEntity.setCiudad(producto.getProveedor().getCiudad());
                proveedorEntity.setCodigoPostal(producto.getProveedor().getCodigoPostal());
                proveedorEntity.setEmpresa(producto.getProveedor().getEmpresa());
                proveedorEntity.setPais(producto.getProveedor().getPais());


                productoEntity.setDescuento(producto.getDescuento());

                //Creamos y rellenamos los costes
                CostesEntity costesEntity = new CostesEntity();
                costesEntity.setCostesEnvio(producto.getCostes().getCostesEnvio());
                costesEntity.setCostesAlmacenaje(producto.getCostes().getCostesAlmacenaje());

                //Enlazamos los costes al producto
                productoEntity.setCostes(costesEntity);

                //producto terminado a la lista
                lista.add(productoEntity);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
        //List of es una lista inmutable, no deja .add()
        //return List.of();
    }

    public void writeFile(List<ProductoEntity> productos)
    {
        for(ProductoEntity p : productos)
        {
            //BigDecimal no admite + - *

            //descuento seria precio * descuento / 100
            BigDecimal descuentoEuros = p.getPrecio().multiply(p.getDescuento()).divide(new BigDecimal("100"));

            //precio final seria precio - descuento, redondeado a 2 decimales
            BigDecimal precioFinal = p.getPrecio().subtract(descuentoEuros).setScale(2, RoundingMode.HALF_UP);

            //coste seria almacenaje + envío
            BigDecimal coste = p.getCostes().getCostesAlmacenaje().add(p.getCostes().getCostesEnvio());

            //y beneficio seria precio final - coste
            BigDecimal beneficio = precioFinal.subtract(coste);

            //Como saldria
            System.out.println("Codigo: "+ p.getCodigo());
            System.out.println("Número de Serie: " + p.getNumeroSerie());
            System.out.println("Marca: "+ p.getMarca());
            System.out.println("Modelo: "+ p.getModelo());
            System.out.println("Categoria: "+ p.getCategoria());
            System.out.println("Anio Lanzamiento: "+ p.getAnioLanzamiento());
            System.out.println("Garantia (Meses): "+ p.getGarantiaMeses());
            System.out.println("Proveedor: "+ p.getProveedor().getEmpresa()+" "+ p.getProveedor().getCiudad()+" "+ p.getProveedor().getPais()+" "+ p.getProveedor().getCodigoPostal());
            System.out.println("Tipo Conexion: "+ p.getTipoConexion());
            System.out.println("Precio: "+ p.getPrecio());
            System.out.println("Descuento: "+ p.getDescuento());
            System.out.println("Precio final: "+ precioFinal);
            System.out.println("Coste: "+ coste);
            System.out.println("Beneficio: "+ beneficio);
        }

        // INICIO DEL EXCEL
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos");

        // Cabecera
        Row header = sheet.createRow(0);

        String[] columnas = {
                "Codigo", "Número de Serie", "Marca", "Modelo",
                "Categoria", "Precio", "Descuento", "Precio Final",
                "Coste", "Beneficio"
        };

// Estilo cabecera
        CellStyle estiloCabecera = workbook.createCellStyle();
        Font fuente = workbook.createFont();
        fuente.setBold(true);
        estiloCabecera.setFont(fuente);
        estiloCabecera.setAlignment(HorizontalAlignment.CENTER);

// Estilos filas
        CellStyle estiloFila1 = workbook.createCellStyle();
        estiloFila1.setAlignment(HorizontalAlignment.CENTER);
        estiloFila1.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        estiloFila1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle estiloFila2 = workbook.createCellStyle();
        estiloFila2.setAlignment(HorizontalAlignment.CENTER);
        estiloFila2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        estiloFila2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

// Crear cabecera
        for (int i = 0; i < columnas.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(estiloCabecera);
        }

// Datos
        int fila = 1;

        for (ProductoEntity p : productos) {

            BigDecimal descuentoEuros = p.getPrecio().multiply(p.getDescuento())
                    .divide(new BigDecimal("100"));

            BigDecimal precioFinal = p.getPrecio().subtract(descuentoEuros)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal coste = p.getCostes().getCostesAlmacenaje()
                    .add(p.getCostes().getCostesEnvio());

            BigDecimal beneficio = precioFinal.subtract(coste);

            Row row = sheet.createRow(fila);

            CellStyle estilo = (fila % 2 == 1) ? estiloFila1 : estiloFila2;

            row.createCell(0).setCellValue(p.getCodigo());
            row.createCell(1).setCellValue(p.getNumeroSerie());
            row.createCell(2).setCellValue(p.getMarca());
            row.createCell(3).setCellValue(p.getModelo());
            row.createCell(4).setCellValue(p.getCategoria());
            row.createCell(5).setCellValue(p.getPrecio().doubleValue());
            row.createCell(6).setCellValue(p.getDescuento().doubleValue());
            row.createCell(7).setCellValue(precioFinal.doubleValue());
            row.createCell(8).setCellValue(coste.doubleValue());
            row.createCell(9).setCellValue(beneficio.doubleValue());

            // Aplicar estilo a toda la fila
            for (Cell cell : row) {
                cell.setCellStyle(estilo);
            }

            fila++;
        }

        // Guardar Excel
        File carpeta = new File("src/main/resources/export");
        carpeta.mkdirs();

        File fichero = new File(ruta);
        String nombre = fichero.getName();
        String fecha = nombre.substring(11, nombre.length() - 4);

        File salida = new File(carpeta, "export_" + fecha + ".xlsx");

        try {
            FileOutputStream out = new FileOutputStream(salida);
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exportSummary(List<ProductoEntity> productos, String path) throws IOException {
        File fichero = new File(ruta);          // el XML de entrada
        String nombre = fichero.getName();      // inventario_junio2026.xml

        // Fecha: nos quedamos con lo que hay entre "inventario_" y ".xml"
        // "inventario_" tiene 11 letras, y ".xml" son las 4 últimas
        String fecha = nombre.substring(11, nombre.length() - 4);   // junio2026

        // Sumamos el beneficio de cada producto
        BigDecimal beneficioTotal = new BigDecimal("0");
        for (ProductoEntity p : productos) {
            BigDecimal descuento = p.getPrecio().multiply(p.getDescuento()).divide(new BigDecimal("100"));
            BigDecimal precioFinal = p.getPrecio().subtract(descuento);
            BigDecimal coste = p.getCostes().getCostesAlmacenaje().add(p.getCostes().getCostesEnvio());
            BigDecimal beneficio = precioFinal.subtract(coste);

            beneficioTotal = beneficioTotal.add(beneficio);
        }

        // Escribimos el txt (la carpeta export debe existir ya)
        File salida = new File(path + "result_" + fecha + ".txt");
        try (FileWriter writer = new FileWriter(salida)) {
            writer.write("Fecha: " + fecha + "\n");
            writer.write("NumeroDeProductos: " + productos.size() + "\n");
            writer.write("BeneficioTotal: " + beneficioTotal + "\n");
            writer.write("Ruta del fichero: " + fichero.getAbsolutePath() + "\n");
            writer.write("Nombre del fichero: " + nombre + "\n");
            writer.write("Tamaño del fichero: " + fichero.length() + " bytes\n");
        }
    }
}

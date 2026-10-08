package org.educa.dao;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.educa.entity.ProductoEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * DAO de exportación a Excel. Utiliza Apache POI para generar un fichero
 * .xlsx con el inventario, aplicando estilos: cabecera en negrita,
 * alineación de celdas y color de fondo alterno por filas.
 *
 * @author Marcos Casas
 * @author Matías Romani
 * @version 1.0
 * @since 07/10/2026
 */
public class ProductoExcelDAO {

    private final String ruta;

    public ProductoExcelDAO(String ruta)
    {
        this.ruta = ruta;
    }

    public void exportExcel(List<ProductoEntity> productos, String path) throws IOException
    {
        File fichero = new File(ruta);
        //inventario_junio2026.xml
        String fecha = fichero.getName().substring(11, fichero.getName().length() - 4); // junio2026

        new File(path).mkdirs(); //crea la carpeta export si no existe

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet hoja = wb.createSheet("Inventario");

            //Cabecera
            String[] titulos = {"Codigo", "Número de Serie", "Precio", "Descuento",
                    "Precio Final", "Costes Envío", "Costes Almacenaje", "Beneficio"};
            CellStyle estiloCabecera = crearEstilo(wb, IndexedColors.GREY_25_PERCENT, null, true, HorizontalAlignment.CENTER);
            Row cabecera = hoja.createRow(0);
            for (int i = 0; i < titulos.length; i++) {
                Cell celda = cabecera.createCell(i);
                celda.setCellValue(titulos[i]);
                celda.setCellStyle(estiloCabecera);
            }

            //Estilos de datos (se crean 1 sola vez, fuera del bucle)
            String euro = "#,##0.00 \"€\"";
            String porcentaje = "0.00%";

            CellStyle sCodigoV = crearEstilo(wb, IndexedColors.LIGHT_GREEN, null, true, HorizontalAlignment.LEFT);
            CellStyle sTextoV  = crearEstilo(wb, IndexedColors.LIGHT_GREEN, null, false, HorizontalAlignment.LEFT);
            CellStyle sEuroV   = crearEstilo(wb, IndexedColors.LIGHT_GREEN, euro, false, HorizontalAlignment.RIGHT);
            CellStyle sPorcV   = crearEstilo(wb, IndexedColors.LIGHT_GREEN, porcentaje, false, HorizontalAlignment.RIGHT);

            CellStyle sCodigoB = crearEstilo(wb, IndexedColors.WHITE, null, true, HorizontalAlignment.LEFT);
            CellStyle sTextoB  = crearEstilo(wb, IndexedColors.WHITE, null, false, HorizontalAlignment.LEFT);
            CellStyle sEuroB   = crearEstilo(wb, IndexedColors.WHITE, euro, false, HorizontalAlignment.RIGHT);
            CellStyle sPorcB   = crearEstilo(wb, IndexedColors.WHITE, porcentaje, false, HorizontalAlignment.RIGHT);

            //Filas de datos
            for (int i = 0; i < productos.size(); i++) {
                ProductoEntity p = productos.get(i);

                boolean verde = (i % 2 == 0);
                //ifs especiales (es if/else)
                CellStyle sCodigo = verde ? sCodigoV : sCodigoB;
                CellStyle sTexto  = verde ? sTextoV  : sTextoB;
                CellStyle sEuro   = verde ? sEuroV   : sEuroB;
                CellStyle sPorc   = verde ? sPorcV   : sPorcB;

                BigDecimal precioFinal = calcularPrecioFinal(p);
                BigDecimal envio = p.getCostes().getCostesEnvio();
                BigDecimal almacenaje = p.getCostes().getCostesAlmacenaje();
                BigDecimal beneficio = precioFinal.subtract(envio.add(almacenaje));

                Row fila = hoja.createRow(i + 1);
                escribir(fila, 0, p.getCodigo(), sCodigo);
                escribir(fila, 1, p.getNumeroSerie(), sTexto);
                escribir(fila, 2, p.getPrecio().doubleValue(), sEuro);
                escribir(fila, 3, p.getDescuento().doubleValue() / 100, sPorc); // 15.50 -> 0.155
                escribir(fila, 4, precioFinal.doubleValue(), sEuro);
                escribir(fila, 5, envio.doubleValue(), sEuro);
                escribir(fila, 6, almacenaje.doubleValue(), sEuro);
                escribir(fila, 7, beneficio.doubleValue(), sEuro);
            }

            for (int i = 0; i < titulos.length; i++) {
                hoja.autoSizeColumn(i);
            }

            try (FileOutputStream salida = new FileOutputStream(path + "export_" + fecha + ".xlsx")) {
                wb.write(salida);
            }
        }
    }

    private BigDecimal calcularPrecioFinal(ProductoEntity p) {
        BigDecimal descuento = p.getPrecio().multiply(p.getDescuento()).divide(new BigDecimal("100"));
        return p.getPrecio().subtract(descuento).setScale(2, RoundingMode.HALF_UP);
    }

    private CellStyle crearEstilo(Workbook wb, IndexedColors color, String formato,
                                  boolean negrita, HorizontalAlignment alineacion) {
        CellStyle estilo = wb.createCellStyle();
        estilo.setFillForegroundColor(color.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estilo.setAlignment(alineacion);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        if (formato != null) {
            estilo.setDataFormat(wb.createDataFormat().getFormat(formato));
        }
        Font fuente = wb.createFont();
        fuente.setBold(negrita);
        estilo.setFont(fuente);
        return estilo;
    }

    private void escribir(Row fila, int col, String valor, CellStyle estilo) {
        Cell c = fila.createCell(col);
        c.setCellValue(valor);
        c.setCellStyle(estilo);
    }

    private void escribir(Row fila, int col, double valor, CellStyle estilo) {
        Cell c = fila.createCell(col);
        c.setCellValue(valor);
        c.setCellStyle(estilo);
    }
}
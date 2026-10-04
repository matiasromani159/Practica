package org.educa.service;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

//esto es el final tras terminar el dao (llama las funciones de allí), no se separa
//por funciones (casi siempre) solo hace falta llamar al dao

//de aqui no revise nada pq es casi el ultimo paso a hacer (el ultimo ultimo es el main, este es el antepenultimo)


public class ProductoService {

    public List<ProductoEntity> readFile(String fileXml) throws JAXBException {
        //TODOImplementar
        return null;
    }

    public void exportSummary(String path, String fileXml) throws JAXBException, IOException {
        //TODOImplementar

    }

    public void exportExcel(String path, String fileXml) throws JAXBException, IOException, ParseException {
        //TODOImplementar
    }
}

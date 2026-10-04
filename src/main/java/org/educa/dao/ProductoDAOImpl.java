package org.educa.dao;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;

import java.util.List;

public class ProductoDAOImpl implements ProductoDAO
{
    //creamos constructor y añadimos parametro de ruta para pasarselo al read
    public final String ruta;

    public ProductoDAOImpl(String ruta)
    {
        this.ruta=ruta;
    }

    //falta hacer codigo leer y escribir de este archivo
    //a hacer todos los set en readfile para despues ordenarlo para escribir
    //y luego pa terminar  q service lo ejecute , y el main llama a service y ya estaría
    public List<ProductoEntity> readFile() throws JAXBException
    {
        try
        {

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    public void writeFile(List<ProductoEntity> productos)
    {

    }
}

package org.educa.dao;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.CostesEntity;
import org.educa.entity.ProductoEntity;
import org.educa.entity.ProveedorEntity;

import java.util.ArrayList;
import java.util.List;

public interface ProductoDAO
{
    //Le exigimos q sea lista para d espues poder ordenar como va a salir
    List<ProductoEntity> readFile() throws JAXBException;

    //aqui mostramos con la lista q sacamos en el read
    void writeFile(List<ProductoEntity> productos);
}

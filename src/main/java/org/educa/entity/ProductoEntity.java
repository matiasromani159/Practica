package org.educa.entity;

import java.io.File;
import java.math.BigDecimal;
//Esto estaba MAL, si miras en el xml las entity deben separarse por las clases del xml q son Producto / Proveedor /Costes (cada una contiene cosas extras)

public class ProductoEntity
{
    private String codigo;
    private String numeroSerie;
    private String marca;
    private String modelo;
    private String categoria;
    private int anioLanzamiento;
    private int garantiaMeses;
    private ProveedorEntity proveedor;//Objeto anidado
    private String tipoConexion;
    private BigDecimal precio;
    private BigDecimal descuento;
    private CostesEntity costes;//Objeto anidado

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public String getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(String tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public CostesEntity getCostes() {
        return costes;
    }

    public void setCostes(CostesEntity costes) {
        this.costes = costes;
    }
}

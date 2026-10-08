package org.educa.entity;

/**
 * Entidad Proveedor.Representa los datos del proveedor de un producto,
 * anidada dentro de ProductoEntity.
 *
 * @author Marcos Casas
 * @author Matías Romani
 * @version 1.0
 * @since 05/10/2026
 */
public class ProveedorEntity
{
    //aqui pasaremos los de proveedor q metistetodo junto en producto
    private String empresa;
    private String ciudad;
    private String pais;
    private String codigoPostal;

    public ProveedorEntity() {
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}




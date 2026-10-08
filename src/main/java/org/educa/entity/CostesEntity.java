package org.educa.entity;

import java.math.BigDecimal;

/**
 * Entidad Costes. Representa los costes de envío y almacenaje de un producto,
 * anidada dentro de ProductoEntity.
 *
 * @author Marcos Casas
 * @author Matías Romani
 * @version 1.0
 * @since 05/10/2026
 */
public class CostesEntity
{
    private BigDecimal costesAlmacenaje;
    private BigDecimal costesEnvio;

    public CostesEntity() {}

    public BigDecimal getCostesAlmacenaje() {
        return costesAlmacenaje;
    }

    public void setCostesAlmacenaje(BigDecimal costesAlmacenaje) {
        this.costesAlmacenaje = costesAlmacenaje;
    }

    public BigDecimal getCostesEnvio() {
        return costesEnvio;
    }

    public void setCostesEnvio(BigDecimal costesEnvio) {
        this.costesEnvio = costesEnvio;
    }

}

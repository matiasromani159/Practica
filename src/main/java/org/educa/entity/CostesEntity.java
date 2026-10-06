package org.educa.entity;

import java.math.BigDecimal;

public class CostesEntity
{
    //aqui pasaremos los de costes q metistetodo junto en producto

    private BigDecimal costesAlmacenaje;
    private BigDecimal costesEnvio;

    public CostesEntity()
    {

    }

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

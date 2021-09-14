package com.banco.cajeroautomatico.dao;

import com.banco.cajeroautomatico.entity.DenominacionesEfectivo;
import com.banco.cajeroautomatico.utilidades.EntitiManager;
import com.banco.cajeroautomatico.utilidades.Persistence;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DenominacionesEfectivoDao extends EntitiManager<DenominacionesEfectivo> {

    @Inject
    private Persistence persistence;

    public DenominacionesEfectivoDao() {
        super(DenominacionesEfectivo.class);
    }

    @Override
    protected Persistence getEntityManager() {
        return persistence;
    }

    public List<DenominacionesEfectivo> obtenerListaDenominacionesEfectivo() {
        return persistence.getMysql().createNamedQuery("DenominacionesEfectivo.findAll").getResultList();
    }

    public BigDecimal obtenerTotalEfectivo() {
        BigDecimal monto = (BigDecimal) persistence.getMysql().createNamedQuery("DenominacionesEfectivo.findAllSuma").getSingleResult();
        return monto == null ? BigDecimal.ZERO : monto;
    }
}

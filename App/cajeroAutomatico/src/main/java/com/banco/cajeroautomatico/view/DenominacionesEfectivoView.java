package com.banco.cajeroautomatico.view;

import com.banco.cajeroautomatico.utilidades.EfectivoBO;
import java.math.BigDecimal;
import java.util.List;

public class DenominacionesEfectivoView {

    private BigDecimal cantidad;
    private List<EfectivoBO> listaEfectivoBO;
    private BigDecimal montoTotal;
    private String mensaje;

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public List<EfectivoBO> getListaEfectivoBO() {
        return listaEfectivoBO;
    }

    public void setListaEfectivoBO(List<EfectivoBO> listaEfectivoBO) {
        this.listaEfectivoBO = listaEfectivoBO;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}

package com.banco.cajeroautomatico.utilidades;

import java.math.BigDecimal;

public class EfectivoBO {

    private Integer cantidad;
    private BigDecimal denominacion;
    private BigDecimal monto;
    private String tipo;

    public EfectivoBO() {
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(BigDecimal denominacion) {
        this.denominacion = denominacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "EfectivoBO{" + "cantidad=" + cantidad + ", denominacion=" + denominacion + ", monto=" + monto + ", tipo=" + tipo + '}';
    }

}

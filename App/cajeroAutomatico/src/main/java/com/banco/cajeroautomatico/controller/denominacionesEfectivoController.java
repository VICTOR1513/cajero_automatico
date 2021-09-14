package com.banco.cajeroautomatico.controller;

import com.banco.cajeroautomatico.model.DenominacionesEfectivoModel;
import com.banco.cajeroautomatico.utilidades.EfectivoBO;
import com.banco.cajeroautomatico.view.DenominacionesEfectivoView;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named(value = "denominacionesEfectivoController")
@SessionScoped
public class denominacionesEfectivoController implements Serializable {

    @Inject
    private DenominacionesEfectivoModel model;

    private DenominacionesEfectivoView view;

    @PostConstruct
    public void init() {
        view = new DenominacionesEfectivoView();
    }

    public DenominacionesEfectivoView getView() {
        return view;
    }

    public void setView(DenominacionesEfectivoView view) {
        this.view = view;
    }

    public void retirarEfectivo() {
        view.setListaEfectivoBO(null);
        view.setCantidad(null);
    }

    public String iniciarDiaCajero() {
        model.iniciarDia();
        view.setListaEfectivoBO(null);
        return "cajero_automatico.xhtml?faces-redirect=true";
    }

    public void cobrar() {
        if (!view.getCantidad().toString().equals("0.00")) {
            view.setListaEfectivoBO(model.retirarEfectivo(view.getCantidad()));
            if (view.getListaEfectivoBO() != null) {
                view.setMontoTotal(view.getListaEfectivoBO().stream().map(EfectivoBO::getMonto).reduce(BigDecimal.ZERO, BigDecimal::add));
            } else {
                view.setMensaje("No se puede retirar esta cantidad de efectivo");
                PrimeFaces.current().executeScript("PF('dialogConfirmacio').show();");
            }
        } else {
            view.setMensaje("Ingrese una cantidad a retirar");
            PrimeFaces.current().executeScript("PF('dialogConfirmacio').show();");
        }
    }

}

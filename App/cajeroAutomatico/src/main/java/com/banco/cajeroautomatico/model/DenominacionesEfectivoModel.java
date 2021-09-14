package com.banco.cajeroautomatico.model;

import com.banco.cajeroautomatico.dao.DenominacionesEfectivoDao;
import com.banco.cajeroautomatico.entity.DenominacionesEfectivo;
import com.banco.cajeroautomatico.utilidades.EfectivoBO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DenominacionesEfectivoModel {

    @Inject
    private DenominacionesEfectivoDao dao;

    public void iniciarDia() {
        System.out.println("CAJERO INICIADO");
        List<DenominacionesEfectivo> listaEntity = new ArrayList<>();
        listaEntity.add(new DenominacionesEfectivo(1, "Billete", 2, new BigDecimal(1000)));
        listaEntity.add(new DenominacionesEfectivo(2, "Billete", 5, new BigDecimal(500)));
        listaEntity.add(new DenominacionesEfectivo(3, "Billete", 10, new BigDecimal(200)));
        listaEntity.add(new DenominacionesEfectivo(4, "Billete", 20, new BigDecimal(100)));
        listaEntity.add(new DenominacionesEfectivo(5, "Billete", 30, new BigDecimal(50)));
        listaEntity.add(new DenominacionesEfectivo(6, "Billete", 40, new BigDecimal(20)));
        listaEntity.add(new DenominacionesEfectivo(7, "Moneda", 50, new BigDecimal(10)));
        listaEntity.add(new DenominacionesEfectivo(8, "Moneda", 100, new BigDecimal(5)));
        listaEntity.add(new DenominacionesEfectivo(9, "Moneda", 200, new BigDecimal(2)));
        listaEntity.add(new DenominacionesEfectivo(10, "Moneda", 300, new BigDecimal(1)));
        listaEntity.add(new DenominacionesEfectivo(11, "Moneda", 100, new BigDecimal(0.5)));
        listaEntity.stream().forEach(entity -> dao.saveOrEdit(entity, entity.getId()));
    }

    public List<EfectivoBO> retirarEfectivo(BigDecimal cantidad) {
        BigDecimal total = dao.obtenerTotalEfectivo();
        if (total.compareTo(cantidad) >= 0) {

            List<DenominacionesEfectivo> lista = dao.obtenerListaDenominacionesEfectivo();
            List<BigDecimal> resulList = cantidaToDenominacion(cantidad, lista);

            List<DenominacionesEfectivo> result = filtarDenominaciones(cantidad, resulList, lista);
            List<EfectivoBO> listEfectivoBO = new ArrayList<>();
            Map<DenominacionesEfectivo, List<DenominacionesEfectivo>> mapDenominacion = result.stream().collect(Collectors.groupingBy(denominacion -> denominacion));
            mapDenominacion.forEach((entity, list) -> {
                EfectivoBO bo = new EfectivoBO();
                bo.setCantidad((int) list.stream().count());
                bo.setDenominacion(entity.getDenominacion());
                bo.setTipo(entity.getTipo());
                bo.setMonto(list.stream().map(DenominacionesEfectivo::getDenominacion).reduce(BigDecimal.ZERO, BigDecimal::add));
                listEfectivoBO.add(bo);
                DenominacionesEfectivo objecto = entity;
                objecto.setCantidad(objecto.getCantidad() - bo.getCantidad());
                dao.saveOrEdit(objecto, objecto.getId());
            });
            return listEfectivoBO;
        }
        return null;
    }

    public List<DenominacionesEfectivo> filtarDenominaciones(BigDecimal cantidad, List<BigDecimal> cantidadToDenominacion, List<DenominacionesEfectivo> listaDenominacion) {
        List<DenominacionesEfectivo> resulList = new ArrayList<>();
        for (DenominacionesEfectivo denominacionesEfectivo : listaDenominacion) {
            int entityCantidad = denominacionesEfectivo.getCantidad();
            do {
                BigDecimal total = resulList.stream().map(DenominacionesEfectivo::getDenominacion).reduce(BigDecimal.ZERO, BigDecimal::add);
                if (total.compareTo(cantidad) >= 0) {
                    break;
                } else {
                    BigDecimal subTotal = total.add(denominacionesEfectivo.getDenominacion());
                    if (subTotal.compareTo(cantidad) > 0) {
                        break;
                    }
                    resulList.add(denominacionesEfectivo);
                    entityCantidad--;
                }
            } while (entityCantidad > 0);
        }
        return resulList;
    }

    public List<BigDecimal> cantidaToDenominacion(BigDecimal cantidad, List<DenominacionesEfectivo> lista) {
        Long decimal = cantidad.longValue();
        BigDecimal fraccion = cantidad.remainder(BigDecimal.ONE);
        List<String> denominaciones = new ArrayList<>();
        int posicion = countChars(decimal.toString());
        for (char c : decimal.toString().toCharArray()) {
            int den = Character.getNumericValue(c);
            String result = String.format("%-" + (posicion--) + "s", den).replace(' ', '0');
            denominaciones.add(result);
        }
        denominaciones.add(fraccion.toString());
        return denominaciones.stream().map(BigDecimal::new).collect(Collectors.toList());
    }

    public int countChars(String valor) {
        return valor != null ? (int) valor.chars().count() : 0;
    }

}

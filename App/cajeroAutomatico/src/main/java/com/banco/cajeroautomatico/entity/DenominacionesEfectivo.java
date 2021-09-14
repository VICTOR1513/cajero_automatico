package com.banco.cajeroautomatico.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "denominaciones_efectivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DenominacionesEfectivo.findAll", query = "SELECT d FROM DenominacionesEfectivo d WHERE d.cantidad > 0")
    ,@NamedQuery(name = "DenominacionesEfectivo.findAllSuma", query = "SELECT  SUM(d.denominacion*d.cantidad) FROM DenominacionesEfectivo d WHERE d.cantidad > 0")
})
public class DenominacionesEfectivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "denominacion")
    private BigDecimal denominacion;

    public DenominacionesEfectivo() {
    }

    public DenominacionesEfectivo(Integer id) {
        this.id = id;
    }

    public DenominacionesEfectivo(Integer id, String tipo, Integer cantidad, BigDecimal denominacion) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.denominacion = denominacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DenominacionesEfectivo)) {
            return false;
        }
        DenominacionesEfectivo other = (DenominacionesEfectivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.banco.cajeroautomatico.entity.DenominacionesEfectivo[ id=" + id + " ]";
    }

}

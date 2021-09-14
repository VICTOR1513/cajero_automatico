package com.banco.cajeroautomatico.utilidades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Persistence {

    @PersistenceContext(unitName = "com.banco_cajeroAutomatico_war_1.0-SNAPSHOTPU")
    private EntityManager mysql;

    public EntityManager getMysql() {
        return mysql;
    }

}

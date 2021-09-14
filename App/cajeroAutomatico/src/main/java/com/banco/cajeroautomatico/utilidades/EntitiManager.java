package com.banco.cajeroautomatico.utilidades;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class EntitiManager<Entity> {

    private static final Logger LOG = Logger.getLogger(EntitiManager.class.getName());

    private Class<Entity> entityClass;

    public EntitiManager(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract Persistence getEntityManager();

    public Entity saveOrEdit(Entity entity, Object id) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Entity>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<Entity>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<Entity> cv = iterator.next();
                LOG.log(Level.SEVERE, "Error en la entidad: {0}", cv.getMessage());
                LOG.log(Level.SEVERE, "Error en la entidad: {0}", cv.getPropertyPath());
            }
        }
        if (id.equals(0)) {
            getEntityManager().getMysql().persist(entity);
        } else {
            getEntityManager().getMysql().merge(entity);
        }
        getEntityManager().getMysql().flush();
        return entity;
    }

    public Entity search(Object id, int institucion) {
        return getEntityManager().getMysql().find(entityClass, id);
    }

}

package ir.maktab32.java.homeworks.hw9.articles.config.hibernate.repositories;

import ir.maktab32.java.homeworks.hw9.articles.config.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public abstract class CrudRepository<Entity, ID extends Serializable> {

    protected abstract Class<Entity> getEntityClass();

    private Session getSession(){
        return HibernateUtil.getSession();
    }

    protected Entity save(Entity entity){
        getSession().beginTransaction();
        getSession().save(entity);
        getSession().getTransaction().commit();
        return entity;
    }

    protected Entity update(Entity entity){
        getSession().beginTransaction();
        getSession().update(entity);
        getSession().getTransaction().commit();
        return entity;
    }

    protected void remove(Entity entity){
        getSession().beginTransaction();
        getSession().remove(entity);
        getSession().getTransaction().commit();
    }

    protected void removeById(ID id){
        Entity entity = findById(id);
        if (entity != null) {
            getSession().beginTransaction();
            getSession().remove(entity);
            getSession().getTransaction().commit();
        }
    }

    protected List<Entity> findAll(){
        getSession().beginTransaction();
        //todo check for errors. i didn't set Query<Entity>
        Query query = getSession().createQuery("from " + getEntityClass().getName(), getEntityClass());
        List<Entity> entities = query.list();
        getSession().getTransaction().commit();
        return entities;
    }

    protected Entity findById(ID id){
        getSession().beginTransaction();
        Entity entity = getSession().load(getEntityClass(), id);
        getSession().getTransaction().commit();
        return entity;
    }
}

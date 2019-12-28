package ir.maktab32.java.homeworks.hw9.articles.repositories;

import ir.maktab32.java.homeworks.hw9.articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw9.articles.entities.Role;

public class RoleRepository extends CrudRepository<Role, Long> {
    @Override
    protected Class getEntityClass() {
        return Role.class;
    }

    private static RoleRepository roleRepository;
    public static RoleRepository getInstance(){
        if (roleRepository == null)
            roleRepository = new RoleRepository();
        return roleRepository;
    }

    private RoleRepository(){}
}

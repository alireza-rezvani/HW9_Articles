package ir.maktab32.java.homeworks.hw9.articles.repositories;

import ir.maktab32.java.homeworks.hw9.articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw9.articles.entities.User;

public class UserRepository extends CrudRepository<User, Long> {

    @Override
    protected Class getEntityClass() {
        return User.class;
    }

    private static UserRepository userRepository;
    public static UserRepository getInstance(){
        if (userRepository == null)
            userRepository = new UserRepository();
        return userRepository;
    }

    private UserRepository(){}
}

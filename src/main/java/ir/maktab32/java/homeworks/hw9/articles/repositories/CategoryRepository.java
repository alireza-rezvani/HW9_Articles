package ir.maktab32.java.homeworks.hw9.articles.repositories;

import ir.maktab32.java.homeworks.hw9.articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw9.articles.entities.Category;

public class CategoryRepository extends CrudRepository<Category, Long> {

    @Override
    protected Class getEntityClass() {
        return Category.class;
    }

    private static CategoryRepository categoryRepository;
    public static CategoryRepository getInstance(){
        if (categoryRepository == null)
            categoryRepository = new CategoryRepository();
        return categoryRepository;
    }

    private CategoryRepository(){}
}

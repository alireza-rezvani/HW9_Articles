package ir.maktab32.java.homeworks.hw9.articles.repositories;

import ir.maktab32.java.homeworks.hw9.articles.config.hibernate.repositories.CrudRepository;
import ir.maktab32.java.homeworks.hw9.articles.entities.Tag;

public class TagRepository extends CrudRepository<Tag, Long> {
    @Override
    protected Class getEntityClass() {
        return Tag.class;
    }

    private static TagRepository tagRepository;
    public static TagRepository getInstance(){
        if (tagRepository == null)
            tagRepository = new TagRepository();
        return tagRepository;
    }

    private TagRepository(){}
}

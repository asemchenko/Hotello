package example.company.model.dao.api;

import example.company.model.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T extends Entity> {
    void create(T entity);

    Optional<T> findById(long id);

    List<T> findAll();

    void update(T t);

    void delete(long id);
}

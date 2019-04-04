package example.company.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    void create(T entity);

    Optional<T> findById(int id);

    List<T> findAll();

    void update(T t);

    void delete(int id);
}

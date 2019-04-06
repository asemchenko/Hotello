package example.company.model.dao.implementation;

import example.company.model.dao.ApartmentDao;
import example.company.model.entity.Apartment;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcApartmentDao implements ApartmentDao {
    private Connection connection;

    public JdbcApartmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Apartment entity) {

    }

    @Override
    public Optional<Apartment> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Apartment> findAll() {
        return null;
    }

    @Override
    public void update(Apartment apartment) {

    }

    @Override
    public void delete(int id) {

    }
}

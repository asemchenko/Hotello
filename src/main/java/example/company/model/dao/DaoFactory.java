package example.company.model.dao;

public interface DaoFactory extends AutoCloseable {
    UserDao getUserDao();

    ApartmentDao getApartmentDao();

    void close();
}

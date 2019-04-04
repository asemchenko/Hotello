package example.company.model.dao;

public interface DaoFactory extends AutoCloseable {
    UserDao getUserDao();

    void close();
}

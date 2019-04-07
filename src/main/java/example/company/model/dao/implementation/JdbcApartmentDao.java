package example.company.model.dao.implementation;

import example.company.model.dao.ApartmentDao;
import example.company.model.entity.Apartment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcApartmentDao extends JdbcGenericDao<Apartment> implements ApartmentDao {
    public static final String FIND_ALL_QUERY = "SELECT apartment_id, id_apartment_class, title, description, places_amount, rooms_amount, price_per_day FROM apartments";
    /* TODO
        надо добавить работу с apartment_classes, но как? Сделать enum для класса или позволить динамически создавать новые классы?
        не забудь поменять текст INSERT_QUERY
     */
    private static final String INSERT_QUERY = "INSERT INTO apartments(id_apartment_class, title, description, places_amount, rooms_amount, price_per_day) VALUES (1, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT apartment_id, id_apartment_class, title, description, places_amount, rooms_amount, price_per_day FROM apartments WHERE apartment_id=?";

    public JdbcApartmentDao(Connection connection) {
        super(connection);
    }

    @Override
    protected void setUpdateQueryParams(PreparedStatement s, Apartment apartment) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getUpdateQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected void setInsertQueryParams(PreparedStatement s, Apartment apartment) throws SQLException {
        setApartmentParams(s, apartment, 0);
    }

    private int setApartmentParams(PreparedStatement s, Apartment apartment, int offset) throws SQLException {
        s.setString(1 + offset, apartment.getTitle());
        s.setString(2 + offset, apartment.getDescription());
        s.setLong(3 + offset, apartment.getPlacesAmount());
        s.setLong(4 + offset, apartment.getRoomsAmount());
        s.setLong(5 + offset, apartment.getPricePerDay());
        return 5 + 1 + offset;
    }

    @Override
    protected Apartment getFromResultSet(ResultSet resultSet) throws SQLException {
        Apartment apartment = new Apartment();
        apartment.setId(resultSet.getLong("apartment_id"));
        apartment.setTitle(resultSet.getString("title"));
        apartment.setPlacesAmount(resultSet.getLong("places_amount"));
        apartment.setRoomsAmount(resultSet.getLong("rooms_amount"));
        apartment.setDescription(resultSet.getString("description"));
        apartment.setPricePerDay(resultSet.getLong("price_per_day"));
        return apartment;
    }

    @Override
    protected void setFindByIdQueryParams(PreparedStatement s, long id) throws SQLException {
        s.setLong(1, id);
    }

    @Override
    protected String getFindByIdQuery() {
        return FIND_BY_ID_QUERY;
    }

    @Override
    protected String getFindAllQuery() {
        return FIND_ALL_QUERY;
    }
}

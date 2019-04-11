package example.company.model.dao.jdbc.concreteDao;

import example.company.model.dao.api.concreteDao.ApartmentDao;
import example.company.model.dao.jdbc.JdbcGenericDao;
import example.company.model.entity.Apartment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcApartmentDao extends JdbcGenericDao<Apartment> implements ApartmentDao {
    public static final String FIND_ALL_QUERY = "SELECT apartment_id, title, description, places_amount, rooms_amount, price_per_day, stars_amount FROM apartments";
    private static final String INSERT_QUERY = "INSERT INTO apartments(title, description, places_amount, rooms_amount, price_per_day, stars_amount) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT apartment_id, title, description, places_amount, rooms_amount, price_per_day, stars_amount FROM apartments WHERE apartment_id=?";

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
        s.setShort(3 + offset, apartment.getPlacesAmount());
        s.setShort(4 + offset, apartment.getRoomsAmount());
        s.setLong(5 + offset, apartment.getPricePerDay());
        s.setShort(6 + offset, apartment.getStarsAmount());
        return 6 + 1 + offset;
    }

    @Override
    protected Apartment getFromResultSet(ResultSet resultSet) throws SQLException {
        Apartment apartment = new Apartment();
        apartment.setId(resultSet.getLong("apartment_id"));
        apartment.setTitle(resultSet.getString("title"));
        apartment.setPlacesAmount(resultSet.getShort("places_amount"));
        apartment.setRoomsAmount(resultSet.getShort("rooms_amount"));
        apartment.setDescription(resultSet.getString("description"));
        apartment.setPricePerDay(resultSet.getLong("price_per_day"));
        apartment.setStarsAmount(resultSet.getShort("stars_amount"));
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

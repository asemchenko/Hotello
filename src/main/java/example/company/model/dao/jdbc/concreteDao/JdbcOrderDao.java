package example.company.model.dao.jdbc.concreteDao;

import example.company.model.dao.api.concreteDao.ApartmentDao;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.dao.api.concreteDao.UserDao;
import example.company.model.dao.jdbc.JdbcGenericDao;
import example.company.model.entity.Apartment;
import example.company.model.entity.Bill;
import example.company.model.entity.Order;
import example.company.model.entity.User;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.nonNull;

public class JdbcOrderDao extends JdbcGenericDao<Order> implements OrderDao {
    private static final String INSERT_QUERY = "INSERT INTO orders (bill_id, apartment_id, user_id, check_in, check_out, price_per_day, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT id_order, bill_id, apartment_id, user_id, check_in, check_out, price_per_day, total_price, creation_time FROM orders WHERE id_order=?";
    public static final String FIND_BY_USER_QUERY = "SELECT id_order, bill_id, apartment_id, user_id, check_in, check_out, price_per_day, total_price, creation_time FROM orders WHERE user_id=?";

    private UserDao userDao;
    private ApartmentDao apartmentDao;

    public JdbcOrderDao(Connection connection, UserDao userDao, ApartmentDao apartmentDao) {
        super(connection);
        this.userDao = userDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    protected void setUpdateQueryParams(PreparedStatement s, Order entity) throws SQLException {
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
    protected void setInsertQueryParams(PreparedStatement s, Order order) throws SQLException {
        setOrderParams(s, order, 0);
    }

    private void setOrderParams(PreparedStatement s, Order order, int offset) throws SQLException {
        // setting bill id if bill is present, null otherwise
        Bill bill = order.getBill();
        if (nonNull(bill)) {
            s.setLong(1 + offset, bill.getId());
        } else {
            s.setNull(1 + offset, Types.INTEGER);
        }
        // apartment_id
        // FIXME нарушение принципа Деметры?
        s.setLong(2 + offset, order.getApartment().getId());
        // user_id
        s.setLong(3 + offset, order.getUser().getId());
        // check in date
        s.setObject(4 + offset, order.getCheckInDate());
        // check out date
        s.setObject(5 + offset, order.getCheckOutDate());
        // price per day
        s.setLong(6 + offset, order.getPricePerDayAtTheTimeOfOrder());
        // total price
        s.setLong(7 + offset, order.getTotalPrice());
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
    protected Order getFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id_order"));
        // setting bill if present, null otherwise
        order.setBill(loadBill(resultSet));
        // reading apartment
        order.setApartment(loadApartment(resultSet));
        // reading user
        order.setUser(loadUser(resultSet));
        // reading remain primitive fields
        order.setCheckInDate(resultSet.getObject("check_in", LocalDate.class));
        order.setCheckOutDate(resultSet.getObject("check_out", LocalDate.class));
        order.setPricePerDayAtTheTimeOfOrder(resultSet.getLong("price_per_day"));
        order.setTotalPrice(resultSet.getLong("total_price"));
        order.setCreationTime(resultSet.getObject("creation_time", Instant.class));
        return order;
    }

    private Bill loadBill(ResultSet resultSet) throws SQLException {
        long billId = resultSet.getLong("bill_id");
        if (resultSet.wasNull()) {
            return null;
        } else {
            // TODO implement reading bill
            throw new UnsupportedOperationException("reading bill is unsupported yet");
        }
    }

    private Apartment loadApartment(ResultSet resultSet) throws SQLException {
        long apartmentId = resultSet.getLong("apartment_id");
        return apartmentDao.findById(apartmentId).get();
    }

    private User loadUser(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong("user_id");
        return userDao.findById(userId).get();
    }

    @Override
    protected String getFindAllQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getByUser(User user) {
       return findList(FIND_BY_USER_QUERY, (s) -> {
           try {
               s.setLong(1, user.getId());
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       });
    }
}

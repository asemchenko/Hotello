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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Objects.nonNull;

public class JdbcOrderDao extends JdbcGenericDao<Order> implements OrderDao {
    private static final String FIND_BY_USER_QUERY = "SELECT id_order, bill_id, apartment_id, user_id, check_in, check_out, price_per_day, total_price, creation_time, order_status FROM orders WHERE user_id=?";
    private static final String INSERT_QUERY = "INSERT INTO orders (bill_id, apartment_id, user_id, check_in, check_out, price_per_day, total_price, creation_time, order_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT id_order, bill_id, apartment_id, user_id, check_in, check_out, price_per_day, total_price, creation_time, order_status FROM orders WHERE id_order=?";
    private static final String FIND_ALL_QUERY = "SELECT id_order, bill_id, apartment_id, user_id, check_in, check_out, price_per_day, total_price, creation_time, order_status FROM orders";
    private static final String UPDATE_QUERY = "UPDATE orders SET bill_id=?, apartment_id=?, user_id=?, check_in=?, check_out=?, price_per_day=?, total_price=?, creation_time=?, order_status=? WHERE id_order=?";
    private UserDao userDao;
    private ApartmentDao apartmentDao;

    public JdbcOrderDao(Connection connection, UserDao userDao, ApartmentDao apartmentDao) {
        super(connection);
        this.userDao = userDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    protected void setUpdateQueryParams(PreparedStatement s, Order order) throws SQLException {
        int nextParameterIndex = setOrderParams(s, order, 0);
        s.setLong(nextParameterIndex, order.getId());
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected void setInsertQueryParams(PreparedStatement s, Order order) throws SQLException {
        setOrderParams(s, order, 0);
    }

    private int setOrderParams(PreparedStatement s, Order order, int offset) throws SQLException {
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
        setLocalDate(s, 4 + offset, order.getCheckInDate());
        // check out date
        setLocalDate(s, 5 + offset, order.getCheckOutDate());
        // price per day
        s.setLong(6 + offset, order.getPricePerDayAtTheTimeOfOrder());
        // total price
        s.setLong(7 + offset, order.getTotalPrice());
        // creation time
        s.setTimestamp(8 + offset, Timestamp.from(order.getCreationTime()));
        // order status
        s.setString(9 + offset, order.getStatus().name());
        return 9 + 1 + offset;
    }

    private void setLocalDate(PreparedStatement s, int parameterIndex, LocalDate date) throws SQLException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = dateTimeFormatter.format(date);
        s.setString(parameterIndex, formattedDate);
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
        order.setCreationTime(resultSet.getTimestamp("creation_time").toInstant());
        order.setStatus(resultSet.getString("order_status"));
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
        return FIND_ALL_QUERY;
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

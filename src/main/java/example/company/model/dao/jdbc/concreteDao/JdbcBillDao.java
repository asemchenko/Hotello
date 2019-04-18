package example.company.model.dao.jdbc.concreteDao;

import example.company.model.dao.api.concreteDao.BillDao;
import example.company.model.dao.jdbc.JdbcGenericDao;
import example.company.model.entity.Bill;

import java.sql.*;
import java.time.Instant;

public class JdbcBillDao extends JdbcGenericDao<Bill> implements BillDao {

    private static final String INSERT_QUERY = "INSERT INTO bills(amount_due, bank_account_number, payment_transaction_id, creation_time) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE bills SET amount_due=?, bank_account_number=?, payment_transaction_id=?, creation_time=? WHERE bill_id=?";
    private static final String SELECT_BY_ID_QUERY = "SELECT bill_id, amount_due, bank_account_number, payment_transaction_id, creation_time FROM bills WHERE bill_id=?";
    private static final String SELECT_ALL_QUERY = "SELECT bill_id, amount_due, bank_account_number, payment_transaction_id, creation_time FROM bills";

    public JdbcBillDao(Connection connection) {
        super(connection);
    }

    @Override
    protected void setUpdateQueryParams(PreparedStatement s, Bill bill) throws SQLException {
        int nextParamId = setBillParams(s, bill, 0);
        s.setLong(nextParamId, bill.getId());
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
    protected void setInsertQueryParams(PreparedStatement s, Bill bill) throws SQLException {
        setBillParams(s, bill, 0);
    }

    @Override
    protected void setFindByIdQueryParams(PreparedStatement s, long id) throws SQLException {
        s.setLong(1, id);
    }

    @Override
    protected String getFindByIdQuery() {
        return SELECT_BY_ID_QUERY;
    }

    @Override
    protected Bill getFromResultSet(ResultSet resultSet) throws SQLException {
        long billId = resultSet.getLong("bill_id");
        long amountDue = resultSet.getLong("amount_due");
        String bankAccountNumber = resultSet.getString("bank_account_number");
        String paymentTransactionId = resultSet.getString("payment_transaction_id");
        Instant creationTime = resultSet.getTimestamp("creation_time").toInstant();
        Bill bill = new Bill(amountDue, bankAccountNumber);
        bill.setId(billId);
        bill.setPaymentTransactionId(paymentTransactionId);
        bill.setCreationTime(creationTime);
        return bill;
    }

    @Override
    protected String getFindAllQuery() {
        return SELECT_ALL_QUERY;
    }

    private int setBillParams(PreparedStatement s, Bill bill, int offset) throws SQLException {
        s.setLong(1 + offset, bill.getAmountDue());
        s.setString(2 + offset, bill.getBankAccountNumber());
        s.setString(3 + offset, bill.getPaymentTransactionId());
        s.setTimestamp(4 + offset, Timestamp.from(bill.getCreationTime()));
        return 5 + offset;
    }
}

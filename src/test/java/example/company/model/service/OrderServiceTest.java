package example.company.model.service;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.entity.Order;
import example.company.model.service.exceptions.ApartmentAlreadyBookedException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.function.Supplier;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderService orderService;
    private OrderDao orderDao;
    private DaoFactory daoFactory;

    @Before
    public void setUp() throws Exception {
        PaymentService paymentService = getPaymentServiceMock();
        Supplier<DaoFactory> supplier = getSupplierMock();
        orderService = new OrderService(paymentService, supplier);
    }

    private PaymentService getPaymentServiceMock() {
        PaymentService paymentServiceMock = mock(PaymentService.class);
        when(paymentServiceMock.processPayment(anyString(), any())).thenReturn("some id 12345");
        return paymentServiceMock;
    }

    private Supplier<DaoFactory> getSupplierMock() {
        Connection connectionMock = mock(Connection.class);
        orderDao = mock(OrderDao.class);
        daoFactory = mock(DaoFactory.class);
        when(daoFactory.getOrderDao()).thenReturn(orderDao);
        when(daoFactory.getCurrentConnection()).thenReturn(connectionMock);
        @SuppressWarnings("unchecked")
        Supplier<DaoFactory> daoFactorySupplierMock = mock(Supplier.class);
        when(daoFactorySupplierMock.get()).thenReturn(daoFactory);
        return daoFactorySupplierMock;
    }

    @Test
    public void makeOrderSuccessful() throws ApartmentAlreadyBookedException {
        Order order = mock(Order.class);
        orderService.makeOrder(order);
        verify(orderDao, times(1)).create(order);
        verify(daoFactory, times(1)).close();
    }

    @Test(expected = ApartmentAlreadyBookedException.class)
    public void makeOrderAlreadyBookedApartment() throws ApartmentAlreadyBookedException {
        doAnswer((a) -> {
                    throw new RuntimeException(
                            new SQLIntegrityConstraintViolationException()
                    );
                }
        ).when(orderDao).create(any());
        orderService.makeOrder(mock(Order.class));
    }
}
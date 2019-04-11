package example.company.model.dao.api.concreteDao;

import example.company.model.dao.api.GenericDao;
import example.company.model.entity.Order;
import example.company.model.entity.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getByUser(User user);
}

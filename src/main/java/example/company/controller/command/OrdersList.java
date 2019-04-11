package example.company.controller.command;

import example.company.model.entity.Order;
import example.company.model.entity.User;
import example.company.model.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrdersList implements Command {
    private OrderService orderService;

    public OrdersList(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Order> ordersList = orderService.getOrders(getUser(request));
        request.setAttribute("orders", ordersList);
        request.getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
    }

    private User getUser(HttpServletRequest request) {
        return (User) request.getSession(false).getAttribute("user");
    }
}

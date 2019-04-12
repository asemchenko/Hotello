package example.company.controller.command.order;

import example.company.controller.command.Command;
import example.company.model.entity.Order;
import example.company.model.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminOrdersList implements Command {
    private OrderService orderService;

    public AdminOrdersList(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Order> allOrders = orderService.getAllOrdersSortedByDate();
        request.setAttribute("orders", allOrders);
        request.getRequestDispatcher("/WEB-INF/admin/orders.jsp").forward(request, response);
    }
}

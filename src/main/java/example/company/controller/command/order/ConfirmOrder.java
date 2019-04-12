package example.company.controller.command.order;

import example.company.controller.command.Command;
import example.company.model.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmOrder implements Command {
    private OrderService orderService;

    public ConfirmOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        orderService.confirmOrder(orderId);
        response.sendRedirect("/app/allOrders");
    }
}

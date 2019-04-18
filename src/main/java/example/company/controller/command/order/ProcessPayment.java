package example.company.controller.command.order;

import example.company.controller.command.Command;
import example.company.model.service.OrderService;
import example.company.model.service.PaymentCredentials;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class ProcessPayment implements Command {
    private OrderService orderService;

    public ProcessPayment(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        PaymentCredentials credentials = new PaymentCredentials();
        credentials.setOwnerName(request.getParameter("cardOwnerFullName"));
        credentials.setCardNumber(request.getParameter("creditCardNumber"));
        credentials.setExpiration(YearMonth.parse(request.getParameter("expirationDate"), DateTimeFormatter.ofPattern("MM/yy")));
        credentials.setCvvCode(Short.parseShort(request.getParameter("cvvCode")));
        orderService.payForOrder(orderId, credentials);
        response.sendRedirect("/app/orders");
    }
}

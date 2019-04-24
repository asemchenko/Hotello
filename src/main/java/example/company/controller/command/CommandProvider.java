package example.company.controller.command;

import example.company.controller.command.order.*;
import example.company.controller.command.search.ApartmentDetail;
import example.company.controller.command.search.FindApartment;
import example.company.controller.command.user.*;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.service.ApartmentService;
import example.company.model.service.OrderService;
import example.company.model.service.PaymentService;
import example.company.model.service.UserService;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static CommandProvider instance;
    private final Map<String, Command> commandMap = new HashMap<>();

    private CommandProvider() {
        initCommandMap();
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            synchronized (CommandProvider.class) {
                if (instance == null) {
                    instance = new CommandProvider();
                }
            }
        }
        return instance;
    }

    private void initCommandMap() {
        commandMap.put("signIn", new SignIn(getUserService()));
        commandMap.put("signUp", new SignUp(getUserService()));
        commandMap.put("logout", new Logout());
        commandMap.put("orders", new OrdersList(getOrderService()));
        commandMap.put("profile", new Profile());
        commandMap.put("changePassword", new ChangePassword(getUserService()));
        commandMap.put("apartment", new ApartmentDetail(getApartmentService()));
        commandMap.put("findApartment", new FindApartment(getApartmentService()));
        commandMap.put("booking", new Booking(getApartmentService()));
        commandMap.put("makeOrder", new MakeOrder(getApartmentService(), getOrderService()));
        commandMap.put("allOrders", new AdminOrdersList(getOrderService()));
        commandMap.put("confirmOrder", new ConfirmOrder(getOrderService()));
        commandMap.put("disapproveOrder", new DisapproveOrder(getOrderService()));
        commandMap.put("payForOrder", new PayForOrder(getOrderService()));
        commandMap.put("processPayment", new ProcessPayment(getOrderService()));
    }
    
    private UserService getUserService() {
        return new UserService();
    }
    
    private OrderService getOrderService() {
        return new OrderService(new PaymentService(), JdbcDaoFactory::getFactory);
    }
    
    private ApartmentService getApartmentService() {
        return new ApartmentService();
    }

    public Command getCommand(String identifier) {
        return commandMap.getOrDefault(identifier, null);
    }
}

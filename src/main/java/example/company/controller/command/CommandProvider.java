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
        // FIXME используй один и тот же экземпляр каждого сервиса
        commandMap.put("signIn", new SignIn(new UserService()));
        commandMap.put("signUp", new SignUp(new UserService()));
        commandMap.put("logout", new Logout());
        commandMap.put("orders", new OrdersList(new OrderService(new PaymentService(), JdbcDaoFactory::getFactory)));
        commandMap.put("profile", new Profile());
        commandMap.put("changePassword", new ChangePassword(new UserService()));
        commandMap.put("apartment", new ApartmentDetail(new ApartmentService()));
        commandMap.put("findApartment", new FindApartment(new ApartmentService()));
        commandMap.put("booking", new Booking(new ApartmentService()));
        commandMap.put("makeOrder", new MakeOrder(new ApartmentService(), new OrderService(new PaymentService(), JdbcDaoFactory::getFactory)));
        commandMap.put("allOrders", new AdminOrdersList(new OrderService(new PaymentService(), JdbcDaoFactory::getFactory)));
        commandMap.put("confirmOrder", new ConfirmOrder(new OrderService(new PaymentService(), JdbcDaoFactory::getFactory)));
        commandMap.put("disapproveOrder", new DisapproveOrder(new OrderService(new PaymentService(), JdbcDaoFactory::getFactory)));
        commandMap.put("payForOrder", new PayForOrder(new OrderService(new PaymentService(), JdbcDaoFactory::getFactory)));
        commandMap.put("processPayment", new ProcessPayment(new OrderService(new PaymentService(), JdbcDaoFactory::getFactory)));
    }

    public Command getCommand(String identifier) {
        return commandMap.getOrDefault(identifier, null);
    }
}

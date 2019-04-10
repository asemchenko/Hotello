package example.company.controller.command;

import example.company.model.service.ApartmentService;
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
        commandMap.put("signIn", new SignIn(new UserService()));
        commandMap.put("signUp", new SignUp(new UserService()));
        commandMap.put("logout", new Logout());
        commandMap.put("orders", new OrdersList());
        commandMap.put("profile", new Profile());
        commandMap.put("changePassword", new ChangePassword(new UserService()));
        commandMap.put("apartment", new ApartmentDetail(new ApartmentService()));
        commandMap.put("findApartment", new FindApartment(new ApartmentService()));
        commandMap.put("booking", new Booking(new ApartmentService()));
    }

    public Command getCommand(String identifier) {
        return commandMap.getOrDefault(identifier, null);
    }
}

package example.company.controller.command;

import example.company.model.service.UserService;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static CommandProvider instance;
    private Map<String, Command> commandMap = new HashMap<>();

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
    }

    public Command getCommand(String identifier) {
        return commandMap.getOrDefault(identifier, null);
    }
}

package example.company.controller.command;

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
        commandMap.put("signIn", new SignInCommand());
    }

    public Command getCommand(String identifier) {
        return commandMap.getOrDefault(identifier, null);
    }
}

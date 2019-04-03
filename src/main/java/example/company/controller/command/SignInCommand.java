package example.company.controller.command;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // TODO проверка логина и пароля
        return "/signIn.jsp";
    }
}

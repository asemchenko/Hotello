package example.company.controller.command;

import example.company.model.entity.User;
import example.company.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
    private UserService userService;

    public SignIn(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // TODO проверка логина и пароля
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (userService.signIn(email, password)) {
            // TODO положить инфу о пользователе в сессию
            User user = userService.getUserByEmail(email).get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.println("Successfully login");
            makeRedirect(response, "/");

        } else {
            // TODO передай ошибку где-то тут
            System.out.println("Error: invalid login/password");
            makeRedirect(response, "/signIn.jsp");
        }
    }

    private void makeRedirect(HttpServletResponse response, String uri) {
        try {
            response.sendRedirect(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

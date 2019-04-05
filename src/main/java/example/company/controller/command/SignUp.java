package example.company.controller.command;

import example.company.model.entity.User;
import example.company.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUp implements Command {
    private UserService userService;

    public SignUp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // TODO add error handling
        // TODO придумай способ передавать сообщение об ошибке/успехе отсюда
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        // setting user status
        user.setStatus(User.UserStatus.CLIENT);
        String password = (request.getParameter("password"));
        // save user to database
        userService.signUp(user, password);
        makeRedirect(response, "/signIn.jsp");
    }

    // TODO дублирование кода метода makeRedirect
    private void makeRedirect(HttpServletResponse response, String uri) {
        try {
            response.sendRedirect(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package example.company.controller.command;

import example.company.model.entity.User;
import example.company.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUp implements Command {
    private final UserService userService;

    public SignUp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        response.sendRedirect("/signIn.jsp");
    }
}

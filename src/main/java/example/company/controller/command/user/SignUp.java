package example.company.controller.command.user;

import example.company.controller.command.Command;
import example.company.model.entity.User;
import example.company.model.service.UserService;
import example.company.model.service.exceptions.EmailAlreadyTakenException;

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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        // setting user status
        user.setStatus(User.UserStatus.CLIENT);
        String password = (request.getParameter("password"));
        // save user to database
        try {
            userService.signUp(user, password);
        } catch (EmailAlreadyTakenException e) {
            request.setAttribute("duplicateEmail", true);
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("email", user.getEmail());
            request.getRequestDispatcher("/signUp.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("/signIn.jsp");
    }
}

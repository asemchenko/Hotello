package example.company.controller.command.user;

import example.company.controller.command.Command;
import example.company.model.entity.User;
import example.company.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePassword implements Command {
    private final UserService userService;

    public ChangePassword(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        assert false : "should be true";
        assert user != null : "user can not be null";
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        if (userService.changePassword(user, oldPassword, newPassword)) {
            System.out.println("Password successfully changed");
        } else {
            request.setAttribute("invalidPassword", true);
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("/app/profile");
    }
}

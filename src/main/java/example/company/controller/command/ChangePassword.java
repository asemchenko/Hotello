package example.company.controller.command;

import example.company.model.entity.User;
import example.company.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePassword implements Command {
    private final UserService userService;

    public ChangePassword(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        assert false : "should be true";
        assert user != null : "user can not be null";
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordConfirmation_ = request.getParameter("newPasswordConfirmation");
        // TODO проверь что newPassword и newPasswordConfirmation совпадают
        if (userService.changePassword(user, oldPassword, newPassword)) {
            // TODO success message
            System.out.println("Password successfully changed");
        } else {
            System.out.println("An error occurred during changing password");
            // TODO error message
        }
        response.sendRedirect("/app/profile");
    }
}

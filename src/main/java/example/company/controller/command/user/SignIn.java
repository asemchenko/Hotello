package example.company.controller.command.user;

import example.company.controller.command.Command;
import example.company.model.entity.User;
import example.company.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
    private static final Logger logger = LoggerFactory.getLogger(SignIn.class);
    private final UserService userService;

    public SignIn(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (userService.signIn(email, password)) {
            User user = userService.getUserByEmail(email).get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.println("Successfully login");
            logger.info("User {} has been successfully signed in. Request [addr: {}, session id: {}]", user, request.getRemoteAddr(), request.getSession().getId());
            response.sendRedirect("/");

        } else {
            logger.info("An attempt to sign in with email {} failed. Request [addr: {}, method: {}]", email, request.getRemoteAddr(), request.getMethod());
            request.setAttribute("invalidCredentials", true);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/signIn.jsp").forward(request, response);
        }
    }
}

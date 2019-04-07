package example.company.controller.command;

import example.company.model.entity.User;
import example.company.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
    private final UserService userService;

    public SignIn(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (userService.signIn(email, password)) {
            User user = userService.getUserByEmail(email).get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.println("Successfully login");
            response.sendRedirect("/");

        } else {
            // TODO передай ошибку где-то тут
            System.out.println("Error: invalid login/password");
            // TODO подумай как установаливать код ошибки, с учетом того что тут редирект
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/signIn.jsp");
//            try {
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//            } catch (ServletException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}

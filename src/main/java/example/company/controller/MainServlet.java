package example.company.controller;

import example.company.controller.command.Command;
import example.company.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        // FIXME delete this trash
        requestURI = requestURI.replaceAll("/app/", "");
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getCommand(requestURI);
        if (command == null) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            String redirect = command.execute(req);
        req.getRequestDispatcher(redirect).forward(req, resp);
//            resp.sendRedirect(redirect);
        }
    }
}

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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        requestURI = requestURI.replaceAll("/app/", "");
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getCommand(requestURI);
        if (command == null) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            command.execute(req, resp);
        }
    }
}

package org.example.rest;

import com.google.gson.Gson;
import org.example.dao.impl.UserDaoImpl;
import org.example.model.Message;
import org.example.services.UserServices;
import org.example.services.impl.UserServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class SignInController extends HttpServlet {
    UserServices userServices = UserServicesImpl.getUserServices();
    public final Gson gson = new Gson();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Message message = userServices.isExistsUser(username, password);
        String json = gson.toJson(message);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.getWriter().close();

    }
}

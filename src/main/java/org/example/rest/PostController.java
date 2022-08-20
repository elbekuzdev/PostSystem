package org.example.rest;

import com.google.gson.Gson;
import org.example.model.Message;
import org.example.model.Post;
import org.example.model.User;
import org.example.services.PostServices;
import org.example.services.impl.PostServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/post")
public class PostController extends HttpServlet {
    PostServices postServices = PostServicesImpl.getUserServices();
    private static Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String json = gson.toJson(postServices.getPost());
        resp.getWriter().print(json);
        resp.getWriter().close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        Message message = postServices.appendPost(new Post(text, user));
        String json = gson.toJson(message);
        resp.setContentType("application/json");

        resp.getWriter().print(json);
        resp.getWriter().close();
    }
}

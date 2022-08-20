package org.example.component;

import com.google.gson.Gson;
import org.example.dao.UserDao;
import org.example.dao.impl.MessageDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.model.Message;
import org.example.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@WebFilter("/*")
public class AuthFilter implements Filter {
    Gson gson = new Gson();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        UserDao userDao = UserDaoImpl.getUserDao();
        String requestURI = req.getRequestURI();

        if (requestURI.startsWith("/login") || requestURI.startsWith("/register")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String key = req.getHeader("key");
            if (key == null || key.isBlank() || key.isEmpty()) {
                Message message = MessageDao.findByStatusCode(120);
                String json = gson.toJson(message);
                resp.setContentType("application/json");
                resp.getWriter().print(json);
                resp.getWriter().close();
            } else {
                try {
                    String decode = new String(Base64.getDecoder().decode(key.getBytes()));
                    String[] keys = decode.split("&");
                    Optional<User> userOptional = userDao.findByUsernameAndPassword(keys[0], keys[1]);
                    if (userOptional.isPresent()) {
                        try {
                            long time = Long.parseLong(keys[2]);
                            if (time > (new Date().getTime()) - 1000 * 60 * 60 * 24 * 14){
                                HttpSession session = req.getSession();
                                session.setAttribute("user", userOptional.get());
                                filterChain.doFilter(servletRequest, servletResponse);
                            }else{
                                Message message = MessageDao.findByStatusCode(107);
                                String json = gson.toJson(message);
                                resp.setContentType("application/json");
                                resp.getWriter().print(json);
                                resp.getWriter().close();
                            }
                        } catch (Exception e) {
                            Message message = MessageDao.findByStatusCode(121);
                            String json = gson.toJson(message);
                            resp.setContentType("application/json");
                            resp.getWriter().print(json);
                            resp.getWriter().close();
                        }
                    } else {
                        Message message = MessageDao.findByStatusCode(105);
                        String json = gson.toJson(message);
                        resp.setContentType("application/json");
                        resp.getWriter().print(json);
                        resp.getWriter().close();
                    }
                } catch (Exception e) {
                    Message message = MessageDao.findByStatusCode(121);
                    String json = gson.toJson(message);
                    resp.setContentType("application/json");
                    resp.getWriter().print(json);
                    resp.getWriter().close();

                }

            }
        }
    }
}
package com.epam.ivanou.avia.web.user;

import com.epam.ivanou.avia.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private WebApplicationContext webAppCtx;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webAppCtx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = webAppCtx.getBean(UserService.class);
        req.setAttribute("userList",userService.getAll());
        req.getRequestDispatcher("/userList.jsp").forward(req,resp);
    }
}

package PartnerShop.autenticazione.service;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HomePageServlet", urlPatterns = "", loadOnStartup = 1)
public class HomePageServlet extends HttpServlet {


    public void init() {

    }



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
        dispatcher.forward(request, response);
    }
    public void destroy() {
    }
}
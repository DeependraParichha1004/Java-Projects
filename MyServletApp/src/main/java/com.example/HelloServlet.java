package com.example;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Simple Data Rendering
//         resp.setContentType("text/html");
//         String req1 = req.getParameter("fname");
//         String req2 = req.getParameter("lname");
         PrintWriter out = resp.getWriter();
         out.println("<h1>Hello from Servlet!</h1>");
         System.out.println("Hello");
        // req.setAttribute("req1", req1);
        // RequestDispatcher rd = req.getRequestDispatcher("demo");
        // rd.forward(req, resp);


        // Send Redirect for sending data from one application to another(one web page to payment gateway)
        // resp.sendRedirect("demo?k="+req1); // url rewriting

        // Session Management
        // int s = 12;
        // HttpSession session = req.getSession();
        // session.setAttribute("s",s);
        // resp.sendRedirect("demo");
    }
}

package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class DemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Simple Data Rendering
        // resp.setContentType("text/html");
        // String req1 = req.getParameter("fname");
        // String req2 = req.getParameter("lname");
        // PrintWriter out = resp.getWriter();
        // out.println("<h1>Hello from Servlet! You are in demo page</h1>"+req.getAttribute("req1"));

        // Request Dispatcher for connecting different servlets internally
        // RequestDispatcher rd = req.getRequestDispatcher("hello");
        // rd.forward(req, resp);

        // Data rendering using send redirect object
        // String s1 = req.getParameter("k");
        // PrintWriter out = resp.getWriter();
        // out.println("<h1>Hello from Servlet! You are in demo page</h1>"+s1);

        // get session data from different servlet(from hello servlet)
        // HttpSession session = req.getSession();
        // int s = (int) session.getAttribute("s");
        // PrintWriter pw = resp.getWriter();
        // pw.println("get Session Data "+ s);
    }
}

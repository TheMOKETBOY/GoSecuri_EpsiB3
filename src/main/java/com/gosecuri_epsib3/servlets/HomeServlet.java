package com.gosecuri_epsib3.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "AUTH" ) == null ) {
            response.sendRedirect("/javaMspr_war_exploded/Auth");

        }else{

            response.setContentType("text/html");
            response.setCharacterEncoding( "UTF-8" );
            PrintWriter out = response.getWriter();
            out.println("Home");

        }

    }
}

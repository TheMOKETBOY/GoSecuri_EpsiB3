package com.gosecuri_epsib3.servlets.Auth;

import com.gosecuri_epsib3.servlets.HomeServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class LogoutServlet extends HomeServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Writer out =  response.getWriter();
        if ( session.getAttribute( "AUTH" ) != null ) {
            session.invalidate();

              out.write("Logout : del session");
        }else{

            out.write("Logout");


        }
        response.sendRedirect("/");
    }
}

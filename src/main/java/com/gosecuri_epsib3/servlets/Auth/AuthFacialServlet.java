package com.gosecuri_epsib3.servlets.Auth;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


public class AuthFacialServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {



        this.getServletContext().getRequestDispatcher( "/WEB-INF/loginForm.jsp" ).forward( request, response );

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<String> whiteliste = new ArrayList<String>();
        whiteliste.add("mitden");
        whiteliste.add("johndoe");
        String login = request.getParameter("login");

System.out.println("login : "+login);

    if (whiteliste.contains(login)){
        session.setAttribute("AUTH",login);
        response.sendRedirect("/javaMspr_war_exploded/");
    }else {

        request.setAttribute("errors","L'utilisateur n'est pas autorisé");
        this.getServletContext().getRequestDispatcher("/WEB-INF/loginForm.jsp").forward(request, response);

    }

    }
}

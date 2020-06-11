package com.gosecuri_epsib3.servlets;

import com.gosecuri_epsib3.beans.DAO;
import com.gosecuri_epsib3.beans.Equipement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import static com.gosecuri_epsib3.beans.DAO.getEquipements;

public class HomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if ( session.getAttribute( "AUTH" ) == null ) {
            response.sendRedirect("/Auth/login");

        }else{
            this.getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);


        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        if ( session.getAttribute( "AUTH" ) == null ) {
            response.sendRedirect("/Auth/login");

        }else{
            response.setContentType("text/html");
            String[] equipements = new String[0];
            if (!(request.getParameterValues("Equipements") == null)){
                equipements =  request.getParameterValues("Equipements");
            }


            Writer out =  response.getWriter();

            out.write("Vous avez  : ");
            out.write(equipements.length+" Equipement(s)");
            out.write("<ul>");
            for (String equipement : equipements) {




                      out.write("<li>"+equipement+"</li>");


            }
            out.write("</ul>");
        }
    }
}

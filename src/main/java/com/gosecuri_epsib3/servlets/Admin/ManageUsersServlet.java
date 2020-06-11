package com.gosecuri_epsib3.servlets.Admin;


import com.gosecuri_epsib3.beans.DAO;
import com.gosecuri_epsib3.beans.User;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.util.ArrayList;




public class ManageUsersServlet extends HttpServlet {
    String basePathServelt = "/Admin/Users";

    //Routeur METHODE GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        if (session.getAttribute("AUTH") == null) {
            response.sendRedirect("/Auth/login");

        } else {
            String action = request.getPathInfo();
            if (action == null) {
                action = "/";
            }
            System.out.println("Action : " + action);
            switch (action) {
                case "/show":
                    showUser(request, response);
                    break;
                case "/new":
                    showNewUser(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    break;
                case "/update":
                    break;
                case "/":
                    showIndex(request, response);
                    break;
                default:
                    showIndex(request, response);
                    break;
            }
        }


    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    //Methodes
    //[GET] List of Users
    private void showIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<User> users = null;


        if (session.getAttribute("usersDB") == null) {

            session.setAttribute("usersDB", users);
        } else {
            users = (ArrayList<User>) session.getAttribute("usersDB");


        }

        request.setAttribute("users", users);
        this.getServletContext().getRequestDispatcher("/WEB-INF/Users/Index.jsp").forward(request, response);

    }

    private void showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        HttpSession session = request.getSession();
        String userId = request.getParameter("id").toString();
        for (User u : (ArrayList<User>) session.getAttribute("usersDB")) {

            if (u.getId() == userId) {
                user = u;
            }
        }
        if (!(user == null)) {
            request.setAttribute("user", user);
            this.getServletContext().getRequestDispatcher("/WEB-INF/Users/Show.jsp").forward(request, response);
        } else {

            response.sendRedirect(getServletContext().getContextPath() + basePathServelt);

        }


    }


    //[GET] Create User
    private void showNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Users/newUserForm.jsp").forward(request, response);

    }

    //[POST] Insert User
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("usersDB");

        User user = new User();

        user.setId("2");
        user.setFirstName(request.getParameter("FirstName").toString());
        user.setLastName(request.getParameter("LastName").toString());
        users.add(user);

        session.setAttribute("usersDB", users);

        //Redirect to "/Admin/Users"
        response.sendRedirect(getServletContext().getContextPath() + basePathServelt);
    }

    //[GET] Delete User
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = request.getParameter("id").toString();
        ArrayList<User> users = new ArrayList<User>();
        //On remplissage de users sans l'user qui a comme id userID
        for (User u : (ArrayList<User>) session.getAttribute("usersDB")) {
            if (u.getId() != userId) {
                users.add(u);
            }
        }
        //on met à jour usersDB
        session.setAttribute("usersDB", users);


        //Redirect to "/Admin/Users"
        response.sendRedirect(getServletContext().getContextPath() + basePathServelt);
    }

}

package servlets;

import store.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tanya on 2/28/14.
 */


public class LoginServlet extends HttpServlet {
    private Store store;
    private XMLBinder xmlBinder;
    private User curUser;

    @Override
    public void init() throws ServletException {
        xmlBinder = new XMLBinder("~/store.xml");
        xmlBinder.getStoreDataFromXML();
    }

    public XMLBinder getXmlBinder() {
        return xmlBinder;
    }

    private int checkUser(String login, String pwd){
        Set<User> users = xmlBinder.getUsers();
        int k = 0;

        for(User u: users){
            if (login.equals(u.getLogin())){
                if (pwd.equals(u.getPassword())){
                    k = 1;
                    curUser = u;
                }
                else{
                    k = -1;
                }
            }
        }
        if (k == 0){
            curUser = new User(login, pwd, "");
        }
        return k;
    }
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int k = 0;
        HttpSession session = null;

        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");

        if (checkUser(login, password) >= 0){
            session = httpServletRequest.getSession();
            if (session == null){
                session = httpServletRequest.getSession(true);
            }
            k = 1;
        }

        PrintWriter out = httpServletResponse.getWriter();
        if (k == 1){
            session.setAttribute("XMLBinder", getXmlBinder());
            session.setAttribute("User", curUser);
            session.setAttribute("userOrders", xmlBinder.getUserOrders(curUser));
            httpServletResponse.sendRedirect("/order.jsp");
        }
        else{
            out.print("<html><body>");
            out.print("<h1>Please, try to log in once more</h1>");
            out.print("<h2><a href=\"/index.jsp\" \">Back</a></h2>");
            out.print("</body></html>");
        }

    }
}

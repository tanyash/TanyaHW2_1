package servlets;

import store.Car;
import store.Order;
import store.User;
import store.XMLBinder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspContext;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tanya on 2/28/14.
 */
public class OrderServlet extends HttpServlet {
    XMLBinder xmlBinder;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        HttpSession session = httpServletRequest.getSession(false);

        User user = (User) session.getAttribute("User");
        xmlBinder = (XMLBinder) session.getAttribute("XMLBinder");

        String brand = httpServletRequest.getParameter("brand");
        String model = httpServletRequest.getParameter("model");
        String year = httpServletRequest.getParameter("year");

        int yearP;
        try{
           yearP = Integer.valueOf(year);
        }
        catch(NumberFormatException nfe){
            yearP = 2014;
        }

        Car car = new Car(brand, model, yearP);

        Order newOrder = new Order();
        newOrder.setOrder(user, car);
        if (xmlBinder  != null){
            xmlBinder.getStore().getOrderList().add(newOrder);
        }

        session.setAttribute("XMLBinder", xmlBinder);
        session.setAttribute("User", user);
        session.setAttribute("userOrders", xmlBinder.getUserOrders(user));

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/order.jsp");
        rd.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
        xmlBinder.setStoreDataToXML();
    }
}


package ictgradschool.web.lab16.examples.exercise04;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HitCounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ("true".equals(req.getParameter("removeCookie"))) {

            //TODO - add code here to delete the 'hits' cookie

        } else {

            //TODO - add code here to get the value stored in the 'hits' cookie then increase it by 1 and update the cookie

        }

        //TODO - use the response object's send redirect method to refresh the page

    }
}

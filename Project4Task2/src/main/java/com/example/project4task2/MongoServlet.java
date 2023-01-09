package com.example.project4task2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Wei Huang
 *  * AndrewID: weih2
 * Servlet, connect the backend and front end of the system.
 *
 */
//reference: https://github.com/CMU-Heinz-95702/Lab2-InterestingPicture
@WebServlet(name = "MongoServlet", urlPatterns = {"/getMongoServlet"})
public class MongoServlet extends HttpServlet {
    private MongoModel model;
    /**
     * The method always run when the class been called.
     */
    @Override
    public void init() {
        model = new MongoModel();
    }

    /**
     * The webpage through the function call the method.
     * @param request the front end request
     * @param response the response from the system
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        model = new MongoModel();
        //get all request log
                request.setAttribute("reqLog", model.reqLog());
        //get all response log
                request.setAttribute("resLog", model.resLog());
        //get average useage time
                int AvgResTime = 0;
                try {
                    AvgResTime = model.avgResTime();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
        //get most popular method
                request.setAttribute("popularMethod", model.popularMethod());
        //get average response time
                request.setAttribute("AvgResTime", AvgResTime);
        //get total request number
                request.setAttribute("numReq", model.numReq());
                System.out.println("Servlet");

        RequestDispatcher view = request.getRequestDispatcher("/result.jsp");
        view.forward(request, response);

    }

}
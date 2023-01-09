package com.example.project4task2;
/*
 * @author Sharon Shih
 * AndrewID: chunchus
 *
 * This code is referenced to Distributed System lab 2
 * Reference: https://github.com/CMU-Heinz-95702/Lab2-InterestingPicture
 * This program is acting as the servlet for an android app. It fetches the third-party
 * API data and get back the information to the Android app.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PoetServlet",
        urlPatterns = {"/getPoetry"})
public class PoetServlet extends HttpServlet {

    PoetModel ipm = null;  // The "business model" for this app

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        ipm = new PoetModel();
    }

    Gson gson = new Gson();

    public String replyToRequest(String g){
        String res = gson.toJson(g);
        System.out.println(g);
        return res;
    }
    // This servlet will reply to HTTP GET requests via this doGet method
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // get the search parameter if it exists
        String userChoice = request.getParameter("choiceType");
        String userSearchName = request.getParameter("searchName");
        //record request time and data into mongodb
        String requestID = ipm.insertRequest(userChoice, userSearchName);


        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        System.out.println(userChoice);
        System.out.println(userSearchName);

        String poetryResult = "";
        if(userChoice == null){
       System.out.println("User not choose.");
        }else {
            if(userChoice.equals("author")){
                poetryResult = ipm.doAuthorSearch(userSearchName);


            }else if(userChoice.equals("title")){
                poetryResult = ipm.doTitleSearch(userSearchName);

            }else if(userChoice.equals("lines")){
                poetryResult = ipm.doLineSearch(userSearchName);

            }else if(userChoice.equals("random")){
                poetryResult = ipm.doRandom(userSearchName);

            }
        }

        request.setAttribute("poetryResult",poetryResult);
        //write to mongoDB
        ipm.insertResponse(requestID, poetryResult);

        //turn into json object
        JsonArray jobj = new Gson().fromJson(poetryResult, JsonArray.class);

        //The Android app will only return the first Poetry in the JSON data returned back from the third-party API,
        //so the information is filtered here before sending to the Android app.
        JsonObject j = jobj.get(0).getAsJsonObject();
        String author = j.get("author").getAsString();
        String title = j.get("title").getAsString();
        JsonArray lines = j.getAsJsonArray("lines");

        PoetryInfo poetryInfo = new PoetryInfo(author, title, lines);

        System.out.println("JSONObject's lines: "+lines.toString());

        Gson gson = new Gson();
        String poetryResults = gson.toJson(poetryInfo);
        System.out.println("poetryResult is this: "+ poetryResults);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(poetryResults);
        out.flush();

    }
}

package com.example.project4task2;

/*
 * @author Sharon Shih
 *  AndrewID: chunchus
 * This code is referenced to Distributed System lab 2
 * Reference: https://github.com/CMU-Heinz-95702/Lab2-InterestingPicture
 *
 * This file is the Model component of the MVC, and it models the business
 * logic for the web application.
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PoetModel {
    /**
     * do poetry default search
     */
    public String doDefaultPoetrySearch() {

        String response = "";

        // Create a URL for the page to be screen scraped
        String poetryURL =
                "https://poetrydb.org/author/Ernest";

        // Fetch the page
        response = fetch(poetryURL);
        return response;
    }

    /**
     * Search for user input's author name's poetry
     *
     * @param authorName
     * @return
     */
    public String doAuthorSearch(String authorName) {

        String response = "";

        // Create a URL for the page to be screen scraped
        authorName.replace(" ", "%20");
        String poetryURL =
                "https://poetrydb.org/author/" + authorName;

        // Fetch the page
        response = fetch(poetryURL);
        return response;
    }

    public String doTitleSearch(String title) {

        String response = "";
        title.replace(" ", "%20");
        // Create a URL for the page to be screen scraped
        String poetryURL =
                "https://poetrydb.org/title/" + title;

        // Fetch the page
        response = fetch(poetryURL);
        return response;
    }

    public String doLineSearch(String line) {

        String response = "";
        line.replace(" ", "%20");
        // Create a URL for the page to be screen scraped
        String poetryURL =
                "https://poetrydb.org/title/" + line;

        // Fetch the page
        response = fetch(poetryURL);
        return response;
    }

    public String doRandom(String random) {

        String response = "";

        // Create a URL for the page to be screen scraped
        String poetryURL =
                "https://poetrydb.org/title/" + random;

        // Fetch the page
        response = fetch(poetryURL);
        return response;
    }

    /*
     * Make an HTTP request to a given URL
     *
     * @param urlString The URL of the request
     * @return A string of the response from the HTTP GET.  This is identical
     * to what would be returned from using curl on the command line.
     */
    private String fetch(String urlString) {
        String response = "";
        try {
            URL url = new URL(urlString);
            /*
             * Create an HttpURLConnection.  This is useful for setting headers
             * and for getting the path of the resource that is returned (which
             * may be different than the URL above if redirected).
             * HttpsURLConnection (with an "s") can be used if required by the site.
             */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Eeek, an exception");
            // Do something reasonable.  This is left for students to do.
        }
        return response;
    }

    public String insertRequest(String method, String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = formatter.format(date);
        RequestDao a = new RequestDao();
        APIRequest test = new APIRequest(method, time, data);
        return a.create(test);
    }

    //Third part API -> servlet: insert information to MongoDB
    public void insertResponse(String requestID, String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = formatter.format(date);
        ResponseDao a = new ResponseDao();
        APIResponse test = new APIResponse(requestID, time, data);
        a.create(test);
    }
}
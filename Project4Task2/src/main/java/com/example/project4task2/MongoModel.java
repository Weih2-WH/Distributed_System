package com.example.project4task2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Wei Huang
 *  * AndrewID: weih2
 * logic for the webpage.
 */
//reference: https://github.com/CMU-Heinz-95702/Lab2-InterestingPicture
public class MongoModel {
    RequestDao request = new RequestDao();
    List requestData = request.getList();
    ResponseDao response = new ResponseDao();
    List responseData = response.getList();

    /**
     * Get the most popular method to search poetry.
     * @return String for the popular method
     */
    public String popularMethod () {
        int author = 0;
        int line = 0;
        int title = 0;
        int random = 0;
        for(int i = 0; i < requestData.size(); i++){
            APIRequest tmp = (APIRequest) requestData.get(i);
            if (tmp.getMethod().equals("Author")) {
                author++;
            }else if (tmp.getMethod().equals("Line")) {
                line++;
            }else if (tmp.getMethod().equals("Title")) {
                title++;
            }else if (tmp.getMethod().equals("Random")) {
                random++;
            }
        }
        int max = Math.max(random,Math.max(title,Math.max(author,line)));

        if (max == author){
            return "Author: " + Integer.toString(author);
        } else if ((max == line)) {
            return "Line: " + Integer.toString(line);
        }else if ((max == title)) {
            return "Title: " + Integer.toString(title);
        }else if ((max == random)) {
            return "Random: " + Integer.toString(random);
        }
        return "Na";
    }

    /**
     * The average response time from API.
     * @return int average sec.
     * @throws ParseException
     */

    public int avgResTime () throws ParseException {
        Date reqTime;
        Date resTime;
        int diff = 0;
        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        for(int i = 0; i < requestData.size();i++){
            APIRequest tmp = (APIRequest) requestData.get(i);
            reqTime = sdf.parse(tmp.getDate());
            for(int j = 0; j < responseData.size();j++){
                APIResponse tmpRes = (APIResponse) responseData.get(j);
                String reqID = tmp.getId();
                String checkID = tmpRes.getRequestID();
                    if (reqID.equals(checkID)) {
                    resTime = sdf.parse(tmpRes.getDate());
                    diff += (resTime.getTime() - reqTime.getTime())/1000;
                    count++;
                }
            }
        }
        return diff/count;
    }

    /**
     * Total request number.
     * @return int number of request.
     */
    public int numReq () {
        return requestData.size();
    }
    /**
     * Get all the request log.
     * @return int number of request.
     */
    public List reqLog () {
        return requestData;
    }
    /**
     * Get all the response log.
     * @return int number of request.
     */
    public List resLog () {
        return responseData;
    }

}

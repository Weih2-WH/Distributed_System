package com.example.project4task2;

/**
 * @author Wei Huang
 * AndrewID: weih2
 * Class for stored the request information from user.
 */

public class APIRequest {
    /**
     * Unique number to identity usage.
     */
    private String Id;
    /**
     * Searching method that user choose.
     */
    private String Method;
    /**
     * Searching time.
     */
    private String Date;
    /**
     * Request data.
     */
    private String Data;
    public APIRequest() {}

    /**
     * Request information.
     * @param Method the searching method the user choose.
     * @param Date the time that user request.
     * @param Data the value used to get information from API.
     */
    public APIRequest(String Method, String Date, String Data) {
        this.Id =Id;
        this.Method = Method;
        this.Date = Date;
        this.Data = Data;
    }
    /**
     * Get unique ID in MongoDB
     */
    public String getId() {
        return Id;
    }
    /**
     * Set unique ID in MongoDB
     */
    public void setId(String Id) {
        this.Id = Id;
    }
    /**
     * Get Method information in MongoDB
     */
    public String getMethod() {
        return Method;
    }
    /**
     * Set Method information in MongoDB
     */
    public void setMethod(String Method) {
        this.Method = Method;
    }
    /**
     * Get Date information in MongoDB
     */
    public String getDate() {
        return Date;
    }
    /**
     * Set Date information in MongoDB
     */
    public void setDate(String Date) {
        this.Date = Date;
    }
    /**
     * Get request data information in MongoDB
     */
    public String getData() {
        return Data;
    }
    /**
     * Set request data information in MongoDB
     */
    public void setData(String Data) {
        this.Data = Data;
    }

}


package com.example.project4task2;
/**
 * Class for stored the response information from user.
 */
public class APIResponse {
    /**
     * Unique number to identity usage.
     */
    private String Id;
    /**
     * Searching method that user choose.
     */
    private String requestID;
    /**
     * Searching time.
     */
    private String Date;
    /**
     * Request data.
     */
    private String Data;
    public APIResponse() {}

    /**
     * Response information
     * @param requestID the request of the response
     * @param Date the time that API request.
     * @param Data the value response from API.
     */

    public APIResponse(String requestID, String Date, String Data) {
        this.Id = Id;
        this.requestID = requestID;
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
     * Get request ID from MongoDB
     */
    public String getRequestID() {
        return requestID;
    }
    /**
     * Set request ID from MongoDB
     */
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
    /**
     * Get Date information in MongoDB
     */
    public String getDate() {
        return Date;
    }
    /**
     * @author Wei Huang
     * AndrewID: weih2
     * Set Date information in MongoDB
     */
    public void setDate(String Date) {
        this.Date = Date;
    }
    /**
     * Get response data information in MongoDB
     */
    public String getData() {
        return Data;
    }
    /**
     * Set response data information in MongoDB
     */
    public void setData(String Data) {
        this.Data = Data;
    }

}

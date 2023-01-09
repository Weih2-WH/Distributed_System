package com.example.project4task2;

import com.google.gson.JsonArray;

/**
 * This is the class for Poetry, which helps fetch information out of the JSON object.
 * AndrewID: chunchus
 *  @author Sharon Shih
 */

public class PoetryInfo {
    public String author;
    public String title;
    public JsonArray lines;
    public PoetryInfo(String author, String title, JsonArray lines){
        this.author = author;
        this.title = title;
        this.lines = lines;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public JsonArray getLines(){
        return lines;
    }
    public void setAuthor(String a){
        this.author = a;
    }

    public void setLines(JsonArray lines) {
        this.lines = lines;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

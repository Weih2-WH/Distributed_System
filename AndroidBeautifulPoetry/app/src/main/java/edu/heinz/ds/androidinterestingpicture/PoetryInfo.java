package edu.heinz.ds.androidinterestingpicture;

import java.util.List;

/**
 * @author Sharon Shih (chunchus)
 * This is the PoetryInfo class, which help to get the author name, title, and lines of the poetry
 * easily.
 */
public class PoetryInfo {
    public String author;
    public String title;
    public List lines;
    public PoetryInfo(String author, String title, List lines){
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
    public List getLines(){
        return lines;
    }
    public void setAuthor(String a){
        this.author = a;
    }

    public void setLines(List lines) {
        this.lines = lines;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

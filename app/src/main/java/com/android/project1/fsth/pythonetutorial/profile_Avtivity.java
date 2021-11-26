package com.android.project1.fsth.pythonetutorial;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class profile_Avtivity {


    private String imageurl,name,time,Comment,date;

    public profile_Avtivity() {

    }

    public profile_Avtivity(String name, String time, String imageurl, String Comment,String date) {
//    public profile_Avtivity(String id,String name, String time, String imageurl, String Comment) {
  //      this.id=id;
        this.imageurl = imageurl;
        this.name = name;
        this.time = time;
        this.Comment = Comment;
        this.date=date;
    }


   /* public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
*/

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {

        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;

    }


    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getDate()
    {   return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

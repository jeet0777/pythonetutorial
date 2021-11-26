package com.android.project1.fsth.pythonetutorial;

import android.content.Context;
import android.content.SharedPreferences;

public class font {


    SharedPreferences font_shared;
    public  font(Context context)
    {
        font_shared=context.getSharedPreferences("filenamw",Context.MODE_PRIVATE);
    }
    public  void setfont(Integer digit){
        SharedPreferences.Editor editor=font_shared.edit();
        editor.putInt("font__size",digit);
        editor.apply();
    }
    public Integer loadfont(){
        Integer digit=font_shared.getInt("font__size",16);
        return  digit;
    }
}

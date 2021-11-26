package com.android.project1.fsth.pythonetutorial;

import android.content.Context;
import android.content.SharedPreferences;

public class soundspecial {
    SharedPreferences snd;
    public soundspecial(Context context){

        snd=context.getSharedPreferences("fn",Context.MODE_PRIVATE);
    }
    public  void setsounddstate(Boolean state){
        SharedPreferences.Editor editor=snd.edit();
        editor.putBoolean("sound",state);
        editor.apply();
        // editor.commit();
    }
    //this will load state
    public Boolean loadsoundmode(){
        Boolean state=snd.getBoolean("sound",false);
        return state;
    }
}

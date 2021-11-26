package com.android.project1.fsth.pythonetutorial;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.widget.Toast;

public class shared {
    SharedPreferences shr;
    Context context;
    public shared(Context context){
        this.context=context;
        shr=context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }//constructor


    //this will save state of mode
    public  void setnightstate(Boolean state){
        SharedPreferences.Editor editor=shr.edit();
        editor.putBoolean("NightMode",state);
        editor.apply();
       // editor.commit();

    }
    //this will load state
    public Boolean loadnightmode(){
      Boolean state=shr.getBoolean("NightMode",false);
      return state;
    }
    public void DisplayMsg(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }

        public void LoadMode(){
        if(loadnightmode())
            context.setTheme(R.style.darkTheme);
        else
            context.setTheme(R.style.AppTheme);

            }
}

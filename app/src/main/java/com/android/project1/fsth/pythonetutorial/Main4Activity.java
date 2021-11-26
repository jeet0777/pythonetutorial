package com.android.project1.fsth.pythonetutorial;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Main4Activity extends AppCompatActivity implements View.OnClickListener{
    AlertDialog alertDialog1;
    CharSequence[] values={"10 px","16px","18px","24px"};
    shared shared;
    font font_pref;
    public   int a=1;
       int q_no;
    FirebaseAuth auth;

    private CardView tutor,prg,quz,shre,faq,cmnts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared = new shared(this);
        font_pref=new font(this);
        //if(AppCompatDelegate.getDefaultNightMode()== MODE_NIGHT_YES)
        /*if(shared.loadnightmode()==true) {
            setTheme(R.style.darkTheme);//

        }else {
            setTheme(R.style.AppTheme);
        }
        */
        shared.LoadMode();//for Darfk MOde
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //partial
        //Animation zoomout= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out_click);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tutor=(CardView)findViewById(R.id.cardtut);
        prg=(CardView)findViewById(R.id.cardprg);
        faq=(CardView)findViewById(R.id.cardfaq);
        cmnts=(CardView)findViewById(R.id.cardcmnts);
        quz=(CardView)findViewById(R.id.cardquz);
        shre=(CardView)findViewById(R.id.cardshre);

        //onclick listener on each card
        tutor.setOnClickListener(this);
        prg.setOnClickListener(this);
        faq.setOnClickListener(this);
        cmnts.setOnClickListener(this);
        quz.setOnClickListener(this);
        shre.setOnClickListener(this);
        auth= FirebaseAuth.getInstance();



    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){

            case R.id.cardtut: shared.DisplayMsg("tutorial");
//                Intent i2 =new Intent(Main4Activity.this,tutorial.class);
//                startActivity(i2);
//tutorial activity have been UNlisted from Manifist.xml
                startActivity(new Intent(getApplicationContext(),ListMainActivity.class));
              //  overridePendingTransition(R.anim.zoom_in_click,R.anim.zoom_in_click);
                break;
            case R.id.cardprg: shared.DisplayMsg("Program");
//                Intent prg=new Intent(Main4Activity.this,program.class);
//                startActivity(prg);
//                startActivity(new Intent(this,Program_and_FAQ_view.class));

        Intent prgintent=new Intent(this,Program_and_FAQ_view.class);
        prgintent.putExtra("PRG","NOTNULL");
        startActivity(prgintent);
                break;
            case R.id.cardfaq:
                shared.DisplayMsg("FAQ");
//                Intent fq=new Intent(Main4Activity.this,FAQ.class);
//                startActivity(fq);
                Intent FAQintent=new Intent(this,Program_and_FAQ_view.class);
                FAQintent.putExtra("FAQ","NOTNULLASFAQ");
                startActivity(FAQintent);

                break;
            case R.id.cardcmnts: shared.DisplayMsg("Comments");
                if(auth.getCurrentUser()!=null) {
                    startActivity(new Intent(this,CommentsIdea.class));
                }else{
                    Intent cmnt=new Intent(Main4Activity.this,SigninActivity.class);
                    startActivity(cmnt);
//                finish();
                 }
                break;
            case R.id.cardquz: shared.DisplayMsg("Quiz");
                Intent qz=new Intent(Main4Activity.this,Quiz.class);
                //startActivity(qz);
                //  Configure Google Sign In
                if(auth.getCurrentUser()==null) {
                Intent im=new Intent(this,SigninActivity.class);
                im.putExtra("yes","yes");
   startActivity(im);
  //              startActivityForResult(im,777);
                finish();
                return;
                }
                else{
                    startActivity(qz);
                }
                break;
            case R.id.cardshre:shared.DisplayMsg("Share");
                Intent sharebtn=new Intent(Intent.ACTION_SEND);
                sharebtn.setType("text/plain");
                String url="App is in devlopment";
                sharebtn.putExtra(Intent.EXTRA_SUBJECT,"Subject/Title");
                sharebtn.putExtra(Intent.EXTRA_TEXT,"hellow");
                startActivity(Intent.createChooser(sharebtn,"Choose sharing method"));

                break;
            default: shared.DisplayMsg("Sommething Went Wrong");

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        MenuItem itm = menu.findItem(R.id.it1);
        if (shared.loadnightmode() == true) {
            itm.setChecked(true);
        }else { itm.setChecked(false);}
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        CheckBox ch1=(CheckBox)findViewById(R.id.it1);
        /*if(shared.loadnightmode()==true) {
            setTheme(R.style.darkTheme);//}
            //Toast.makeText(this, "Hello Good", Toast.LENGTH_SHORT).show();
            //ch1.setChecked(true);
        }else {
            setTheme(R.style.AppTheme);
           // ch1.setChecked(false);
        }*/
        switch(item.getItemId()){
            case R.id.it1:
                if(!item.isChecked()){
                    shared.setnightstate(true);
                    // SharedPreferences pref=getApplicationContext().getSharedPreferences("key",0);
                    //SharedPreferences.Editor editor=pref.edit();
                    //editor.putInt("key1",5);
                    //editor.apply();
                    //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restart(); // Refresh Activity
                    // item.setChecked(false);
                    shared.DisplayMsg("dark mode enabled");
                    //          item.setChecked(true);
                }
                else {
                    shared.setnightstate(false);  //item.setChecked(false);
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    rrestart();
                   shared.DisplayMsg("dark mode disabled");
                }
                return true;


            //making font function
            case R.id.fnt: dilg();
                return true;
            case R.id.refo:
                startActivity(new Intent(this,Refernce.class)); finish();return  true;

            case R.id.it3:
                startActivity(new Intent(this,About.class)); finish();return  true;
                //case R.id.newact:
              //  startActivity(new Intent(this,Main4Activity.class));finish();return true;

            default : shared.DisplayMsg("select something perfact");
        }
        return super.onOptionsItemSelected(item);
    }
    //Alert dialogue for font size
    public  void dilg(){
        AlertDialog.Builder builder;
        if(shared.loadnightmode()) {
             builder = new AlertDialog.Builder(Main4Activity.this, R.style.dark);
        }else{
            builder=new AlertDialog.Builder(Main4Activity.this,R.style.white);
        }

        builder.setTitle("select your choice");
        if(font_pref.loadfont()==10){
            a=0;
        }if(font_pref.loadfont()==16) {a=1;}
        if(font_pref.loadfont()==18){a=2;}
        if(font_pref.loadfont()==24){a=3;}
        builder.setSingleChoiceItems(values, a, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                switch (item){

                    case 0: shared.DisplayMsg("10px");

                        font_pref.setfont(10);
                        break;
                    case 1: shared.DisplayMsg("16px");
                        font_pref.setfont(16);

                        break;
                    case 2: shared.DisplayMsg("18px");
                        font_pref.setfont(18);

                        break;
                    case 3: shared.DisplayMsg("24px");
                        font_pref.setfont(24);

                        break;
                }
                alertDialog1.dismiss();
            }
        });
        alertDialog1=builder.create();
        alertDialog1.show();
    }

    //Refrashing the Activity
    public void restart(){
        Intent i=new Intent(getApplicationContext(),Main4Activity.class);
        startActivity(i);
        finish();

    }
    public void rrestart(){
        Intent i=new Intent(getApplicationContext(),Main4Activity.class);
        startActivity(i);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==777){
        //Do nothing Just TO handle A cituation
//boolean checker=true;
    }
    }

    @Override
    public void onBackPressed() {
        finish();

    }
}

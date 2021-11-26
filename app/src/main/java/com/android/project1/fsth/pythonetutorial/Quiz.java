package com.android.project1.fsth.pythonetutorial;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Quiz extends AppCompatActivity {
 shared shared;
 //Testing Purpose
Button next;
    TextView textView4,current_question,current_score;
    public static TextView questionTxt;
    RadioGroup optionGroup;
    RadioButton b1,b2,b3,b4,acess;
    int correct=0,wrong=0,total=0;
  //  ProgressBar showprogress;
    public long q_no;
    int computerCount=0;
public static boolean FETCH_TIMER_DATA=false;
    DatabaseReference databaseReference;
    //End Test
    boolean flag=false; //To maintain 2 StartActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        shared =new shared(this);
        shared.LoadMode();//Dark Mode
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Quiz");

        setContentView(R.layout.activity_quiz);
        next=findViewById(R.id.next);
        optionGroup=findViewById(R.id.optiongroup);
        b1 = (RadioButton) findViewById(R.id.OptionA);
        b2 = (RadioButton) findViewById(R.id.OptionB);
        b3 = (RadioButton) findViewById(R.id.OptionC);
        b4 = (RadioButton) findViewById(R.id.OptionD);
        questionTxt = (TextView) findViewById(R.id.Questions);

        current_question=findViewById(R.id.noofquestion);
        current_score=findViewById(R.id.currentScore);

        //delaying the method
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                updateQuestion();

            }
        }, 500);

  //      reverseTimer(30,textView4);   Temporary Suspended

        current_question.setText("Question: "+1);
    }


    protected void QuickRead() {
        FirebaseDatabase database2=FirebaseDatabase.getInstance();
        DatabaseReference myref3=database2.getReference("Comments");
        myref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                q_no=dataSnapshot.child("-M9XPA7U3kuGLt8ovVlvDevloped_by_JP").child("Questions").getChildrenCount();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updateQuestion()
    {
        computerCount++;
        current_score.setText("Score: "+correct);
        if(computerCount>q_no)
        {
            if(q_no==0){ QuickRead(); readquiz();   return;     }
            else{
            flag=true;
            Intent myIntent = new Intent(Quiz.this,ResultActivity.class);
            myIntent.putExtra("total",String.valueOf(total));
            myIntent.putExtra("correct",String.valueOf(correct));
            myIntent.putExtra("incorrect",String.valueOf(wrong));
            startActivity(myIntent);
            finish();
            }

        }
        else
        {
            current_question.setText("Question: "+computerCount);
            readquiz();

        }



    }

public void readquiz() {


    databaseReference = FirebaseDatabase.getInstance().getReference("Comments").child("-M9XPA7U3kuGLt8ovVlvDevloped_by_JP").child("Questions").child(String.valueOf(computerCount));
    total++;
   /* if(total==1) {
        FETCH_TIMER_DATA = true;
        invalidateOptionsMenu();
    }//for backup
   */ databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            final Question question = dataSnapshot.getValue(Question.class);
            questionTxt.setText(question.getQuestion());
        if(total==1) {
        FETCH_TIMER_DATA = true;
        invalidateOptionsMenu();
        }       //for backup

            b1.setText(question.getOption1());
            b2.setText(question.getOption2());
            b3.setText(question.getOption3());
            b4.setText(question.getOption4());

            next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        acess=findViewById(optionGroup.getCheckedRadioButtonId());
        int id=optionGroup.getCheckedRadioButtonId();
        if(id<=0){
            //Do Nothing
            shared.DisplayMsg("Please Select Something");
            return;
        }else{
        if(acess.getText().toString().equals(question.getAnswer())){

            checktrue(question); //Pasing Object of Question Class

            correct++;
            optionGroup.clearCheck();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ColorChanger();//changing Color
                    updateQuestion();

                }
            }, 1000);

        }else{              //Wrong Answer Goes Here

         checktrue(question);
         wrong++;
            optionGroup.clearCheck();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ColorChanger();
                    updateQuestion();
                }
            }, 1000);

        }
    }}
});


        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    });


}
    public void checktrue(Question question){
        Drawable success=getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme());
        Drawable red=getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme());//here value was null
        success.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        red.setColorFilter(Color.RED,PorterDuff.Mode.SRC_ATOP);
        if(b1.getText().toString().equals(question.getAnswer())){

            b1.setBackground(success);
            b2.setBackground(red);
            b3.setBackground(red);
            b4.setBackground(red);
            getWindow().getDecorView().findViewById(R.id.Quizview).invalidate();
        }
        else if(b2.getText().toString().equals(question.getAnswer())){
            b1.setBackground(red);
            b2.setBackground(success);
            b3.setBackground(red);
            b4.setBackground(red);
            getWindow().getDecorView().findViewById(R.id.Quizview).invalidate();
        }
        else if(b3.getText().toString().equals(question.getAnswer())){
            b1.setBackground(red);
            b2.setBackground(red);
            b3.setBackground(success);
            b4.setBackground(red);
            getWindow().getDecorView().findViewById(R.id.Quizview).invalidate();
        }
        else if(b4.getText().toString().equals(question.getAnswer())){
            b1.setBackground(red);
            b2.setBackground(red);
            b3.setBackground(red);
            b4.setBackground(success);
        }

    }
//long time=3000;

    public void ColorChanger(){
        if(shared.loadnightmode())
        {   b1.setTextColor(Color.WHITE);
            b2.setTextColor(Color.WHITE);
            b3.setTextColor(Color.WHITE);
            b4.setTextColor(Color.WHITE);
//            b1.setBackgroundColor(Color.parseColor("#303030"));
  //          b2.setBackgroundColor(Color.parseColor("#303030"));
    //        b3.setBackgroundColor(Color.parseColor("#303030"));
      //      b4.setBackgroundColor(Color.parseColor("#303030"));
            b1.setBackground(getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme()));
            b2.setBackground(getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme()));
            b3.setBackground(getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme()));
            b4.setBackground(getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme()));

        }else{  //White Mode
            //Text Color
            b1.setTextColor(Color.BLACK);
            b2.setTextColor(Color.BLACK);
            b3.setTextColor(Color.BLACK);
            b4.setTextColor(Color.BLACK);

            //for Some versions
        //    b1.setBackgroundColor(Color.parseColor("#fcfcfc"));
          //  b2.setBackgroundColor(Color.parseColor("#fcfcfc"));
            //b3.setBackgroundColor(Color.parseColor("#fcfcfc"));
            //b4.setBackgroundColor(Color.parseColor("#fcfcfc"));

            //predefined Drawable

            b1.setBackground(getResources().getDrawable(R.drawable.mycustomstyle, this.getTheme()));
            b2.setBackground(getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme()));
            b3.setBackground(getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme()));
            b4.setBackground(getResources().getDrawable(R.drawable.mycustomstyle,this.getTheme()));

        }


    }
    public void reverseTimer(int Seconds,final TextView tv){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));



            }

            public void onFinish() {
                tv.setText("Completed");if(flag==false){
                Intent myIntent = new Intent(Quiz.this,ResultActivity.class);
                myIntent.putExtra("total",String.valueOf(total));
                myIntent.putExtra("correct",String.valueOf(correct));
                myIntent.putExtra("incorrect",String.valueOf(wrong));
                startActivity(myIntent);}else{ //Do NOthing hahahaha
          }
            }
        }.start();

    }


    //end thisTRY
int timer=1000;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.timer,menu);
    final MenuItem counter=menu.findItem(R.id.settimer);
        final TextView textView=new TextView(getApplicationContext());
            textView.setTextSize(35);

     //   ObjectAnimator smoothprogress=ObjectAnimator.ofInt(showprogress,"progress",showprogress.getProgress(),100*100);
            String str=questionTxt.getText().toString();

        /*    Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
        */

        /*        @Override
                public void run() {
        */
        if(FETCH_TIMER_DATA) {
            new CountDownTimer(300 * timer + timer, 1000) {
                int count = 0;

                public void onTick(long millisUntilFinished) {
                    int seconds = (int) (millisUntilFinished / 1000);
                    int minutes = seconds / 60;
                    seconds = seconds % 60;

                    long ms = millisUntilFinished;
                    textView.setPadding(0, 0, 30, 2);

                    if (shared.loadnightmode())
                        textView.setTextColor(Color.WHITE);

                    textView.setText(String.format("%02d", minutes)
                            + ":" + String.format("%02d", seconds));
                    counter.setActionView(textView);
                    count++;
                    int sum = 30 * timer + timer;
                    //showprogress.setProgress((int)count*100/(2000/100));

                }

                public void onFinish() {
                    count++;
                    // showprogress.setProgress(100);
                    textView.setText("Submit");
                    if (flag == false) {
                        Intent myIntent = new Intent(Quiz.this, ResultActivity.class);
                        myIntent.putExtra("total", String.valueOf(q_no));
                        myIntent.putExtra("correct", String.valueOf(correct));
                        myIntent.putExtra("incorrect", String.valueOf(wrong));
                        startActivity(myIntent);
                    } else { //Do NOthing hahahaha
                    }
                }
            }.start();

          /*      }
            }, 2700);
*/
        }
        return  true;
    }

    @Override
    public void onBackPressed() {
        if(flag==false){
            flag=true;
        }
        super.onBackPressed();
    }
}

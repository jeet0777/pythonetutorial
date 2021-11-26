package com.android.project1.fsth.pythonetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
//trial
    TextView t1_total,t2_correct,t3_wrong,Grade;
    String total,correct,incorrect;
    ImageView congrats;
    Button okay;
   //end TRIAL
    shared shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared =new shared(this);

        shared.LoadMode();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//start MODULE
        congrats=(ImageView)findViewById(R.id.congrats);
        t2_correct = (TextView)findViewById(R.id.correct);
        t3_wrong = (TextView)findViewById(R.id.incorrect);
        t1_total = (TextView)findViewById(R.id.attempted);
        Grade=findViewById(R.id.Grade);
      okay=findViewById(R.id.ok);
       if(getIntent()!=null){
        Intent i = getIntent();
        total = i.getStringExtra("total");
        correct = i.getStringExtra("correct");
        incorrect = i.getStringExtra("incorrect");
        setValues();}
        else{ Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
            return;}

            okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

//END MODULE
    }

    private void setValues()
    {
        float percentage_correct=Integer.parseInt(correct);
        float percentage_total=Integer.parseInt(total);
        float getper=percentage_correct/percentage_total*100;
        getper=Math.round(getper);

        t1_total.setText(total);
        t2_correct.setText(correct);
        t3_wrong.setText(incorrect);
        Grade.setText(String.valueOf(getper)+"%");
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultActivity.this,Main4Activity.class));
        finish();
    }
}


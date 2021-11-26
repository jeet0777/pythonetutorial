package com.android.project1.fsth.pythonetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommentsIdea extends AppCompatActivity {
shared shared;
    FirebaseAuth auth;
FirebaseUser user;
    EditText editText;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList <profile_Avtivity> list;
    fetchAdapter adapter;
    ImageView myprofilepic;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared =new shared(this);
     /*   if(shared.loadnightmode()==true)
            setTheme(R.style.darkTheme);
        else
            setTheme(R.style.AppTheme);
     */
        shared.LoadMode();//Dark Mode
        super.onCreate(savedInstanceState);
        setTitle("Comments");
        setContentView(R.layout.activity_comments_idea);
    auth=FirebaseAuth.getInstance();
    user=auth.getCurrentUser();
        recyclerView=findViewById(R.id.FetchData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        editText=findViewById(R.id.submit_comment);
        submit=findViewById(R.id.submit_to_fb);
        myprofilepic=findViewById(R.id.myprofile);
        if(auth.getCurrentUser()!=null){

            Picasso.get().load(auth.getCurrentUser().getPhotoUrl()).into(myprofilepic, new Callback() {
                @Override
                public void onSuccess() {
                    Bitmap bitmap=((BitmapDrawable)myprofilepic.getDrawable()).getBitmap();
                    RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(Resources.getSystem(),bitmap);
                    roundedBitmapDrawable.setCircular(true);
                    roundedBitmapDrawable.setCornerRadius(Math.max(bitmap.getWidth(),bitmap.getHeight())/2.0f);
                    myprofilepic.setImageDrawable(roundedBitmapDrawable);
                }

                @Override
                public void onError(Exception e) {
                    myprofilepic.setImageResource(R.drawable.ic_program);
                }
            });
        }else{ myprofilepic.setImageResource(R.drawable.ic_code);}
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myref=database.getReference("Comments");
if(TextUtils.isEmpty(editText.getText())) {
    shared.DisplayMsg("please enter your Comment");
    return;}
else{
    String id = myref.push().getKey();
   // myref.child(id).child("imageurl").setValue(user.getPhotoUrl().toString());

  //  myref.child(id).child("name").setValue(user.getEmail().replace("@gmail.com","").toString());
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
    String current = sdf.format(d);

    //   myref.child(id).child("time").setValue(Calendar.getInstance().getTime().toString());
   // myref.child(id).child("time").setValue(current);
    //myref.child(id).child("Comment").setValue(editText.getText().toString().trim());
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date curdate = new Date();
    //myref.child(id).child("date").setValue(dateFormat.format(curdate));

   //imageurl   name   comment timem   date

    Map<String, Object> value=new HashMap<>();
    value.put("imageurl",user.getPhotoUrl().toString());
    value.put("name",user.getEmail().replace("@gmail.com","").toString());
    value.put("time",current);
    value.put("comment",editText.getText().toString().trim());
 //   value.put("comment",ServerValue.TIMESTAMP.toString());
    value.put("date",dateFormat.format(curdate));

   myref.child(id).setValue(value).addOnSuccessListener(new OnSuccessListener<Void>() {
       @Override
       public void onSuccess(Void aVoid) {
   shared.DisplayMsg("Comment Submited");

       }

   }).addOnFailureListener(new OnFailureListener() {
       @Override
       public void onFailure(@NonNull Exception e) {
           shared.DisplayMsg("Sorry Something Went Wrong");
       }
   });
    editText.setText("");
}

                adapter=new fetchAdapter(CommentsIdea.this,list);
                recyclerView.setAdapter(adapter);


            }
        });
        reference=FirebaseDatabase.getInstance().getReference("Comments");

//        reference.addListenerForSingleValueEvent(valueEventListener);
reference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        list=new ArrayList<profile_Avtivity>();
        list.clear();
        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
            String childid=reference.getKey();
            if(dataSnapshot1.getKey().contains("Devloped_by_JP")){
                //Toast.makeText(getApplicationContext(),"returning",Toast.LENGTH_SHORT).show();
            continue;
            }
            else {
//long i=dataSnapshot.getChildrenCount();
                profile_Avtivity p = new profile_Avtivity();
                p = dataSnapshot.child(dataSnapshot1.getKey()).getValue(profile_Avtivity.class);
                //             Toast.makeText(getApplicationContext(),dataSnapshot.child(dataSnapshot1.getKey()).getValue(profile_Avtivity.class).toString(),Toast.LENGTH_SHORT).show();

                list.add(p);

            } //to maintain seprate database in 1
        }
        adapter=new fetchAdapter(CommentsIdea.this,list);

        recyclerView.setAdapter(adapter);

    }



    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    //startActivity(new Intent(CommentsIdea.this,Main4Activity.class)); finish();

    }
}

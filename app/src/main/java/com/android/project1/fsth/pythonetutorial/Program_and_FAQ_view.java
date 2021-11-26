package com.android.project1.fsth.pythonetutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Program_and_FAQ_view extends AppCompatActivity {
    private RecyclerView prgRecyclerview;
    public static boolean ISFAQ=false;
    FloatingActionButton ReaderMode,fab2;
    private prg_FAQ_Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Prg_and_FAQ_items> prgAndFAQ;
    shared shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared = new shared(this);

        shared.LoadMode();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_and__faq_view);
         prgAndFAQ=new ArrayList<>();
        if(getIntent().getStringExtra("PRG")!=null){
            ISFAQ=false;
        prgAndFAQ.add(new Prg_and_FAQ_items("odd or even numbers"));
        prgAndFAQ.add(new Prg_and_FAQ_items("prime or non prime number"));
        prgAndFAQ.add(new Prg_and_FAQ_items("paython program to check leap year"));
        prgAndFAQ.add(new Prg_and_FAQ_items("fibonacci series using python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("factorial in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("palindrome using python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("simple intrest in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("largest element in an array using  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("sum of array numbers using python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("shortest string of string list in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("find string length in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("find date to time in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("display calender using python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("exception in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("armstrong number in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("Decimal to Binary Octal and Hexadecimal in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("find length of list elements in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("Cloning List in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("interchange first and last elements in a list using  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("Python program to find sum of elements in list"));
        prgAndFAQ.add(new Prg_and_FAQ_items("generate random number  in  python"));
        prgAndFAQ.add(new Prg_and_FAQ_items("Ascii value in  python"));

        }
        else if(getIntent().getStringExtra("FAQ")!=null) {

            ISFAQ=true; //now its faq activity so jump to FAQ folder when clicked
            prgAndFAQ.add(new Prg_and_FAQ_items("What is pythone"));
            prgAndFAQ.add(new Prg_and_FAQ_items("What are the rules for local and global variables in Python"));
            prgAndFAQ.add(new Prg_and_FAQ_items("How do I convert a string to a number"));
            prgAndFAQ.add(new Prg_and_FAQ_items("how python is interpreted"));
            prgAndFAQ.add(new Prg_and_FAQ_items("what is lambda in python"));
            prgAndFAQ.add(new Prg_and_FAQ_items("what is module and package in python"));
            prgAndFAQ.add(new Prg_and_FAQ_items("Explain how can you generate random number in python"));
            prgAndFAQ.add(new Prg_and_FAQ_items("history of python"));
            prgAndFAQ.add(new Prg_and_FAQ_items("Feature of python"));
            prgAndFAQ.add(new Prg_and_FAQ_items("what are the python decoraters"));

        }
        else{
           shared.DisplayMsg("Something Went Wrong");
        }
        prgRecyclerview=findViewById(R.id.prg_and_faq);
        prgRecyclerview.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        adapter=new prg_FAQ_Adapter(prgAndFAQ,this);

        prgRecyclerview.setLayoutManager(layoutManager);
        prgRecyclerview.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_search,menu);

        MenuItem SEarch_item=menu.findItem(R.id.list_search_topics);
        SearchView searchView=(SearchView) SEarch_item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;

            }

            @Override
            public boolean onQueryTextChange(String s)
            {


                adapter.getFilter().filter(s);
                return false;
            }


        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       if(id==R.id.list_search_topics){
            return true;
       }
       else if(id==R.id.ReaderMode) {
           ReaderMode=findViewById(R.id.hiddenmode);
           fab2 = findViewById(R.id.fab2);
           ReaderMode.show();
           ReaderMode.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   fab2.show();//show fab2
                   ReaderMode.hide();//hide this to show new one

               }
           });

           fab2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   ReaderMode.hide();//hide fab1 to display fab2

                   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                   getSupportActionBar().show();

                   fab2.hide();//here clicked so the readermode gets off
                   // and this button hides

               }
           });
           getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
           getSupportActionBar().hide();

       }

       else if(id==R.id.bkmrk)
       {
           Intent intent=new Intent(this,BookmarkActivity.class);
           intent.putExtra("gethome","home");
           startActivityForResult(intent,101);

       }


        return super.onOptionsItemSelected(item);

    }
}

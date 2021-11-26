package com.android.project1.fsth.pythonetutorial;


import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
//import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//import android.content.Intent;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

//import java.util.ArrayList;

public class ListMainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    FloatingActionButton ReaderMode,fab2;

    private SearchManager searchManager;
    private SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
    private MenuItem searchItem;

    shared shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared=new shared(this);
        shared.LoadMode();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main_view);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        parentList = new ArrayList<ParentRow>();

        showTheseParentList = new ArrayList<ParentRow>();

        // The app will crash if display list is not called here.
        displayList();

        // This expands the list.
//try later
//        expandAll();


    }

    private void loadData() {
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;

        childRows.add(new ChildRow("Introduction"));
        childRows.add(new ChildRow(
                "Features"));
        childRows.add(new ChildRow(
                "Pros and Cons"));
        parentRow = new ParentRow("introduction", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow( "installation"));
        childRows.add(new ChildRow("path setup"));
        parentRow = new ParentRow("Installation and setup", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("Running Program"));
        childRows.add(new ChildRow( "Variables"));
        childRows.add(new ChildRow( "identifiers & keywords"));
        childRows.add(new ChildRow( "Statements & Comments"));
        childRows.add(new ChildRow( "Numbers"));
        childRows.add(new ChildRow( "Strings"));
        childRows.add(new ChildRow( "Format Method"));
        childRows.add(new ChildRow( "Escape sequence"));
        parentRow = new ParentRow("Basics of python", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("Operators"));
        childRows.add(new ChildRow( "Expressions"));
        childRows.add(new ChildRow( "Evaluation orders"));
        parentRow = new ParentRow("Operators & Expressions", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("if statement"));
        childRows.add(new ChildRow( "Elif statement"));
        childRows.add(new ChildRow( "While loop"));
        childRows.add(new ChildRow( "for loop"));
        childRows.add(new ChildRow( "Break statement"));
        childRows.add(new ChildRow( "Continue statement"));
        childRows.add(new ChildRow( "Pass statement"));
        parentRow = new ParentRow("Flow control", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("function Basics"));
        childRows.add(new ChildRow( "Function parameters"));
        childRows.add(new ChildRow( "Local variables"));
        childRows.add(new ChildRow( "The global statement"));
        childRows.add(new ChildRow( "Default argument values"));
        childRows.add(new ChildRow( "Keyword Arguments"));
        childRows.add(new ChildRow( "VarArgs parameter"));
        childRows.add(new ChildRow( "The Return statement"));
        childRows.add(new ChildRow( "DocString"));
        parentRow = new ParentRow("Functions", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("Module"));
        childRows.add(new ChildRow( "Importing Module"));
        childRows.add(new ChildRow( "The dir function"));
        childRows.add(new ChildRow( "Packages"));
        parentRow = new ParentRow("Modules", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("Lists"));
        childRows.add(new ChildRow( "Tuples"));
        childRows.add(new ChildRow( "Dictionary"));
        childRows.add(new ChildRow( "Sequences"));
        childRows.add(new ChildRow( "Sets"));
        parentRow = new ParentRow("DataStructure", childRows);
        parentList.add(parentRow);


        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("Python oop"));
        childRows.add(new ChildRow( "class"));
        childRows.add(new ChildRow( "Inheritance"));
        childRows.add(new ChildRow( "Operator Overloading"));
        childRows.add(new ChildRow( "Encapsulation"));
        parentRow = new ParentRow("Object Oriented Programming", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("Input from user"));
        childRows.add(new ChildRow( "Files"));
        childRows.add(new ChildRow( "Pickle"));
        childRows.add(new ChildRow( "Unicode"));
        parentRow = new ParentRow("Input & Output", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("Errors & Exceptions"));
        childRows.add(new ChildRow( "Exception Handling"));
        childRows.add(new ChildRow( "Raise Exception"));
        childRows.add(new ChildRow( "try finally"));
        childRows.add(new ChildRow( "The With Statement"));
        childRows.add(new ChildRow( "User Defined Errors"));
        parentRow = new ParentRow("Exception Handling", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow("passing Tuples around"));
        childRows.add(new ChildRow( "Special Methods"));
        childRows.add(new ChildRow( "Single Statement Blocks"));
        childRows.add(new ChildRow( "Lambda"));
        childRows.add(new ChildRow( "List Comprehension"));
        childRows.add(new ChildRow( "Assert Statement"));
        childRows.add(new ChildRow( "Decorators"));
        parentRow = new ParentRow("Some Advanced Concepts", childRows);
        parentList.add(parentRow);

    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        } //end for (int i = 0; i < count; i++)
    }

    private void displayList() {
        loadData();

        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new MyExpandableListAdapter(ListMainActivity.this, parentList);


        myList.setAdapter(listAdapter);

        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent,
                                    View v, int groupPosition,
                                    int childPosition, long id) {
            return false;
        }
    });

        Log.d("Verify","NULL");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_search, menu);
        searchItem = menu.findItem(R.id.list_search_topics);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo
                (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.list_search_topics) {
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

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode==101){

//            donothing


        }

        super.onActivityResult(requestCode, resultCode, data);
    }

//public void togglemode(){
//
//
//    ReaderMode=findViewById(R.id.hiddenmode);
//    fab2 = findViewById(R.id.fab2);
//    ReaderMode.show();
//    ReaderMode.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            fab2.show();//show fab2
//            ReaderMode.hide();//hide this to show new one
//
//        }
//    });
//
//    fab2.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            ReaderMode.hide();//hide fab1 to display fab2
//
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getSupportActionBar().show();
//
//            fab2.hide();//here clicked so the readermode gets off
//            // and this button hides
//
//        }
//    });
//    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    getSupportActionBar().hide();
//
//
//}

}

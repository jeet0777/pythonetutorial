package com.android.project1.fsth.pythonetutorial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class tutorial extends AppCompatActivity {
    WebView tut;
    shared shared;
    font font_prefer;
    TextView getTitle;
    public static boolean clear_Title=false;
    ViewGroup first;
    //new data
    String current_page_url = "http://paythone.000webhostapp.com/Tutorial/Home.html";
    public static final String PREFERENCES = "PREFERENCES_NAME";
    public static final String WEB_LINKS = "links";
    public static final String WEB_TITLE = "title";
//closed
    public LinearLayout container;
    Button nextbutton,closebutton;
    private EditText findbox;
    private  static  final int SEARCH_ID=Menu.FIRST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared = new shared(this);
       /* if (shared.loadnightmode()) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
*/       shared.LoadMode();//Dark Mode
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Tutorial");
        font_prefer = new font(this);
        setContentView(R.layout.activity_tutorial);
//        getTitle=findViewById(R.id.gettitle);
      //  first=(RelativeLayout)findViewById(R.id.first);
        int a = font_prefer.loadfont();
        tut = (WebView) findViewById(R.id.tutorial);
        tut.getSettings().setJavaScriptEnabled(true);
        if (shared.loadnightmode() == true) { //String str="<html><head><style>p{background-color:#ffff; }</style></head></html>";
            tut.setBackgroundColor(Color.BLACK);
            tut.evaluateJavascript("javascript:document.body.style.color=\"red\"",null);

        } else {
              tut.setBackgroundColor(Color.WHITE);
        }
        if(getIntent().getExtras()!=null){  current_page_url=getIntent().getStringExtra("url");}

        tut.getSettings().setBuiltInZoomControls(true);
        tut.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        tut.setWebViewClient(new WebViewClient());
        tut.getSettings().setDefaultFontSize((int) a);
        tut.loadUrl(current_page_url);


        tut.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
 //       if(shared.loadnightmode())
   //         tut.evaluateJavascript("javascript:document.body.style.color=\"white\"",null);

        final ProgressBar pgb1 = (ProgressBar) findViewById(R.id.pgb1);

    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo ni = cm.getActiveNetworkInfo();

        if(ni!=null && ni.isConnected())
        {
            String setr="<html><head><style>p{background-color:#fff010f; }</style></head></html>";
            tut.loadUrl(current_page_url);
            if(shared.loadnightmode())
                tut.evaluateJavascript("javascript:document.body.style.color=\"white\"",null);


            tut.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pgb1.setVisibility(View.VISIBLE);
                    //newdata
                    current_page_url = url;
                    invalidateOptionsMenu();
                    //closed

                }//new Data
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    tut.loadUrl(url);
                    return true;
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        tut.loadUrl(request.getUrl().toString());
                    }
                    return true;
                }//closed
                @Override
                public void onPageFinished(final WebView view, String url) {
                    pgb1.setVisibility(View.GONE);
                    invalidateOptionsMenu();//newdata
                    super.onPageFinished(view, url);
                    tut.post(new Runnable() {
                        @Override
                        public void run() {

                            if(shared.loadnightmode())
                                tut.evaluateJavascript("javascript:document.body.style.color=\"white\"",null);
                            if(shared.loadnightmode() && !tut.canGoBack()) {
                                //tut.evaluateJavascript("javascript:document.body.style.backgroundColor=\"black\"", null);
                                tut.setBackgroundColor(Color.BLACK);
                                tut.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"a\");\n" +
                                        "for(var i=0; i<x.length; i++){\n" +
                                        "x[i].style.color=\"white\";\n}", null);
                           //     tut.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"div\");\n" +
                             //           "for(var i=0; i<x.length;i++){\n" +
                               //         "x[i].style.color=\"white\";\n", null);
                            }//nested if
                        /*    if(view.canGoBack()) {getTitle.setVisibility(View.VISIBLE); getTitle.setText(view.getTitle().toString()); *//*Temp Design part1.1 *//*

                            }*/
                        }
                    });


                }
                @Override//new data
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    pgb1.setVisibility(View.GONE);
                    invalidateOptionsMenu();
                }
            });

        }//closing if for online used.
        else{

            pgb1.setVisibility(View.GONE);
            tut.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    //newdata
                    current_page_url = url;
                    invalidateOptionsMenu();
                    //closed
                    if(shared.loadnightmode())
                        tut.evaluateJavascript("javascript:document.body.style.color=\"white\"",null);

                }//new Data
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    tut.loadUrl(url);
                    return true;
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        tut.loadUrl(request.getUrl().toString());
                    }
                    return true;
                }//closed

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if(shared.loadnightmode())
                        tut.evaluateJavascript("javascript:document.body.style.color=\"white\"",null);
                    if(shared.loadnightmode() && !tut.canGoBack()) {
                      //  tut.evaluateJavascript("javascript:document.body.style.backgroundColor=\"gray\"", null);
                       tut.setBackgroundColor(Color.BLACK);
                            tut.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"a\");\n" +
                                    "for(var i=0; i<x.length; i++){\n" +
                                    "x[i].style.color=\"white\";\n}", null);
                     //       tut.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"div\");\n" +
                       //             "for(var i=0; i<x.length;i++){\n" +
                         //           "x[i].style.backgroundColor=\"white\";\n" +
                           //         "x[i].style.backgroundColor=\"black\";}\n", null);

                    }//nested if inside else
             /*       if(view.canGoBack()) {
                        getTitle.setVisibility(View.VISIBLE); getTitle.setText(view.getTitle().toString()); *//*Temp Design part1.1 *//*

                    }
             */   }
                @Override//new data
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    pgb1.setVisibility(View.GONE);
                    invalidateOptionsMenu();
                }

            });
            tut.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        } //closing else used for offline purpose
  //  }
//});

/*        tut.addJavascriptInterface(new Object(){
            @JavascriptInterface
            public void show(){
                if(shared.loadnightmode())
                    tut.evaluateJavascript("javascript:document.body.style.color=\"white\"",null);
                    tut.evaluateJavascript("javascript:document.body.style.backgroundColor=\"yellow\"",null);
                    Toast.makeText(getApplicationContext(),"main page loaded",Toast.LENGTH_SHORT).show();
            }
        },"Android");
*/
      //  if(shared.loadnightmode())
        //  tut.evaluateJavascript("javascript:document.body.style.color=\"white\"",null);

    }
    @Override //option menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflates=getMenuInflater();
        inflates.inflate(R.menu.other,menu);
        if(!tut.canGoBack()){
            MenuItem please = menu.findItem(R.id.please);
            please.setVisible(false);
        }
        if(tut.canGoBack()){MenuItem hideact = menu.findItem(R.id.youshouldwork);
            //hideact.setVisible(false);
        }
        menu.add(0,SEARCH_ID,0,"search");
        //new Data
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String links = sharedPreferences.getString(WEB_LINKS, null);

        if (links != null) {

            Gson gson = new Gson();
            ArrayList<String> linkList = gson.fromJson(links, new TypeToken<ArrayList<String>>() {
            }.getType());

            if (linkList.contains(current_page_url)) {
                menu.getItem(0).setIcon(R.drawable.ic_bookmark_black_24dp);
            } else {
                menu.getItem(0).setIcon(R.drawable.ic_bookmark_border_black_24dp);
            }
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_bookmark_border_black_24dp);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
         super.onPrepareOptionsMenu(menu);
        return true;
    }

    //selected items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.rm:
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getSupportActionBar().hide();
                return true;
            case R.id.shre:
                Intent sharebtn=new Intent(Intent.ACTION_SEND);
                sharebtn.setType("text/plain");
                String url="App is in devlopment";
                sharebtn.putExtra(Intent.EXTRA_SUBJECT,"Subject/Title");
                sharebtn.putExtra(Intent.EXTRA_TEXT,"hellow");
                startActivity(Intent.createChooser(sharebtn,"Choose sharing method"));
                return  true;
            //new data
            case R.id.please:
                String message;

                SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
                String jsonLink = sharedPreferences.getString(WEB_LINKS, null);
                String jsonTitle = sharedPreferences.getString(WEB_TITLE, null);


                if (jsonLink != null && jsonTitle != null) {

                    Gson gson = new Gson();
                    ArrayList<String> linkList = gson.fromJson(jsonLink, new TypeToken<ArrayList<String>>() {
                    }.getType());

                    ArrayList<String> titleList = gson.fromJson(jsonTitle, new TypeToken<ArrayList<String>>() {
                    }.getType());

                    if (linkList.contains(current_page_url)) {
                        linkList.remove(current_page_url);
                        titleList.remove(tut.getTitle().trim());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                        editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                        editor.apply();


                        message = "Bookmark Removed";

                    } else {
                        linkList.add(current_page_url);
                        titleList.add(tut.getTitle().trim());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                        editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                        editor.apply();

                        message = "Bookmarked";
                    }
                } else {

                    ArrayList<String> linkList = new ArrayList<>();
                    ArrayList<String> titleList = new ArrayList<>();
                    linkList.add(current_page_url);
                    titleList.add(tut.getTitle());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                    editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                    editor.apply();

                    message = "Bookmarked";
                }

                shared.DisplayMsg(message);
                invalidateOptionsMenu();
                return true;
            //closed
            case R.id.youshouldwork:
                //Intent in =new Intent(this,BookmarkActivity.class);
                //String urlpass=tut.getUrl().toString();
                //in.putExtra("getpreviuosdata",urlpass);
                //startActivity(in);
                startActivity(new Intent(this,BookmarkActivity.class)); finish();
                return  true;

            case SEARCH_ID:
                search(); return true;
        }
        return super.onOptionsItemSelected(item);
    }

  /*  @Override
    public void onBackPressed() {
        getSupportActionBar().show();
        super.onBackPressed();
    }*/

    public void search(){
    // EditText editTex;
     //editTex=new EditText(this);
     //getActionBar().setCustomView(editTex);
      //getActionBar().setDisplayShowTitleEnabled(false);
//if(shared.loadnightmode()==true){ findbox.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);}else{}
      final   ViewGroup.MarginLayoutParams p=(ViewGroup.MarginLayoutParams)tut.getLayoutParams();
        final ViewGroup.LayoutParams c=(ViewGroup.LayoutParams)tut.getLayoutParams();
        p.topMargin=60;
      p.height=10000;
      tut.setLayoutParams(p);
      container=(LinearLayout) findViewById(R.id.linearlayout);
      //LinearLayout container=new LinearLayout(tutorial.this);
      //LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.)
      nextbutton=new Button(this);
      nextbutton.setText("next");
      nextbutton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              tut.findNext(true);
          }
      });
      container.addView(nextbutton);

      closebutton =new Button(this);
      closebutton.setText("close");
      closebutton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              container.removeAllViews();
              p.topMargin=0;
              p.height=10000;
          // tut.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
          }
      });


      container.addView(closebutton);


      findbox=new EditText(this);
      findbox.setMinEms(30);
      findbox.setSingleLine(true);
      findbox.setHint("Search");
      findbox.setOnKeyListener(new View.OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
              if((event.getAction()==KeyEvent.ACTION_DOWN)&&((keyCode==KeyEvent.KEYCODE_ENTER)))
                  tut.findAll(findbox.getText().toString());
              try{

                  Method m=WebView.class.getMethod("setFindisup",Boolean.TYPE);
                  m.invoke(tut,true);

              }catch (Exception ignored){}
              return false;
          }
      });
      container.addView(findbox);

     // getActionBar().setCustomView(container);

  }
    @Override
    public void onBackPressed() {
      if(tut.canGoBack()){
//        first.setBackgroundColor(Color.parseColor("#000000"));
          tut.goBack();
     //     getTitle.setVisibility(View.GONE);
      }else
        super.onBackPressed();


    }

}
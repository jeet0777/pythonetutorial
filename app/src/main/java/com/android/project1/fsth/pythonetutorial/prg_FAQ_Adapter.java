package com.android.project1.fsth.pythonetutorial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.android.project1.fsth.pythonetutorial.Program_and_FAQ_view.ISFAQ;

public class prg_FAQ_Adapter extends RecyclerView.Adapter<prg_FAQ_Adapter.prg_FAQ_Viewholder> implements Filterable {
private ArrayList<Prg_and_FAQ_items> items;
private ArrayList<Prg_and_FAQ_items> dummyitems;

Context context;
    public int pos;
    public prg_FAQ_Adapter(ArrayList<Prg_and_FAQ_items> items,Context context)

    {
        this.context=context;
        this.items=items;
        this.dummyitems=new ArrayList<>(items);
    }
    @NonNull
    @Override
    public prg_FAQ_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view= LayoutInflater.from(parent.getContext()).
            inflate(R.layout.prg_and_faq_items,parent,false);
            prg_FAQ_Viewholder prg_faq_vh=new prg_FAQ_Viewholder(view);
            return prg_faq_vh;

    }

    @Override
    public void onBindViewHolder(@NonNull prg_FAQ_Viewholder holder, final int position) {
         pos=position+1;
        final Prg_and_FAQ_items itemsfromprg=this.items.get(position);
        if(pos<10)
        holder.prgcounter.setText(String.valueOf("0"+pos));
        else
            holder.prgcounter.setText(String.valueOf(pos));

        Random random=new Random();
        int color= Color.argb(145,random.nextInt()+5,random.nextInt()+5,random.nextInt()+5);
        Drawable d=context.getResources().getDrawable(R.drawable.circle_counter,context.getTheme());
        d.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        holder.prgcounter.setBackground(d);
        holder.prgtopics.setText(itemsfromprg.getTopicsname());
        holder.prgtopics.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,prgandfaqweb.class);
                if(ISFAQ==true)
                {
//                    Toast.makeText(context, "FAQ123", Toast.LENGTH_SHORT).show();
                    intent.putExtra("FAQv",String.valueOf(itemsfromprg.getTopicsname()));
                    context.startActivity(intent);
                    //return;
                }

                else if(!ISFAQ){
                    intent.putExtra("PRG",String.valueOf(itemsfromprg.getTopicsname()));
                    context.startActivity(intent);
//                    return;

                }
              }
        });



    }


    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public Filter getFilter() {
        return FIndFilter;
    }


    public static class prg_FAQ_Viewholder extends RecyclerView.ViewHolder{

    public TextView prgcounter,prgtopics;

    public prg_FAQ_Viewholder(@NonNull View itemView) {
        super(itemView);
        prgcounter=itemView.findViewById(R.id.prg_and_faqcounter);
        prgtopics=itemView.findViewById(R.id.prg_and_faqTopics);
    }
}
//    @Override
//    public Filter getFilter() {
//
//        return FIndFilter;
//
//    }

    private Filter FIndFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Prg_and_FAQ_items> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(getGroupArrayList);
                filteredList.addAll(dummyitems);
            }
            else {

                String filteredpattern = constraint.toString().toLowerCase().trim();
                for (Prg_and_FAQ_items item : dummyitems) {

                    if (item.getTopicsname().toLowerCase().contains(filteredpattern)) {
                        filteredList.add(item);
                        //   Log.d("mention me",item.getName());
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

//            groupArrayList.clear();
//            Toast.makeText(activity, results.values.toString(), Toast.LENGTH_SHORT).show();

            items.clear();//Working
            //Cont.............................................
//            groupArrayList.addAll((List)results.values);

//            groupmem.add(new GetTopics("starting"));

//           submemberList.clear();
            items.addAll((List)results.values);

//                submemberList.addAll((List)results.values);
//            Log.d("list",groupmemeber.get());
            notifyDataSetChanged();
            // Log.d("no",String.valueOf(submemberList.contains("AI")));

        }
    };









}

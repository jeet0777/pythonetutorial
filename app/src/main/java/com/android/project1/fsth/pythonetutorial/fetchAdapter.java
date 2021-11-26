package com.android.project1.fsth.pythonetutorial;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

public class fetchAdapter extends RecyclerView.Adapter<fetchAdapter.myViewHolder> {
Context context;
ArrayList<profile_Avtivity> profiles;

public fetchAdapter(Context c,ArrayList<profile_Avtivity> p){
    context=c;
    profiles=p;
}

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.commentsdesign,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {

        Picasso.get().load(profiles.get(position).getImageurl()).into(holder.url, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap=((BitmapDrawable)holder.url.getDrawable()).getBitmap();
                RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(Resources.getSystem(),bitmap);
                roundedBitmapDrawable.setCircular(true);
                roundedBitmapDrawable.setCornerRadius(Math.max(bitmap.getWidth(),bitmap.getHeight())/2.0f);
                holder.url.setImageDrawable(roundedBitmapDrawable);
            }

            @Override
            public void onError(Exception e) {
            holder.url.setImageResource(R.drawable.ic_code);
            }
        });
        holder.name.setText(profiles.get(position).getName());
        holder.date.setText(profiles.get(position).getTime());
        holder.comment.setText(profiles.get(position).getComment());
        holder.shortdate.setText(profiles.get(position).getDate());
      }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView url;

        TextView name,date,comment,shortdate;
    public myViewHolder(View itemView) {
        super(itemView);

        url=itemView.findViewById(R.id.getimage);
        name=(TextView)itemView.findViewById(R.id.getname);
        date=(TextView)itemView.findViewById(R.id.getTime);
        comment=(TextView)itemView.findViewById(R.id.getComments);
        shortdate=(TextView)itemView.findViewById(R.id.shortdate);
    }
}



}

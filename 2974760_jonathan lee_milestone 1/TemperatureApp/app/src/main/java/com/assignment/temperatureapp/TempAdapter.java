package com.assignment.temperatureapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TempAdapter extends RecyclerView.Adapter<TempAdapter.TempHolder> {

    List<Tempe> tempes;
    Context context;
    SqlDb db;

    public TempAdapter(List<Tempe> tempes, Context context) {
        this.tempes=tempes;
        this.context = context;
        this.db=new SqlDb(context);
    }

    public void setItems() {
        this.tempes = db.getAllHistory();
    }

    @NonNull
    @Override
    public TempHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_item,parent,false);
        return new TempHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempHolder holder, @SuppressLint("RecyclerView") int position) {

        Tempe item=tempes.get(position);

        holder.title.setText(item.getDegree()+" °C");
        holder.desc.setText(item.getDate());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailsActivity.class);
                intent.putExtra("deg",item.getDegree());
                intent.putExtra("date",item.getDate());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tempes.size();
    }


    class TempHolder extends RecyclerView.ViewHolder{

        TextView title,desc;


        public TempHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.degItem);
            desc=itemView.findViewById(R.id.dateItem);





        }
    }

}

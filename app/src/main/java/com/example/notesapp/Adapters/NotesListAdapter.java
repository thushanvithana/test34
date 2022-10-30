package com.example.notesapp.Adapters;

import android.content.Context;
import android.hardware.lights.LightState;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.Models.Notes;
import com.example.notesapp.NotesClick;
import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    Context context;
    List<Notes> list;
    NotesClick notesClick;



    public NotesListAdapter(Context context, List<Notes> list, NotesClick notesClick) {
        this.context = context;
        this.list = list;
        this.notesClick = notesClick;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list,parent,false));



    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        holder.textView_tittle.setText(list.get(position).getTittle());
        holder.textView_tittle.setSelected(true);


        holder.textView_notes.setText(list.get(position).getNotes());
        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        if (list.get(position).isPinned()){
            holder.pin_image.setImageResource(R.drawable.ic_baseline_push_pin_24);
        }
        else
        {
            holder.pin_image.setImageResource(0);

        }
        int color_code=getRandomColr();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code,null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesClick.onClick(list.get(holder.getAdapterPosition()));



            }
        });
        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                notesClick.onLongClick(list.get(holder.getAdapterPosition()),holder.notes_container);

                return true;
            }
        });

    }
    private int getRandomColr(){
        List<Integer> colorCode =new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random=new Random();
        int random_colr=random.nextInt(colorCode.size());
        return colorCode.get(random_colr);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void  filtered_list(List<Notes> filter_list){
        list =filter_list;
        notifyDataSetChanged();
    }
}
class  NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView textView_tittle,textView_notes,textView_date;
    ImageView pin_image;
    public NotesViewHolder(@NonNull View itemView) {

        super(itemView);

        notes_container=itemView.findViewById(R.id.notes_card_container);
        textView_tittle=itemView.findViewById(R.id.tittle_text);
        textView_notes=itemView.findViewById(R.id.textview_notes);
        textView_date=itemView.findViewById(R.id.textview_date);
        pin_image=itemView.findViewById(R.id.image_view_pin);
    }
}

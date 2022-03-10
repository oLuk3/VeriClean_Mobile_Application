package io.agora.openlive;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    ArrayList<Task> taskArrayList;
   private OnFeedbackListener mOnFeedbackListener;

    public MyAdapter(Context context, ArrayList<Task> taskArrayList,  OnFeedbackListener OnFeedbackListener) {
        this.context = context;
        this.taskArrayList = taskArrayList;
        this.mOnFeedbackListener = OnFeedbackListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);


        return new MyViewHolder(v, mOnFeedbackListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       Task task = taskArrayList.get(position);

        holder.assigned_floor.setText(task.assigned_floor);
        holder.assigned_room.setText(task.assigned_room);
        holder.assigned_method.setText(task.assigned_method);
        holder.assigned_time_in.setText(task.assigned_time_in);
        holder.assigned_time_out.setText(task.assigned_time_out);

    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        TextView assigned_method,assigned_room,method,createdAt, assigned_floor;
        TextView assigned_room, assigned_floor, assigned_method,assigned_time_in,assigned_time_out;
        OnFeedbackListener OnFeedbackListener;

        public MyViewHolder(@NonNull View itemView, OnFeedbackListener OnFeedbackListener) {
            super(itemView);

            assigned_floor = itemView.findViewById(R.id.textViewFloor);
            assigned_room = itemView.findViewById(R.id.textViewRoom);
            assigned_method = itemView.findViewById(R.id.textViewMethod);
            assigned_time_in = itemView.findViewById(R.id.textViewTime);
            assigned_time_out = itemView.findViewById(R.id.textViewTime2);

            this.OnFeedbackListener = OnFeedbackListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            OnFeedbackListener.OnFeedbackclick(getAdapterPosition());
        }
    }

    public interface OnFeedbackListener{
        void OnFeedbackclick(int position);
    }

}

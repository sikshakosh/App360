package com.android.app360.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;
import com.android.appcompose.network.Classroom;

import java.util.ArrayList;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ClassroomViewHolder> {

    Context context;
    ArrayList<Classroom> classrooms;

    public ClassroomAdapter(Context context, ArrayList<Classroom> articles) {
        this.context = context;
        this.classrooms = articles;
    }

    @NonNull
    @Override
    public ClassroomAdapter.ClassroomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_home_item, parent, false);
        return new  ClassroomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassroomAdapter.ClassroomViewHolder holder, int position) {
        Classroom classroom = classrooms.get(position);
        holder.bindTo(classroom);
    }

    @Override
    public int getItemCount() {
        return classrooms.size();
    }

    public class ClassroomViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mNewsText;
        private Button mBtnAction1;
        private Button mBtnAction2;

        public ClassroomViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitleText = (TextView)itemView.findViewById(R.id.title);
            mInfoText = (TextView)itemView.findViewById(R.id.subtitle);
            mNewsText = (TextView)itemView.findViewById(R.id.supporting_text);
            mBtnAction1 = (Button)itemView.findViewById(R.id.action_button_1);
            mBtnAction2 = (Button)itemView.findViewById(R.id.action_button_2);

        }

        void bindTo(Classroom classroom){
            //Populate the textviews with data
            mTitleText.setText(classroom.getChash());
            mInfoText.setText(classroom.getChash());
            mNewsText.setText("Added a new question in");
            mBtnAction1.setText("View");
            mBtnAction2.setText("Favourite");
        }
    }
}

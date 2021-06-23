package com.android.app360.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;

import java.util.ArrayList;

public class PostModelAdapter extends RecyclerView.Adapter  {

    //Member variables
    private ArrayList<PostModel> mPostData;
    private Context mContext;
    int total_types;



    public static class QuestionTypeViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView subtitle;
        ImageView image;

        public QuestionTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            subtitle = (TextView)itemView.findViewById(R.id.subtitle);
        }
    }

    /**
     * Constructor that passes in the classroom data and the context
     * @param context Context of the application
     * @param classroomData ArrayList containing the classroom data
     */
    public PostModelAdapter(Context context, ArrayList<PostModel> classroomData) {
        this.mPostData = classroomData;
        this.mContext = context;
        total_types = classroomData.size();
    }


    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create ViewHolder.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case PostModel.POST_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post_item,parent,false);
                return new QuestionTypeViewHolder(view);
            case PostModel.POST_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post_header,parent,false);
                return new QuestionTypeViewHolder(view);
            case PostModel.POST_SHOW_MORE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post_more,parent,false);
                return new QuestionTypeViewHolder(view);

        }
        return null;
        //new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_connection_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        switch (mPostData.get(position).type){
            case 1:
                return PostModel.POST_TYPE;
            case 0:
                return PostModel.POST_HEADER;
            case 2:
                return PostModel.POST_SHOW_MORE;
            default:
                return -1;
        }

        // return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostModel currentClassroom = mPostData.get(position);
        //Populate the textviews with data
        if(currentClassroom != null){
            switch (currentClassroom.type){
                case PostModel.POST_TYPE:
                    ((QuestionTypeViewHolder)holder).title.setText(currentClassroom.title);
                    ((QuestionTypeViewHolder)holder).subtitle.setText(currentClassroom.subtitle);
                    break;
                case PostModel.POST_HEADER:
//                    ((ConnectioinTypeViewHolder)holder).title.setText(currentClassroom.title);
//                    ((ConnectioinTypeViewHolder)holder).subtitle.setText(currentClassroom.subtitle);
                    break;
                case PostModel.POST_SHOW_MORE:
                   // ((QuestionTypeViewHolder)holder).title.setText(currentClassroom.title);
//                    ((ConnectioinTypeViewHolder)holder).subtitle.setText(currentClassroom.subtitle);
                    break;
            }
        }

    }




    /**
     * Required method for determining the size of the data set.
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mPostData.size();
    }



}
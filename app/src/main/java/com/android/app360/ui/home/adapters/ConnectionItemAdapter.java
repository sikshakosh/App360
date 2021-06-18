package com.android.app360.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;

import java.util.ArrayList;

public class ConnectionItemAdapter extends RecyclerView.Adapter<ConnectionItemAdapter.ViewHolder>  {

    //Member variables
    private ArrayList<ConnectionItem> mConnectionData;
    private Context mContext;

    /**
     * Constructor that passes in the classroom data and the context
     * @param context Context of the application
     * @param classroomData ArrayList containing the classroom data
     */
    public ConnectionItemAdapter(Context context, ArrayList<ConnectionItem> classroomData) {
        this.mConnectionData = classroomData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create ViewHolder.
     */
    @Override
    public ConnectionItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_connection_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(ConnectionItemAdapter.ViewHolder holder, int position) {
        //Get current classroom
        ConnectionItem currentClassroom = mConnectionData.get(position);
        //Populate the textviews with data
        holder.bindTo(currentClassroom);
    }


    /**
     * Required method for determining the size of the data set.
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mConnectionData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        //Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mNewsText;
        private Button mBtnAction1;
        private Button mBtnAction2;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mTitleText = (TextView)itemView.findViewById(R.id.title);
            mInfoText = (TextView)itemView.findViewById(R.id.subtitle);
           // mNewsText = (TextView)itemView.findViewById(R.id.supporting_text);
            mBtnAction1 = (Button)itemView.findViewById(R.id.action_button_1);
            mBtnAction2 = (Button)itemView.findViewById(R.id.action_button_2);
        }

        void bindTo(ConnectionItem currentClassroom){
            //Populate the textviews with data
            mTitleText.setText(currentClassroom.getTitle());
            mInfoText.setText(currentClassroom.getInfo());
           // mNewsText.setText("Added a new question in");
            mBtnAction1.setText("View");
            mBtnAction2.setText("Favourite");
        }
    }
}
package com.android.app360.ui.welcome.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;
import com.android.app360.common.constants.DataType;
import com.android.appcompose.database.model.ClassroomModel;
import com.android.appcompose.database.model.MentorModel;
import com.android.appcompose.network.model.Mentor;

import java.util.ArrayList;


public class ChildRecyclerViewAdapter<T> extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ViewHolder>  {

    //Member variables
    private ArrayList<Object> data = new ArrayList<Object>();

    private Context mContext;

    private DataType type;
    /**
     * Constructor that passes in the sports data and the context
     * @param childData ArrayList containing the sports data
     * @param context Context of the application
     */
    public ChildRecyclerViewAdapter(Context context, ArrayList<T> childData) {

        this.mContext = context;
        this.setData((ArrayList<Object>) childData);
        for (Object obj: childData){

            if (obj instanceof ClassroomModel){

                this.type = DataType.FEATURED_CLASSROOMS;
            }else if (obj instanceof MentorModel){
                this.type =  DataType.FEATURED_MENTORS;
            }else{

            }
           break;
        }

    }






    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create ViewHolder.
     */
    @Override
    public ChildRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildRecyclerViewAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.welcome_grid_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(ChildRecyclerViewAdapter.ViewHolder holder, int position) {
        switch (this.type){
            case FEATURED_CLASSROOMS:
                //Get current sport
                ClassroomModel currentSport = (ClassroomModel) data.get(position);
                //Populate the textviews with data
                holder.bindToClassroom(currentSport);
                break;
            case FEATURED_MENTORS:
                //Get current sport
                MentorModel mentor = (MentorModel) data.get(position);
                //Populate the textviews with data
                holder.bindToMentor(mentor);
                break;
            default:
                Log.d("","NA");
        }

    }


    /**
     * Required method for determining the size of the data set.
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        int totalItems = this.data.size();

        return totalItems;
    }


    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        //Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mSubtitleText;
        private ImageView mImageView;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mTitleText = (TextView)itemView.findViewById(R.id.title);
            mSubtitleText = (TextView)itemView.findViewById(R.id.subtitle);
            mImageView = (ImageView) itemView.findViewById(R.id.image);

        }

        void bindToClassroom(ClassroomModel item){
            //Populate the textviews with data
            mTitleText.setText(item.getName());
            mSubtitleText.setText(item.getAdmin());


        }
        void bindToMentor(MentorModel item){
            //Populate the textviews with data
            mTitleText.setText(item.getName());
            mSubtitleText.setText("Teacher");

            String base64EncodedString = item.getImage();
            byte[] imageBytes = Base64.decode(base64EncodedString,Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            mImageView.setImageBitmap(decodedImage);
        }
    }
}
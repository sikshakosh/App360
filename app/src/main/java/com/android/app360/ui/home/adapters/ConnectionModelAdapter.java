package com.android.app360.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app360.R;

import java.util.ArrayList;

public class ConnectionModelAdapter extends RecyclerView.Adapter  {

    //Member variables
    private ArrayList<ConnectionModel> mConnectionData;
    private Context mContext;
    int total_types;

    public static class InvitationTypeViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView subtitle;
        ImageView image;

        public InvitationTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            subtitle = (TextView)itemView.findViewById(R.id.subtitle);
        }
    }

    public static class ConnectioinTypeViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView subtitle;
        ImageView image;

        public ConnectioinTypeViewHolder(@NonNull View itemView) {
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
    public ConnectionModelAdapter(Context context, ArrayList<ConnectionModel> classroomData) {
        this.mConnectionData = classroomData;
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
            case ConnectionModel.CONNECTIOIN_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_connection_item,parent,false);
                return new ConnectioinTypeViewHolder(view);
            case ConnectionModel.INVITATION_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_connection_invite_item,parent,false);
                return new ConnectioinTypeViewHolder(view);
            case ConnectionModel.INVITATION_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_connection_header,parent,false);
                return new ConnectioinTypeViewHolder(view);
            case ConnectionModel.INVITATION_SHOW_MORE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_connection_more,parent,false);
                return new ConnectioinTypeViewHolder(view);

        }
        return null;
        //new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_connection_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        switch (mConnectionData.get(position).type){
            case 0:
                return ConnectionModel.INVITATION_TYPE;
            case 1:
                return ConnectionModel.CONNECTIOIN_TYPE;
            case 2:
                return ConnectionModel.INVITATION_HEADER;
            case 3:
                return ConnectionModel.INVITATION_SHOW_MORE;
            default:
                return -1;
        }

        // return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ConnectionModel currentClassroom = mConnectionData.get(position);
        //Populate the textviews with data
        if(currentClassroom != null){
            switch (currentClassroom.type){
                case ConnectionModel.CONNECTIOIN_TYPE:
                    ((ConnectioinTypeViewHolder)holder).title.setText(currentClassroom.title);
                    ((ConnectioinTypeViewHolder)holder).subtitle.setText(currentClassroom.subtitle);
                    break;
                case ConnectionModel.INVITATION_TYPE:
                    ((ConnectioinTypeViewHolder)holder).title.setText(currentClassroom.title);
                    ((ConnectioinTypeViewHolder)holder).subtitle.setText(currentClassroom.subtitle);
                    break;
                case ConnectionModel.INVITATION_HEADER:
//                    ((ConnectioinTypeViewHolder)holder).title.setText(currentClassroom.title);
//                    ((ConnectioinTypeViewHolder)holder).subtitle.setText(currentClassroom.subtitle);
                    break;
                case ConnectionModel.INVITATION_SHOW_MORE:
//                    ((ConnectioinTypeViewHolder)holder).title.setText(currentClassroom.title);
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
        return mConnectionData.size();
    }



}
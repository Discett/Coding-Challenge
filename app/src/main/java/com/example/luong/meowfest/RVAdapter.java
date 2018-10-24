package com.example.luong.meowfest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Luong on 10/23/2018.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private Context context;
    private List<CatType> catTypes;

    public RVAdapter(Context context, List<CatType> catTypes) {
        this.context = context;
        this.catTypes = catTypes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.catlayout,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(catTypes.get(position).getTitle());
        holder.description.setText(catTypes.get(position).getDescription());
        holder.timestamp.setText(catTypes.get(position).getTimeStamp());
        holder.image.setImageBitmap(catTypes.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return catTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public ImageView image;
        public TextView timestamp;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}

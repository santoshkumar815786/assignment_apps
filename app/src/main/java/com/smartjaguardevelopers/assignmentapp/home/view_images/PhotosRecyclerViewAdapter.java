package com.smartjaguardevelopers.assignmentapp.home.view_images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartjaguardevelopers.assignmentapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.PhotosViewHolder>
{
    private Context context;
    private List<Photo> photosList;

    public PhotosRecyclerViewAdapter(Context context, List<Photo> photosList)
    {
        this.context = context;
        this.photosList = photosList;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_rv_images_item,parent,false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        Photo photo = photosList.get(position);
         Picasso.get()
                .load(photo.getThumbnailUrl())
                .into(holder.ivPhoto, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.i("Santosh","Image load successfull...");
                            }
                            @Override
                            public void onError(Exception e) {
                                Log.i("Santosh","Image Load Exception  : "+e.getMessage());
                            }
                        });


        holder.tvId.setText("" + photo.getId());
        holder.tvTitle.setText(""+photo.getTitle());
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    class PhotosViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ivPhoto;
        public TextView tvId;
        public TextView tvTitle;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPhoto = (ImageView)itemView.findViewById(R.id.ivPhoto);
            tvId = (TextView)itemView.findViewById(R.id.tvId);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
        }

    }
}

package com.smartjaguardevelopers.assignmentapp.home.view_images;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartjaguardevelopers.assignmentapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewImagesFragment extends Fragment
{
    private RecyclerView rvPhotosDiplayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_images_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvPhotosDiplayer = (RecyclerView)getActivity().findViewById(R.id.rvPhotosDisplayer) ;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Photo>> call = api.getPhotos();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> photosList = response.body();

                rvPhotosDiplayer.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvPhotosDiplayer.setAdapter(new PhotosRecyclerViewAdapter(getActivity(),photosList));
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.i("Santosh","Exception : "+t.getMessage());
            }
        });
    }
}

package com.moneyhawk.videobaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.moneyhawk.videobaseapp.api.ApiInterface;
import com.moneyhawk.videobaseapp.model.AppName;
import com.moneyhawk.videobaseapp.model.ListModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);

        Retrofit retrofit = com.moneyhawk.videobaseapp.api.ApiClient.getclient();
        apiInterface = retrofit.create(com.moneyhawk.videobaseapp.api.ApiInterface.class);

        gethorizontalscrollitem();

        MediaController  mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
       // videoView.setVideoURI((Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")));
        videoView.setVideoURI((Uri.parse("https://codingsansar.com/api/android2/android/uploads/2839596-hd_1280_720_30fps.mp4")));
        videoView.start();
    }

    public void gethorizontalscrollitem() {



        apiInterface.fetchmaincategories().enqueue(new Callback<ListModel>() {
            @Override
            public void onResponse(Call<ListModel> call, Response<ListModel> response) {

                try {
                    if (response != null) {

                        Log.e("apihit","api responce: "+ response.body().getMessage());


                        if (response.body().isStatus()==true) {
                          //  setadapter(response.body().getData());
//                            setadapterhardware(response.body().getData());
//                            GalleryList(new Gson().toJson(response.body()).toString()+"");



                        } else {
                            Toast.makeText(VideoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<ListModel> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });

//
    }


}
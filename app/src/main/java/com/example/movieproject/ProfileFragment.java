package com.example.movieproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieproject.Model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    ImageView iv_profil;
    Button btn_gal, btn_cam;
    UserSqliteHelper userSqliteHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        iv_profil = v.findViewById(R.id.iv_profil);
        btn_gal = v.findViewById(R.id.btn_gal);
        btn_cam = v.findViewById(R.id.btn_cam);

        userSqliteHelper = new UserSqliteHelper(getContext());

        btn_gal.setOnClickListener(onClickListenerGal);
        btn_cam.setOnClickListener(onClickListenerCam);


        return v;
    }

    private View.OnClickListener onClickListenerGal = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 0);
        }
    };

    private View.OnClickListener onClickListenerCam = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto , 1);
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    iv_profil.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    iv_profil.setImageURI(selectedImage);
                }
                break;
        }
    }


}

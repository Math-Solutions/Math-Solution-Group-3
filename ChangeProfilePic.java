package com.example.mathsolutionprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ChangeProfilePic extends AppCompatActivity {
    private ImageButton camera;
    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_pic);
        camera = (ImageButton) findViewById(R.id.cameraBtn);
        pic = (ImageView) findViewById(R.id.picIV);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                Intent intent1 = new Intent(this, MainActivity);
                startActivityForResult(intent,0);
//                startActivity(intent1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        pic.setImageBitmap(bitmap);
//        ändra databasen också
    }
}

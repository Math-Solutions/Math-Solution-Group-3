package com.example.pa2576;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FullScreenImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        ImageView fullScreenImage = findViewById(R.id.fullScreenImage);
        ViewSolution.viewImage(fullScreenImage);
        TextView comment = findViewById(R.id.textComment);
        comment.setText(ViewSolution.comment.getText());


    }
}

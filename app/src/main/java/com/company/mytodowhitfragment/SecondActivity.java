package com.company.mytodowhitfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private final int REQUEST_CODE_IMAGE_PICK = 111;
    private Uri imageUri;
    private ImageView imageView;
    private EditText editText1;
    private EditText editText2;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText1 = findViewById(R.id.edit_text_1);
        editText2 = findViewById(R.id.edit_text_2);
        radioButton1 = findViewById(R.id.r_btn1);
        radioButton2 = findViewById(R.id.r_btn2);
        radioButton3 = findViewById(R.id.r_btn3);
        imageView = findViewById(R.id.id_image_2);

        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Intent data = new Intent(SecondActivity.this, MainActivity.class);
                String name = editText1.getText().toString();
                String description = editText2.getText().toString();
                data.putExtra("name", name);
                data.putExtra("description", description);
                data.putExtra("high", radioButton1.isChecked());
                data.putExtra("medium", radioButton2.isChecked());
                data.putExtra("low", radioButton3.isChecked());
                data.setData(imageUri);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        imageView = findViewById(R.id.id_image_2);
        findViewById(R.id.btn_pick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pickerIntent = new Intent(Intent.ACTION_PICK);
                pickerIntent.setType("image/*");
                startActivityForResult(pickerIntent, REQUEST_CODE_IMAGE_PICK);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_IMAGE_PICK) {
                imageUri = data.getData();
                if (null != imageUri) {
                    imageView.setImageURI(imageUri);
                }
            }
        }
    }

}


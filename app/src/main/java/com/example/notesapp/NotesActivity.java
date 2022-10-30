package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notesapp.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {

    EditText editText_tittle,editText_notes;
    ImageView image_save;
    Notes notes;
    boolean is_old_note=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_notes);

        image_save=findViewById(R.id.image_view_save);
        editText_tittle=findViewById(R.id.edit_text_tittle);
        editText_notes=findViewById(R.id.edit_text_notes);
        is_old_note=true;

        notes=new Notes();
        try {
            notes= (Notes) getIntent().getSerializableExtra("old_note");
            editText_tittle.setText(notes.getTittle());
            editText_notes.setText(notes.getNotes());

        }catch (Exception e){
            e.printStackTrace();
        }

        image_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tittle=editText_tittle.getText().toString();
                String description=editText_notes.getText().toString();


                if (description.isEmpty()){
                    Toast.makeText(NotesActivity.this, "Please add some  notes !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat format=new SimpleDateFormat("EEE,d MMM yyyy HH : mm a");
                Date date=new Date();


                if (is_old_note){
                    notes=new Notes();
                }

                notes.setTittle(tittle);
                notes.setNotes(description);
                notes.setDate(format.format(date));

                Intent intent= new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });
    }
}
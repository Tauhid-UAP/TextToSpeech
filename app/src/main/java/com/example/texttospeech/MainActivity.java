package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech tts;
    private int compatibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if(i == TextToSpeech.SUCCESS)
                    compatibility=tts.setLanguage(Locale.US);
                else
                    Toast.makeText(getApplicationContext(), "This feature is not supported on your device.", Toast.LENGTH_SHORT).show();


            }

        });

        Button speakButton=(Button) findViewById(R.id.speakButton);

        speakButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                if((compatibility == TextToSpeech.LANG_NOT_SUPPORTED) || (compatibility == TextToSpeech.LANG_MISSING_DATA))
                    Toast.makeText(getApplicationContext(), "This feature is not supported on your device.", Toast.LENGTH_SHORT).show();
                else {


                    EditText speechBox=(EditText) findViewById(R.id.speechBox);

                    String text=speechBox.getText().toString();


                    tts.speak(text, TextToSpeech.QUEUE_ADD, null, "Speech");

                }

            }

        });



    }
}

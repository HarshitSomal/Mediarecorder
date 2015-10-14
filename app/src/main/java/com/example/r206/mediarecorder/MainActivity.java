package com.example.r206.mediarecorder;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaRecorder recorder;
    static int i= 0;
    ImageButton start , stop;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recorder = new MediaRecorder();
        start = (ImageButton)findViewById(R.id.imageButton1);
        stop = (ImageButton)findViewById(R.id.imageButton2);
        stop.setEnabled(false);
        Button next=(Button)findViewById(R.id.nxt);
    }


    public void start(View v)
    {

        String sdcard =  Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = sdcard + "/Files";
        Toast.makeText(this, path, Toast.LENGTH_LONG).show();
        File f = new File(path);

        if(!f.exists())
        {

            f.mkdir();
            Toast.makeText(this, "Congrats folder is created", Toast.LENGTH_LONG).show();

        }
        String outputfile = path +"/Record"+ ++i + ".3gp";
        recorder.reset();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(outputfile);

        try
        {
            recorder.prepare();
            recorder.start();
            start.setEnabled(false);
            stop.setEnabled(true);
        }
        catch (IllegalStateException | IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void stop(View v)
    {
        recorder.stop();
        stop.setEnabled(false);
        start.setEnabled(true);

    }

    public void nxtbtn(View v)
    {
        Intent p=new Intent(MainActivity.this,Fetch.class);
        startActivity(p);

    }

}

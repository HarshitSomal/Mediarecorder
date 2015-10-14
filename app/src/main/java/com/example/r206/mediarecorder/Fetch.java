package com.example.r206.mediarecorder;

/**
 * Created by R206 on 12-10-2015.
 */
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Fetch extends AppCompatActivity implements OnItemClickListener {
    File file;
    MyAdapter adapter;
    ListView recordingList;
    ArrayList<File> list;
    Button playbutton, pausebutton, stopbutton;
    MediaPlayer player = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetch);
        recordingList = (ListView) findViewById(R.id.listView1);
        playbutton = (Button) findViewById(R.id.play);
        pausebutton = (Button) findViewById(R.id.pause);
        stopbutton = (Button) findViewById(R.id.Stop);


        // ================== Initialize the Variable ==========================
        player = new MediaPlayer();


        // ===== code Responsible to Fetch data from Sd card and store list variable==============================
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        list = new ArrayList<File>();
        String s = sdcard + "/Files";
        File f = new File(s);
        File file[] = f.listFiles();
        for (File ff : file) {

            list.add(ff);

        }
        //========================================================================================================
        adapter = new MyAdapter(this, R.layout.items, list);
        recordingList.setAdapter(adapter);

        // =============== Handling Event ===========================================================
        recordingList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

        Toast.makeText(this, position + "", Toast.LENGTH_LONG).show();

        playRecord(position);

        //Toast.makeText(this, arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_LONG).show();

    }

    public void playRecord(int pos) {
        Toast.makeText(this, list.get(pos).getAbsolutePath(), Toast.LENGTH_LONG).show();
        player.reset();
        try {

            player.setDataSource(list.get(pos).getAbsolutePath());
            player.prepare();
            player.start();
        } catch (Exception e) {


            e.printStackTrace();
        }

    }

    public void play(View view) {
        Toast.makeText(this, "Playing sound",
                Toast.LENGTH_SHORT).show();
        player.start();

        playbutton.setEnabled(false);
        pausebutton.setEnabled(true);
        stopbutton.setEnabled(true);
    }


    public void pause(View v) {

        Toast.makeText(this, "Pausing sound",
                Toast.LENGTH_SHORT).show();

        player.pause();
        pausebutton.setEnabled(false);
        playbutton.setEnabled(true);
        stopbutton.setEnabled(true);
    }

    public void stop(View v) {

        Toast.makeText(this, "Stop sound ",
                Toast.LENGTH_SHORT).show();

        player.stop();
        stopbutton.setEnabled(false);
        playbutton.setEnabled(true);
        pausebutton.setEnabled(true);
    }


    class MyAdapter extends BaseAdapter {
        private List<File> list;
        private Fetch fetch1;

        public MyAdapter(Fetch fetch, int items, List<File> list) {
            // TODO Auto-generated constructor stub
            super();
            this.list = list;
            this.fetch1 = fetch;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int pos, View view, ViewGroup arg2) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.items, arg2, false);
            }
            TextView name = (TextView) view.findViewById(R.id.text);
            TextView dur = (TextView) view.findViewById(R.id.textView2);
            File myfile = list.get(pos);//get position


//Toast.makeText(Fetch.this, myfile.getName(),Toast.LENGTH_LONG).show();
            //System.out.println("================="+myfile.getName());
            name.setText(myfile.getName());
            dur.setText(myfile.length() + "");

            return view;
        }
    }

    }







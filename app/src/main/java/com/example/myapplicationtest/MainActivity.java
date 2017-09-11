package com.example.myapplicationtest;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE ="com.example.myapplicationtest.MESSAGE" ;
    private static final String LOG_TAG ="Empty";
    SeekBar seekBar_bottom;
    SeekBar seekBar_bright;
    SeekBar seekBar_top;
    int curSlide = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        seekBar_bottom=(SeekBar)findViewById(R.id.seekBar2);

        // perform seek bar change listener event used for getting the progress value
        seekBar_bottom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                if (progressChangedValue>=seekBar_bright.getProgress()){
                    seekBar_bright.setProgress(seekBar_bottom.getProgress());
                }
                if (progressChangedValue>=seekBar_top.getProgress()){
                    seekBar_bottom.setProgress(seekBar_top.getProgress());
                    seekBar_bright.setProgress(seekBar_top.getProgress());
                }

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Progress :" + seekbarToBrightness(seekBar_bright.getProgress()), Toast.LENGTH_SHORT).show();
            }
        });

        seekBar_bright=(SeekBar)findViewById(R.id.seekBar3);
        // perform seek bar change listener event used for getting the progress value
        seekBar_bright.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;

                //INCLUDE HERE BRIGHTNESS CHANGE
                //ADD LIMITATION TO BRIGHTNESS CHANGE ACCORDING TO SEEKBAR TOP ANB BOTTOM??


            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar_bottom.getProgress()>=progressChangedValue){
                    seekBar_bright.setProgress(seekBar_bottom.getProgress());
                }
                if (seekBar_top.getProgress()<=progressChangedValue){
                    seekBar_bright.setProgress(seekBar_top.getProgress());
                }

                Toast.makeText(MainActivity.this, "Progress :" + seekbarToBrightness(progressChangedValue), Toast.LENGTH_SHORT).show();
            }
        });

        seekBar_top=(SeekBar)findViewById(R.id.seekBar4);
        seekBar_top.setProgress(100);
        // perform seek bar change listener event used for getting the progress value
        seekBar_top.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 100;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                if (progressChangedValue<=seekBar_bright.getProgress()){
                    seekBar_bright.setProgress(seekBar_top.getProgress());
                }
                if (progressChangedValue<=seekBar_bottom.getProgress()){
                    seekBar_top.setProgress(seekBar_bottom.getProgress());
                    seekBar_bright.setProgress(seekBar_bottom.getProgress());
                }


            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Progress :" + seekbarToBrightness(seekBar_bright.getProgress()), Toast.LENGTH_SHORT).show();
            }
        });

        // WindowManager.LayoutParams layout = getWindow().getAttributes();
        // layout.screenBrightness = 1F;
        // getWindow().setAttributes(layout);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(MainActivity.this, "MEDIA_MOUNTED", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }


    private static int[] imgs = { R.drawable.images_test, R.drawable.image2};
    /** Called when the user taps the Send button */

    public double seekbarToBrightness(int progress){
        double maxBrightness=450;
        double minBrightness=1.6;

        double dPeak=(maxBrightness-minBrightness)*Math.pow( (double) progress/100,1/0.3)+minBrightness;

        // ADD HERE CONVERSION FROM BRIGHNESS IN CANDELA TO PHONE LOOKUP TABLE

        return dPeak;
    }


    public void nextImage(View view) throws IOException {
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);

        if (curSlide<imgs.length) {
            ImageView img = (ImageView) findViewById(R.id.image);
            img.setImageResource(imgs[curSlide]);
            curSlide = curSlide + 1;

            seekBar_bright.setProgress(50);
            seekBar_top.setProgress(100);
            seekBar_bottom.setProgress(0);

            //isExternalStorageWritable();

            //File sdcard = Environment.getExternalStorageDirectory();

            //File dir = new File(sdcard.getAbsolutePath() + "testing");
            //dir.mkdir();

            //String string="This is a test";

            //File file = new File(dir, "filetest.txt");
            //FileOutputStream os = new FileOutputStream(file);
            //String data = "This is the content of my file";
            //os.write(data.getBytes());
            //os.close();

            //FileOutputStream fos = new FileOutputStream(file);
            //fos.write(string.getBytes());
            //fos.flush();
            //fos.close();
            //Toast.makeText(MainActivity.this, "SAVED?", Toast.LENGTH_SHORT).show();


        }

    }



}

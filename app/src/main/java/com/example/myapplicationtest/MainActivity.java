package com.example.myapplicationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE ="com.example.myapplicationtest.MESSAGE" ;
    SeekBar seekBar_bottom;
    SeekBar seekBar_bright;
    SeekBar seekBar_top;

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
            }
        });

        seekBar_bright=(SeekBar)findViewById(R.id.seekBar3);
        // perform seek bar change listener event used for getting the progress value
        seekBar_bright.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;


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
                //Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue, Toast.LENGTH_SHORT).show();
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
            }
        });

        // WindowManager.LayoutParams layout = getWindow().getAttributes();
        // layout.screenBrightness = 1F;
        // getWindow().setAttributes(layout);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}

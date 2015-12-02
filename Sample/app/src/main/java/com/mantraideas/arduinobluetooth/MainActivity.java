package com.mantraideas.arduinobluetooth;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    BluetoothArduino mBlue;
    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        message = (TextView) findViewById(R.id.txt_message);
        findViewById(R.id.connect).setOnClickListener(this);

        findViewById(R.id.btn_forward).setOnClickListener(this);
        findViewById(R.id.btn_reverse).setOnClickListener(this);
        findViewById(R.id.btn_left).setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(this);
    }

    void showMessage(String message){
        Snackbar.make(findViewById(R.id.wrapper), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    void setup(){
        mBlue = BluetoothArduino.getInstance("ExampleRobot12");
        if(mBlue.isBluetoothEnabled()) {
            if(mBlue.Connect()){
                showMessage("Bluetooth connected");
                findViewById(R.id.connect).setVisibility(View.GONE);
                findViewById(R.id.control).setVisibility(View.VISIBLE);
            }
            else{
                showMessage("Bluetooth connection error");
            }
        }
        else{
            showMessage("Bluetooth not enabled");
        }
        //textSize(30);
    }

    public String recieve(){
        if(mBlue != null && mBlue.isBluetoothEnabled()) {
            String msg =  mBlue.getLastMessage();
            if(!msg.isEmpty()) {
                Log.d("Receiving", "Recieving data : " + msg);
                return msg;
            }
        }
        return "";
    }
    public void sendData(String data){
        if(mBlue != null){
            Log.d("Sending", "Sending data : " + data);
            mBlue.SendMessage(data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showMessage("Connecting to bluetooth");
            setup();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.connect:
                showMessage("Connecting to bluetooth");
                setup();
                break;
            case R.id.btn_forward:
                sendData("forward");

                break;
            case R.id.btn_reverse:
                sendData("reverse");
                break;
            case R.id.btn_left:
                sendData("left");
                break;
            case R.id.btn_right:
                sendData("right");
                break;
        }
        String rcv = recieve();
        if(!rcv.isEmpty())
            message.setText("Data from robot = " + recieve());
    }
}

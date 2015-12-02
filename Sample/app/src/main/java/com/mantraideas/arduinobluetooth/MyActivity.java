package com.mantraideas.arduinobluetooth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by hereshem on 11/28/15.
 */
public class MyActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        Button btn_forward = (Button) findViewById(R.id.btn_forward);

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Forward button clicked"
                        , Toast.LENGTH_SHORT).show();
            }
        });


    }
}

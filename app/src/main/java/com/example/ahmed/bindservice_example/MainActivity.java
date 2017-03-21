package com.example.ahmed.bindservice_example;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    MyService service = new MyService();

    boolean isBind =false;


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {

            MyService.MyBinder b = (MyService.MyBinder) binder;

            service = b.getService();
            isBind=true;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBind=false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this,  MyService.class);

        bindService(intent,connection,BIND_AUTO_CREATE);


        Button b1 = (Button) findViewById(R.id.button);
        final TextView t1 = (TextView) findViewById(R.id.textView);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isBind==false)
                    return;

                int x = service.getCounter();

                t1.setText(String.valueOf(x));
            }
        });

    }
}

package com.example.ahmed.bindservice_example;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ahmed on 20/03/17.
 */

public class MyService extends Service {


    MyBinder myBinder =new MyBinder();

    private int counter = 0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        new Task().execute();

        return myBinder;
    }



    public int getCounter(){

        return counter;
    }


    public class MyBinder extends Binder{

        public MyService getService(){

            return MyService.this;
        }
    }






    private class Task extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... params) {

            int x=0;
            while (x==0){

                try {

                    counter ++;

                    Thread.sleep(1000);
                }
                catch (Exception e){

                }
            }

            return null;
        }
    }
}

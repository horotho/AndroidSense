package com.sucks.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener
{

    public SensorManager sensorManager;
    public TextView xAccel, yAccel, zAccel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);

        xAccel = (TextView) this.findViewById(R.id.xAccel);
        yAccel = (TextView) this.findViewById(R.id.yAccel);
        zAccel = (TextView) this.findViewById(R.id.zAccel);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        final float aThreshold = 2;

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION)
        {

            float x = Math.abs(event.values[0]);
            float y = Math.abs(event.values[1]);
            float z = Math.abs(event.values[2]);

            xAccel.setText("X Acceleration: " + x);
            yAccel.setText("Y Acceleration: " + y);
            zAccel.setText("Z Acceleration: " + z);

            if (x < aThreshold && y < aThreshold && z < aThreshold)
            {

                return;

            }

            if (x > y && x > z)
            {


            }
            else if (y > x && y > z)
            {


            }
            else
            {


            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }
}

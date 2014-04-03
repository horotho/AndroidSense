package com.sucks.sensor;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener
{

    public SensorManager sensorManager;
    public TextView xAccel, yAccel, zAccel;
    public ImageView indicator;
    public float xHigh, xLow, yHigh, yLow, zHigh, zLow;

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

        indicator = (ImageView) this.findViewById(R.id.indicator);
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
        final float aThreshold = 3;

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION)
        {

            float x = Math.abs(event.values[0]);
            float y = Math.abs(event.values[1]);
            float z = Math.abs(event.values[2]);

            float xActual = event.values[0];
            float yActual = event.values[1];
            float zActual = event.values[2];

            if (xActual > xHigh) xHigh = xActual;
            if (xActual < xLow) xLow = xActual;

            if (yActual > yHigh) yHigh = yActual;
            if (yActual < yLow) yLow = yActual;

            if (zActual > zHigh) zHigh = zActual;
            if (zActual < zLow) zLow = zActual;

            xAccel.setText("X: " + event.values[0] + ", " + xHigh + ", " + xLow);
            yAccel.setText("Y: " + event.values[1] + ", " + yHigh + ", " + yLow);
            zAccel.setText("Z: " + event.values[2] + ", " + zHigh + ", " + zLow);

            if (x < aThreshold && y < aThreshold && z < aThreshold)
            {
                yAccel.setTextColor(Color.BLACK);
                zAccel.setTextColor(Color.BLACK);
                xAccel.setTextColor(Color.BLACK);
                indicator.setImageResource(R.drawable.ic_launcher);
            } else if (x > y && x > z)
            {
                if (event.values[0] < 0) indicator.setImageResource(R.drawable.right_arrow);
                else indicator.setImageResource(R.drawable.left_arrow);

                yAccel.setTextColor(Color.BLACK);
                zAccel.setTextColor(Color.BLACK);
                xAccel.setTextColor(Color.RED);
            }
            else if (y > x && y > z)
            {
                if (event.values[1] < 0) indicator.setImageResource(R.drawable.up_arrow);
                else indicator.setImageResource(R.drawable.down_arrow);

                xAccel.setTextColor(Color.BLACK);
                zAccel.setTextColor(Color.BLACK);
                yAccel.setTextColor(Color.RED);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }
}

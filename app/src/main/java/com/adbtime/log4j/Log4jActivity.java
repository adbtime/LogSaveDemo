package com.adbtime.log4j;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.adbtime.logsavedemo.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jActivity extends AppCompatActivity {

    Logger logger = null;

    private static final String TAG = Log4jActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log4j);
        logger = Log4jApplication.logger;
        logout();
    }

    private void logout() {

        for (int i = 0; i < 10; i++) {

            logger.debug("debug message. Details: {}", "debug 输出"+i);
            logger.info("info message. Details: {}", "debug 输出"+i);
            logger.error("error message. Details: {}", "debug 输出"+i);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log4j, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

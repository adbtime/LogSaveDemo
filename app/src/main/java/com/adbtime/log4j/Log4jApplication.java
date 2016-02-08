package com.adbtime.log4j;

import android.app.Application;
import android.os.Environment;

import java.io.File;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by sunhao on 2016/2/3.
 */
public class Log4jApplication extends Application{


    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Log4jApplication.class);
    //public final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MainActivity.class);

    @Override
    public void onCreate() {
        super.onCreate();

        configLog4j("demo");
        Logger log = Logger.getLogger(Log4jApplication.class);//org.slf4j.Logger
        log.info("My Application Created");


    }

    /**
     * config the log4j
     * @param logFileNamePrefix
     */

    private void configLog4j(String logFileNamePrefix) {
        LogConfigurator logConfigurator = new LogConfigurator();
        //set the log file name and pleace what you like
        logConfigurator.setFileName(Environment.getExternalStorageDirectory()
                + File.separator + "adbtime" + File.separator + "logs"
                + File.separator + (logFileNamePrefix == null ? "" : logFileNamePrefix) + "log4j.txt");
        //set the lowest log level to print
        logConfigurator.setRootLevel(Level.DEBUG);//
        //a flag demo, you can use some else tag instead of "org.apache"
        logConfigurator.setLevel("org.apache", Level.ERROR);
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        logConfigurator.setMaxFileSize(1024 * 1024 * 5);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();
    }

}

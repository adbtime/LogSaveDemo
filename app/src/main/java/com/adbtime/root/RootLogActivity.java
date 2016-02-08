package com.adbtime.root;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.adbtime.logsavedemo.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
 * Created by sunhao on 2016/2/3.
 */
public class RootLogActivity extends Activity{
    private boolean isRun = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isRun = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Log.e("", "Run");
//    haveRoot();
                execRootCmd("logcat -f /mnt/sdcard/t.txt");
            }
        }).start();
    }


    // exec command and output the results
    protected static Vector execRootCmd(String paramString) {
        Vector localVector = new Vector();
        try {
            // the app should have root permission
            Process localProcess = Runtime.getRuntime().exec("su");
            OutputStream localOutputStream = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream(
                    localOutputStream);
            InputStream localInputStream = localProcess.getInputStream();
            DataInputStream localDataInputStream = new DataInputStream(
                    localInputStream);
            String str1 = String.valueOf(paramString);
            String str2 = str1 + "\n";
            localDataOutputStream.writeBytes(str2);//write command and execute
            localDataOutputStream.flush();
            String str3 = localDataInputStream.readLine();
            localVector.add(str3);
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localProcess.waitFor();
            return localVector;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return localVector;
    }


    //determine whether there is root permission for the app
    protected static boolean haveRoot() {
        //by performing a test order
        int i = execRootCmdSilent("echo hello");
        if (i != -1)
            return true;
        return false;
    }

    // exec the command but do not focus on results output
    protected static int execRootCmdSilent(String paramString) {
        try {
            Process localProcess = Runtime.getRuntime().exec("su");
            Object localObject = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream(
                    (OutputStream) localObject);
            String str = String.valueOf(paramString);
            localObject = str + "\n";
            localDataOutputStream.writeBytes((String) localObject);
            localDataOutputStream.flush();
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localProcess.waitFor();
            localObject = localProcess.exitValue();
            return (Integer) localObject;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return 0;
    }

}

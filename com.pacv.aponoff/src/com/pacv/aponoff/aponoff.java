package com.pacv.aponoff;
// v1a
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.content.*;
import android.net.wifi.*;
import java.lang.reflect.*;

public class aponoff extends Activity {
    
    public aponoff(){
    	super();
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        configApState(this);
        
        finish();
        //System.exit(0);
    }
    
public static boolean isApOn(Context context){
    WifiManager wifimanager= (WifiManager)context.getSystemService(context.WIFI_SERVICE);       
    try {
        Method method = wifimanager.getClass().getDeclaredMethod("isWifiApEnabled");
        method.setAccessible(true);
        return (Boolean) method.invoke(wifimanager);
    }
    catch (Throwable ignored){}
    return false;
}

//turn on/off wifi hotspot as toggle
public static boolean configApState(Context context){
    WifiManager wifimanager= (WifiManager)context.getSystemService(context.WIFI_SERVICE);
    WifiConfiguration wificonfiguration = null;
    try {  
        if(isApOn(context)){
            //turn off whether wifi is on
            wifimanager.setWifiEnabled(false);
        }               
        Method method=wifimanager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);                 
        method.invoke(wifimanager, wificonfiguration, !isApOn(context));
        return true;
    } 
    catch (NoSuchMethodException e) 
    {
        e.printStackTrace();
    } 
    catch (IllegalArgumentException e) 
    {
        e.printStackTrace();
    } 
    catch (IllegalAccessException e) 
    {
        e.printStackTrace();
    } 
    catch (InvocationTargetException e) 
    {
        e.printStackTrace();
    }
    return false;
  }


}

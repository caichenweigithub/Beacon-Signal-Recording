package com.example.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.io.File;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Vector;


import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

//import com.THLight.USBeacon.App.Lib.USBeaconConnection;
//import com.THLight.USBeacon.App.Lib.USBeaconData;
//import com.THLight.USBeacon.App.Lib.USBeaconList;
//import com.THLight.USBeacon.App.Lib.USBeaconServerInfo;
import com.THLight.USBeacon.App.Lib.iBeaconData;
import com.THLight.USBeacon.App.Lib.iBeaconScanManager;
//import com.THLight.USBeacon.Sample.R;
import com.THLight.USBeacon.Sample.ScanediBeacon;
//import com.THLight.USBeacon.Sample.THLApp;
//import com.THLight.USBeacon.Sample.THLConfig;
//import com.THLight.USBeacon.Sample.ui.ListItem;
//import com.THLight.USBeacon.Sample.ui.UIMain;
//import com.THLight.Util.THLLog;



public class MainActivity extends ActionBarActivity implements iBeaconScanManager.OniBeaconScan{


	final int MSG_SCAN_IBEACON			= 1000;
	final int MSG_UPDATE_BEACON_LIST	= 1001;
	final int TIME_BEACON_TIMEOUT		= 10000;
	final int REQ_ENABLE_BT		= 2000;
	static int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0,
			count9 = 0, count10 = 0, count11 = 0, count12 = 0, count13 = 0, count14 = 0, count15 = 0, count16 = 0;
	static int old_count = 0;

	iBeaconScanManager miScaner	= null;

	BluetoothAdapter mBLEAdapter= BluetoothAdapter.getDefaultAdapter();
	List<ScanediBeacon> miBeacons	= new ArrayList<ScanediBeacon>();

	TextView text1, text2, text3, text4, text5;
    TextView minor1, minor2, minor3, minor4, minor5, minor6, minor7, minor8,
			minor9, minor10, minor11, minor12, minor13, minor14, minor15, minor16;

	ImageView Img2, Img3, Img4, Img5, Img6;

    Button btn1;

    ImageView []Img;

    String []chk;
    String storage1 = "", storage2 = "", storage3 = "", storage4 = "", storage5 = "", storage6 = "", storage7 = "", storage8 = "",
			storage9 = "", storage10 = "", storage11 = "", storage12 = "", storage13 = "", storage14 = "", storage15 = "", storage16 = "";


	Vector test_ID = new Vector();
	Vector test_major = new Vector();
	Vector test_minor = new Vector();
	Vector test_rssi = new Vector();


	int maxi=0;
	int max=0,number=0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text1	= (TextView)findViewById(R.id.it3_text1);
	    text2	= (TextView)findViewById(R.id.it3_text2);
	    text3	= (TextView)findViewById(R.id.it3_text3);
	    text4	= (TextView)findViewById(R.id.it3_text4);
        minor1	= (TextView)findViewById(R.id.minor1);
        minor2	= (TextView)findViewById(R.id.minor2);
        minor3	= (TextView)findViewById(R.id.minor3);
        minor4	= (TextView)findViewById(R.id.minor4);
        minor5	= (TextView)findViewById(R.id.minor5);
        minor6	= (TextView)findViewById(R.id.minor6);
        minor7	= (TextView)findViewById(R.id.minor7);
        minor8	= (TextView)findViewById(R.id.minor8);
        minor9	= (TextView)findViewById(R.id.minor9);
        minor10	= (TextView)findViewById(R.id.minor10);
        minor11	= (TextView)findViewById(R.id.minor11);
        minor12	= (TextView)findViewById(R.id.minor12);
        minor13	= (TextView)findViewById(R.id.minor13);
        minor14	= (TextView)findViewById(R.id.minor14);
        minor15	= (TextView)findViewById(R.id.minor15);
        minor16	= (TextView)findViewById(R.id.minor16);

	    text5 =(TextView)findViewById(R.id.textView1);

	    //chk= new String[] {"490F774C","731F774C","C416774C","001F774C","F516774C"};


	    Img2 = (ImageView)findViewById(R.id.imageView2);
	    Img3 = (ImageView)findViewById(R.id.imageView3);
	    Img4 = (ImageView)findViewById(R.id.imageView4);
	    Img5 = (ImageView)findViewById(R.id.imageView5);
	    Img6 = (ImageView)findViewById(R.id.imageView6);
	    Img = new ImageView[]{Img2,Img3,Img4,Img5,Img6};

		for(int i=0;i<=4;i++){
			Img[i].setVisibility(View.INVISIBLE);
		}

        btn1 = (Button)findViewById(R.id.store);
        btn1.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {

                boolean hasExternalStorage = isExternalStorageWritable();
                if(hasExternalStorage)
                {
                    // 目前日期
                    String dateformat = "yyyyMMdd kk_mm_ss";
                    SimpleDateFormat df = new SimpleDateFormat(dateformat);
                    String filename1 = "1_" + df.format(new Date())+".txt";
					String filename2 = "2_" + df.format(new Date())+".txt";
					String filename3 = "3_" + df.format(new Date())+".txt";
					String filename4 = "4_" + df.format(new Date())+".txt";
					String filename5 = "5_" + df.format(new Date())+".txt";
					String filename6 = "6_" + df.format(new Date())+".txt";
					String filename7 = "7_" + df.format(new Date())+".txt";
					String filename8 = "8_" + df.format(new Date())+".txt";
					String filename9 = "9_" + df.format(new Date())+".txt";
					String filename10 = "10_" + df.format(new Date())+".txt";
					String filename11 = "11_" + df.format(new Date())+".txt";
					String filename12 = "12_" + df.format(new Date())+".txt";
					String filename13 = "13_" + df.format(new Date())+".txt";
					String filename14 = "14_" + df.format(new Date())+".txt";
					String filename15 = "15_" + df.format(new Date())+".txt";
					String filename16 = "16_" + df.format(new Date())+".txt";
					File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File file1 = new File(path, filename1);
					File file2 = new File(path, filename2);
					File file3 = new File(path, filename3);
					File file4 = new File(path, filename4);
					File file5 = new File(path, filename5);
					File file6 = new File(path, filename6);
					File file7 = new File(path, filename7);
					File file8 = new File(path, filename8);
					File file9 = new File(path, filename9);
					File file10 = new File(path, filename10);
					File file11 = new File(path, filename11);
					File file12 = new File(path, filename12);
					File file13 = new File(path, filename13);
					File file14 = new File(path, filename14);
					File file15 = new File(path, filename15);
					File file16 = new File(path, filename16);
					try
                    {
                        path.mkdirs();
                        OutputStream os1 = new FileOutputStream(file1, true);    // 第二個參數為是否 append
						OutputStream os2 = new FileOutputStream(file2, true);
						OutputStream os3 = new FileOutputStream(file3, true);
						OutputStream os4 = new FileOutputStream(file4, true);
						OutputStream os5 = new FileOutputStream(file5, true);    // 第二個參數為是否 append
						OutputStream os6 = new FileOutputStream(file6, true);
						OutputStream os7 = new FileOutputStream(file7, true);
						OutputStream os8 = new FileOutputStream(file8, true);
						OutputStream os9 = new FileOutputStream(file9, true);    // 第二個參數為是否 append
						OutputStream os10 = new FileOutputStream(file10, true);
						OutputStream os11 = new FileOutputStream(file11, true);
						OutputStream os12 = new FileOutputStream(file12, true);
						OutputStream os13 = new FileOutputStream(file13, true);    // 第二個參數為是否 append
						OutputStream os14 = new FileOutputStream(file14, true);
						OutputStream os15 = new FileOutputStream(file15, true);
						OutputStream os16 = new FileOutputStream(file16, true);
                        // 若為 true，則新加入的文字會接續寫在文字檔的最後
                        dateformat = "kk:mm:ss";
                        df.applyPattern(dateformat);
                        String string1 = storage1 + "\n\n";
						String string2 = storage2 + "\n\n";
						String string3 = storage3 + "\n\n";
						String string4 = storage4 + "\n\n";
						String string5 = storage5 + "\n\n";
						String string6 = storage6 + "\n\n";
						String string7 = storage7 + "\n\n";
						String string8 = storage8 + "\n\n";
						String string9 = storage9 + "\n\n";
						String string10 = storage10 + "\n\n";
						String string11 = storage11 + "\n\n";
						String string12 = storage12 + "\n\n";
						String string13 = storage13 + "\n\n";
						String string14 = storage14 + "\n\n";
						String string15 = storage15 + "\n\n";
						String string16 = storage16 + "\n\n";
						os1.write(string1.getBytes());
						os2.write(string2.getBytes());
						os3.write(string3.getBytes());
						os4.write(string4.getBytes());
						os5.write(string5.getBytes());
						os6.write(string6.getBytes());
						os7.write(string7.getBytes());
						os8.write(string8.getBytes());
						os9.write(string9.getBytes());
						os10.write(string10.getBytes());
						os11.write(string11.getBytes());
						os12.write(string12.getBytes());
						os13.write(string13.getBytes());
						os14.write(string14.getBytes());
						os15.write(string15.getBytes());
						os16.write(string16.getBytes());
						os1.close();
						os2.close();
						os3.close();
						os4.close();
						os5.close();
						os6.close();
						os7.close();
						os8.close();
						os9.close();
						os10.close();
						os11.close();
						os12.close();
						os13.close();
						os14.close();
						os15.close();
						os16.close();

                        Toast.makeText(MainActivity.this,"Storage",Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e)
                    {
                        // Unable to create file, likely because external storage is
                        // not currently mounted.
                        Log.w("ExternalStorage", "Error writing " + file1, e);
						Log.w("ExternalStorage", "Error writing " + file2, e);
						Log.w("ExternalStorage", "Error writing " + file3, e);
						Log.w("ExternalStorage", "Error writing " + file4, e);
                    }
                }
                else
                    Toast.makeText(MainActivity.this,"no Storage",Toast.LENGTH_SHORT).show();
            }
        });

		if(!mBLEAdapter.isEnabled())
		{
			Intent intent= new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(intent, REQ_ENABLE_BT);
		}
		else
		{
			miScaner = new iBeaconScanManager(this, this);
			Message msg= Message.obtain(mHandler, MSG_SCAN_IBEACON, 1000, 1100);
			msg.sendToTarget();
		}
		mHandler.sendEmptyMessageDelayed(MSG_UPDATE_BEACON_LIST, 500);
	}

//                                              Handler Code
/*=================================================================================================*/
private Handler mHandler= new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case MSG_SCAN_IBEACON:
					synchronized(this)
					{
						int timeForScaning		= msg.arg1;
						int nextTimeStartScan	= msg.arg2;
						miScaner.startScaniBeacon(timeForScaning);
						this.sendMessageDelayed(Message.obtain(msg), nextTimeStartScan);
					}
					break;
				case MSG_UPDATE_BEACON_LIST:
					{
						verifyiBeacons();
						if(count1 == old_count){
							scan();
							Message ms= Message.obtain(mHandler, MSG_SCAN_IBEACON, 1000, 1100);
							ms.sendToTarget();
							mHandler.sendEmptyMessageDelayed(MSG_UPDATE_BEACON_LIST,1000);
						}else{
							mHandler.sendEmptyMessageDelayed(MSG_UPDATE_BEACON_LIST,1000);
						}


					}
					break;
			}
			
			
			
			 /*for(int i=0;i<=4;i++){
				if (text1.getText().equals(chk[i].toString()))
					Img[i].setVisibility(View.VISIBLE);
				else
					Img[i].setVisibility(View.INVISIBLE);
			}*/

		}
	};
/*=================================================================================================*/


	public void scan(){
		miScaner = new iBeaconScanManager(this, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onScaned(iBeaconData iBeacon) {
		// TODO Auto-generated method stub
		synchronized(this)
		{
		addOrUpdateiBeacon(iBeacon);
		}
	}



	public void addOrUpdateiBeacon(iBeaconData iBeacon)
	{
		long currTime= System.currentTimeMillis();

		ScanediBeacon beacon= null;

		for(ScanediBeacon b : miBeacons)
		{
			if(b.equals(iBeacon, false))
			{
                   beacon = b;
				break;
            }
		}

		if(null == beacon)
		{
            beacon = ScanediBeacon.copyOf(iBeacon);
			miBeacons.add(beacon);
		}
		else
		{
            beacon.rssi= iBeacon.rssi;
		}
            beacon.lastUpdate = currTime;
	}

	public void verifyiBeacons()
	{
		{
			long currTime	= System.currentTimeMillis();
			int len= miBeacons.size();
			ScanediBeacon beacon= null;

			for(int i= len- 1; 0 <= i; i--)
			{
				beacon= miBeacons.get(i);

				if(null != beacon && TIME_BEACON_TIMEOUT < (currTime- beacon.lastUpdate))
				{
					miBeacons.remove(i);
				}
			}
		}

		{
			test_rssi.clear();
            test_ID.clear();
            test_minor.clear();
            test_major.clear();
            number=0;
            max=-200;

			for(ScanediBeacon beacon : miBeacons)
			{
                if(beacon.minor == 2) {

                    test_ID.add(beacon.beaconUuid.toString().toUpperCase());
                    test_major.add(beacon.major);
                    test_minor.add(beacon.minor);
                    test_rssi.add(beacon.rssi);
                    if (beacon.rssi != max) {
                        max = beacon.rssi;
                        maxi = number;
                    }
                    storage1 = storage1 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage1);
                    number++;
                    count1++;
                    minor1.setText(beacon.minor+" OK    ");
                }else if(beacon.minor == 6){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage2 = storage2 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage2);
					number++;
					count2++;
                    minor2.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 7){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage3 = storage3 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage3);
					number++;
					count3++;
                    minor3.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 8){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage4 = storage4 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage4);
					number++;
					count4++;
					minor4.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 9){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage5 = storage5 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage5);
					number++;
					count5++;
					minor5.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 10){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage6 = storage6 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage6);
					number++;
					count6++;
					minor6.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 12){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage7 = storage7 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage7);
					number++;
					count7++;
					minor7.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 13){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage8 = storage8 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage8);
					number++;
					count8++;
					minor8.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 14){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage9 = storage9 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage9);
					number++;
					count9++;
					minor9.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 15){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage10 = storage10 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage10);
					number++;
					count10++;
					minor10.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 16){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage11 = storage11 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage11);
					number++;
					count11++;
					minor11.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 17){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage12 = storage12 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage12);
					number++;
					count12++;
					minor12.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 18){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage13 = storage13 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage13);
					number++;
					count13++;
					minor13.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 19){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage14 = storage14 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage14);
					number++;
					count14++;
                    minor14.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 20){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage15 = storage15 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage15);
					number++;
					count15++;
					minor15.setText(beacon.minor+" OK    ");
				}else if(beacon.minor == 21){
					test_ID.add(beacon.beaconUuid.toString().toUpperCase());
					test_major.add(beacon.major);
					test_minor.add(beacon.minor);
					test_rssi.add(beacon.rssi);
					if (beacon.rssi != max) {
						max = beacon.rssi;
						maxi = number;
					}
					storage16 = storage16 + " " + beacon.rssi;
					Log.e("multi beacon",beacon.minor+" "+storage16);
					number++;
					count16++;
					minor16.setText(beacon.minor+" OK    ");
				}
			}
				//text1.setText(count);
				text2.setText(test_major.elementAt(maxi).toString());
				text3.setText(test_minor.elementAt(maxi).toString());
				text4.setText(test_rssi.elementAt(maxi).toString() + " , count1: " + count1);

		}
	}

    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }

}

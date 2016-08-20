package net.tutorial.ndkintro;

import ndk.NDK_Methods;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.returnaddr.ndktest.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends Activity 
{
	NDK_Methods ndk_lib;	
	
	static 
	{
		System.loadLibrary("native_lib");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RunMyModule();
	}

	private void RunMyModule()
	{
		TextView messageTextView = (TextView)findViewById(R.id.tvNDKmessage);

		///////////////////////////////////////////////////////////
		// command execution with root privileges
		///////////////////////////////////////////////////////////
		Process process = null;
		Runtime runtime = Runtime.getRuntime();
		try
		{
			String[] cmds = new String[3];
			cmds[0] = "su";
			cmds[1] = "-c";
			//cmds[2] = "/system/bin/id";
			cmds[2] = "/system/xbin/whoami hello_xcuter";

			process = runtime.exec(cmds);
			process.waitFor();

			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuffer strbuf = new StringBuffer();
			int buflen = 512;
			char[] buf = new char[buflen];
			int len;

			do {
				len = bufferedreader.read(buf);
				Log.d("xcuter", "read bytes : "+len);
				strbuf.append(buf, 0, len);
			} while(len == buflen);

			messageTextView.setText( strbuf );

		} catch (final Exception e)
		{
			Log.d("xcuter", "Exception while trying to run..");
			process = null;
		}


		/*
		///////////////////////////////////////////////////////////
		// get executed results that declared in .so
		///////////////////////////////////////////////////////////
		String ndkMessage = NDK_Methods.SayHelo();
		android.widget.Toast.makeText(this, ndkMessage, 1).show();
		
		if(ndkMessage != null)
		{
			TextView messageTextView = (TextView)findViewById(R.id.tvNDKmessage);
			messageTextView.setText(ndkMessage);
		}
		*/
	}


}


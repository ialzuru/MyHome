package com.myhome;

import java.io.IOException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.myhome.movementsensorendpoint.Movementsensorendpoint;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

//import com.myhome.LoginActivity.EndpointsTask;
import com.myhome.lightendpoint.Lightendpoint;
import com.myhome.lightendpoint.model.Light;

public class LightsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lights);
		/* KITCHEN LIGHTS ON */
		
        final Button onbutton = (Button) findViewById(R.id.radioButton3);
        onbutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	new EndpointsTask().execute(getApplicationContext());
            }

        });
        /* KITCHEN LIGHTS OFF */
        final Button offbutton = (Button) findViewById(R.id.radioButton4);
        offbutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
              
            }

        });
        
        final Button homepage = (Button) findViewById(R.id.lightshomepage);
        homepage.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent intentobj = new Intent (LightsActivity.this, SuperviseActivity.class);
                LightsActivity.this.startActivity(intentobj);
            }

        });
        
        
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lights, menu);
		return true;
	}

	

	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		protected Long doInBackground(Context... contexts) {
			Lightendpoint.Builder endpointBuilder = new Lightendpoint.Builder(
			AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			new HttpRequestInitializer() {
				public void initialize(HttpRequest httpRequest) {
				}
			});
			Lightendpoint endpoint = CloudEndpointUtils.updateBuilder(
				endpointBuilder).build();
			
			try {
				Light light = new Light();
				light = endpoint.getLight("0").execute();
				if (light == null) {
					System.out.println("No Action ");
				} else {
					light.setState(1);
					Light result = endpoint.updateLight(light).execute();
				}
				
				/*
				Light ms1 = new Light();
				ms1.setIdname("1");
				ms1.setHomeID(1);
				ms1.setId(1);
				ms1.setLocation("BedRoom");
				ms1.setState(1);
				ms1.setUpdateTime("2014-04-17 10:10:10");
				Light result1 = endpoint.insertLight(ms1).execute();*/ 
			} catch (IOException e) {
				System.out.println("After Update v3 ");
				e.printStackTrace();
			}
			return (long) 0;
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_lights,
					container, false);
			return rootView;
		}
	}

}

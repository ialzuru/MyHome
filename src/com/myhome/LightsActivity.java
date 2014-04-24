package com.myhome;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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
import android.widget.RadioButton;





//import com.myhome.LoginActivity.EndpointsTask;
import com.myhome.lightendpoint.Lightendpoint;
import com.myhome.lightendpoint.model.Light;

public class LightsActivity extends Activity {
	Lightendpoint epBedroom, epKitchen;
	Light lightBedroom, lightKitchen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lights);
		/* BEDROOM LIGHTS ON */
		
        final Button onbutton = (Button) findViewById(R.id.radioButton3);
        onbutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	//((RadioButton) v).setChecked(true);
            	//new EndpointsTask().execute(getApplicationContext());
            }

        });
        /* BEDROOM LIGHTS OFF */
        final Button offbutton = (Button) findViewById(R.id.radioButton4);
        offbutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
    	    	//Intent intentobj = new Intent (LightsActivity.this, SuperviseActivity.class);
    	        //LightsActivity.this.startActivity(intentobj);
            }

        });
        
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}


	public class Values {
		public int[] v;
		
		Values(int n) {
			v = new int[n];
			for(int i=0; i<n; i++)
				v[i] = 0;
		}
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	    if(hasFocus){
	    	Values lightValues = new Values(2);
	    	try {
				new EndpointsTask(lightValues).execute(getApplicationContext()).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	if (lightValues.v[0] == 1)
	    		((RadioButton) findViewById(R.id.radioButton1)).setChecked(true);
	    	else
	    		((RadioButton) findViewById(R.id.radioButton2)).setChecked(true);
	    	if (lightValues.v[1] == 1)
	    		((RadioButton) findViewById(R.id.radioButton3)).setChecked(true);
	    	else
	    		((RadioButton) findViewById(R.id.radioButton4)).setChecked(true);	    	
	    }   
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lights, menu);
		return true;
	}

	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		Values v;
		
		EndpointsTask(Values auxV) {
			v = auxV;
		}
		
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
		    	System.out.println("Begin try");
				
		    	lightBedroom = new Light();
				lightBedroom = endpoint.getLight("1").execute();
				System.out.println("State for bedroom: " + lightBedroom.getState() );
				if (lightBedroom == null) {
					System.out.println("No Action ");
				} else {
					v.v[0] = lightBedroom.getState();
				}
				
				lightKitchen = new Light();
				lightKitchen = endpoint.getLight("0").execute();
				System.out.println("State for Kitchen: " + lightKitchen.getState() );
				if (lightKitchen == null) {
					System.out.println("No Action ");
				} else {
					v.v[1] = lightKitchen.getState();
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

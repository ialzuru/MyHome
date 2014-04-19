package com.myhome;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

import java.io.IOException;
import java.util.Date;

import android.os.AsyncTask;
import android.content.Context;

import com.myhome.actionendpoint.Actionendpoint;
import com.myhome.actionendpoint.model.Action;
import com.myhome.movementsensorendpoint.Movementsensorendpoint;
import com.myhome.movementsensorendpoint.model.MovementSensor;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final EditText inputname = (EditText) findViewById(R.id.editText2);
		EditText inputpassword = (EditText) findViewById(R.id.editText3);
		final Button lbutton = (Button) findViewById(R.id.button1);

		lbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String check = inputname.getText().toString();
				if (check.equals("Akchay")) {
					Intent intentobj = new Intent(LoginActivity.this,
							SuperviseActivity.class);
					LoginActivity.this.startActivity(intentobj);
				}
			}

		});

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//new EndpointsTask().execute(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/* public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		protected Long doInBackground(Context... contexts) {

			Actionendpoint.Builder endpointBuilder = new Actionendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {
						}
					});
			Actionendpoint endpoint = CloudEndpointUtils.updateBuilder(
					endpointBuilder).build();
			
			/*Movementsensorendpoint.Builder endpointBuilder = new Movementsensorendpoint.Builder(
			AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			new HttpRequestInitializer() {
				public void initialize(HttpRequest httpRequest) {
				}
			});
			Movementsensorendpoint endpoint = CloudEndpointUtils.updateBuilder(
				endpointBuilder).build();
			*/
			//try {
				/*Action action = new Action();
				String noteID = "123445";
				//String noteID = "id=5738600293466112";
				action.setId(noteID);
				action.setActionType("Update");
				action.setHomeID(2);
				action.setSensorID(2);
				action.setSensorType("UPdate");
				action.setState(2);
				System.out.println("BEfore Update v3 ");*/
				// action.put();
				//Action result = endpoint.insertAction(action).execute();    //             insertAction(action).execute();
				//System.out.println("After Update: " + result);
				
				/*System.out.println("Before update v4");
				Action action = new Action();
				action = endpoint.getAction("1234").execute();
				if (action == null) {
					System.out.println("No Action ");
				} else {
					System.out.println("Action Type = " + action.getActionType());
					action.setActionType("Update2");
					Action result = endpoint.updateAction(action).execute();
				}*/

				/*MovementSensor ms1 = new MovementSensor();
				ms1.setIdname("0");
				ms1.setHomeID(1);
				ms1.setLocation("Movement");
				ms1.setLastMovement("2014-04-17 10:10:10");
				ms1.setUpdateTime("2014-04-17 10:10:10");
				MovementSensor result1 = endpoint.insertMovementSensor(ms1).execute();*/ 
		/*	} catch (IOException e) {
				System.out.println("After Update v3 ");
				e.printStackTrace();
			}
			return (long) 0;
		}
	} */

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
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}

}

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
		final EditText inputpassword = (EditText) findViewById(R.id.editText3);
		final Button lbutton = (Button) findViewById(R.id.button1);

		lbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String checkName = inputname.getText().toString();
				String checkPwd = inputpassword.getText().toString();
				if ((checkName.equals("admin"))&&((checkPwd.equals("admin")))) {
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

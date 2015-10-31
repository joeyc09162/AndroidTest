package com.example.jsontest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());

		HttpPost httpRequest = new HttpPost(
				"http://140.124.9.14/HealthInfo/SQLquery.php");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("qtype", "Questionnaire"));
		params.add(new BasicNameValuePair("fields", "*"));
		params.add(new BasicNameValuePair("options", "qID=" + 7));
		String JsonResult = null;

		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				JsonResult = EntityUtils.toString(httpResponse.getEntity());
			}

			JSONArray arr = new JSONArray(JsonResult);
			Log.d("count", "" + arr.length());
			for (int i = 0; i < arr.length(); i++) {
				Log.d("Test", ""+arr.get(i).toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

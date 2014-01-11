package aweber.smila;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MonitoringTask extends AsyncTask<Void, Void, String> {
	
	private Activity _activity;
	
	public MonitoringTask(Activity activity) {
		_activity = activity;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet("http://192.168.178.22:8080/smila/jobmanager/jobs/");
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);
			HttpEntity entity = response.getEntity();
			text = getASCIIContentFromEntity(entity);
		} catch (Exception e) {
			Log.e("doInBackground()", e.getMessage());
			return e.getLocalizedMessage();
		}
		return text;
	}

	@Override
	protected void onPostExecute(String results) {
		if (results != null) {
			showJobDetails(results);
		}
	}

	private void showJobDetails(String jsonString) {
		EditText et = (EditText) _activity.findViewById(R.id.my_edit);
		String s = "jobs";
		List<String> jobNames = new ArrayList<String>();
		try {
			JSONObject jobs = new JSONObject(jsonString);
			JSONArray jobsArray = jobs.getJSONArray("jobs");
			for (int i = 0; i < jobsArray.length(); i++) {
				JSONObject job = jobsArray.getJSONObject(i);
				jobNames.add(job.getString("name"));
				if (job.has("latestJobRun")) {
					JSONObject latest = job.getJSONObject("latestJobRun");
					String name = job.getString("name");
					String state = latest.getString("state");
					s = s + "\n\t" + name + ":\t" + state;
					int wfS = latest.getInt("successfulWorkflowRunCount");
					int wfF = latest.getInt("failedWorkflowRunCount");
					s = s + "\n\t\t wf run:\t\t s:" + wfS + "\t\t f:" + wfF;
					int tS = latest.getInt("successfulTaskCount");
					int tF = latest.getInt("failedWithoutRetryTaskCount");
					int tFR = latest.getInt("failedAfterRetryTaskCount");
					s = s + "\n\t\t tasks:\t\t s:" + tS + "\t\t f:" + (tF + tFR);
				}
			}
			fillJobSpinner(jobNames);
			et.setText(s);

		} catch (JSONException e) {
			Log.e("showJobDetails()", jsonString);
			et.setText("Error procesing JSON: " + e.getMessage());
		}
	}
	
	private void fillJobSpinner(List<String> jobList) {
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(_activity,
			android.R.layout.simple_spinner_item, jobList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner jobSpinner = (Spinner) _activity.findViewById(R.id.jobSpinner);
		jobSpinner.setAdapter(dataAdapter);
	}
	
	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}

}
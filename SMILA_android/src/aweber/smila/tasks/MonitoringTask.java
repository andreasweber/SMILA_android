package aweber.smila.tasks;

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
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import aweber.smila.R;
import aweber.smila.R.id;
import aweber.smila.R.layout;

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
			showTaskDetails(results);
		}
	}

	private void showJobDetails(String jsonString) {
		TextView jobDetails = (TextView) _activity.findViewById(R.id.job_details_textview);
	
		List<String> jobNames = new ArrayList<String>();
		
		SpannableStringBuilder sb = new SpannableStringBuilder(" Jobs");
		ForegroundColorSpan fcs = new ForegroundColorSpan(Color.GREEN);
		
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
					// workflow runs
					int wfS = latest.getInt("successfulWorkflowRunCount");
					int wfF = latest.getInt("failedWorkflowRunCount");
					// tasks
					int tS = latest.getInt("successfulTaskCount");
					int tF = latest.getInt("failedWithoutRetryTaskCount");
					int tFR = latest.getInt("failedAfterRetryTaskCount");
					
					sb.append("\n\t" + name);
					sb.append("\t" + state);
					sb.append(Html.fromHtml("<font color='green'>\t" + wfS + "/" + tS + "</font>"));
					sb.append(Html.fromHtml("  <font color='red'>" + wfF + "/" + (tF + tFR) + "</font>"));
				}
			}
			fillJobSpinner(jobNames);
			jobDetails.setText(sb, TextView.BufferType.SPANNABLE);

		} catch (JSONException e) {
			Log.e("showJobDetails()", jsonString);
			jobDetails.setText("Error procesing JSON: " + e.getMessage());
		}
	}
	
	private void showTaskDetails(String jsonString) {
		TextView taskDetails = (TextView) _activity.findViewById(R.id.task_details_textview);
		taskDetails.setText(" Tasks");
	}
	
	private void fillJobSpinner(List<String> jobList) {
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(_activity,
			R.layout.job_spinner_item, jobList);
		dataAdapter.setDropDownViewResource(R.layout.job_spinner_item);
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
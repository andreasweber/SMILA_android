package aweber.smila.tasks;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import aweber.smila.R;
import aweber.smila.R.id;

public class StartTask extends AsyncTask<Void, Void, String> {

	private Activity _activity;

	public StartTask(Activity activity) {
		_activity = activity;
	}

	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost("http://192.168.178.22:8080/smila/jobmanager/jobs/indexUpdate");
		String jobRunId = null;
		try {
			HttpResponse response = httpClient.execute(httpPost, localContext);
			HttpEntity entity = response.getEntity();
			String jsonResult = getASCIIContentFromEntity(entity);
			JSONObject jobStarted = new JSONObject(jsonResult);
			if (jobStarted.has("jobId")) {
				jobRunId = jobStarted.getString("jobId");
				Log.i("START jobRunId: ", jobRunId);
			}
			Log.i("START", jsonResult);
		} catch (Exception e) {
			Log.e("doInBackground()", e.getMessage());
			return e.getLocalizedMessage();
		}
		return jobRunId;
	}

	@Override
	protected void onPostExecute(String results) {
		Button b = (Button) _activity.findViewById(R.id.start_button);
		b.setClickable(true);
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
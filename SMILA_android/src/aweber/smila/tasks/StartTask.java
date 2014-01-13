package aweber.smila.tasks;

import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import aweber.smila.R;

public class StartTask extends AbstractTask {

	public StartTask(Activity activity) {
		super(activity);
	}

	@Override
	protected String doInBackground(Void... params) {
		String jobRunId = null;
		try {
			String jsonResult = executePOST("jobmanager/jobs/indexUpdate");
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

}
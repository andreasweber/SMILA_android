package aweber.smila.tasks;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import aweber.smila.R;

public class StopTask extends AbstractTask {

	private String _jobRunId;

	public StopTask(Activity activity, String jobRunId) {
		super(activity);
		_jobRunId = jobRunId;
	}

	@Override
	protected String doInBackground(Void... params) {
		String jsonResult = null;
		try {
			jsonResult = executePOST("jobmanager/jobs/indexUpdate/" + _jobRunId + "/finish");
			Log.i("STOP", jsonResult);
		} catch (Exception e) {
			Log.e("doInBackground()", e.getMessage());
			return e.getLocalizedMessage();
		}
		return jsonResult;
	}

	@Override
	protected void onPostExecute(String results) {
		Button b = (Button) _activity.findViewById(R.id.stop_button);
		b.setClickable(true);
	}

}
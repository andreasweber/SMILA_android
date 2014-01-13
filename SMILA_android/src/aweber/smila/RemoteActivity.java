package aweber.smila;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import aweber.smila.tasks.MonitoringTask;
import aweber.smila.tasks.StartTask;
import aweber.smila.tasks.StopTask;

public class RemoteActivity extends Activity implements OnClickListener {

	String _jobRunId = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remote);
		findViewById(R.id.start_button).setOnClickListener(this);
		findViewById(R.id.stop_button).setOnClickListener(this);
	}

	@Override
	public void onClick(View clickedView) {
		int viewId = clickedView.getId();
		Button b = (Button) findViewById(viewId);
		b.setClickable(false);
		if (viewId == R.id.start_button) {
			AsyncTask<Void, Void, String> taskStart = new StartTask(this);
			taskStart.execute();
			try {
				String jobRunId = taskStart.get(3, TimeUnit.SECONDS);
				if (jobRunId != null) {
					_jobRunId = jobRunId;
				}
			} catch (Exception e) {
				Log.e("START", e.getMessage());
			}
		} else if (viewId == R.id.stop_button && _jobRunId != null) {
			AsyncTask<Void, Void, String> taskStop = new StopTask(this, _jobRunId);
			taskStop.execute();
			try {
				taskStop.get(3, TimeUnit.SECONDS);
				_jobRunId = null;
			} catch (Exception e) {
				Log.e("STOP", e.getMessage());
			}
		}
		AsyncTask<Void, Void, String> taskMon = new MonitoringTask(this);
		taskMon.execute();
	}

}

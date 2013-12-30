package aweber.smila;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MonitoringActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitoring);
		
		// get the textview
		TextView monitoringView = (TextView) findViewById(R.id.textViewMonitoring);
		monitoringView.setText("TEST");
	}
}

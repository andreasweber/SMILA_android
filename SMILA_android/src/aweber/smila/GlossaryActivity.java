package aweber.smila;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GlossaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listviewexampleactivity);

		final ListView listview = (ListView) findViewById(R.id.listview);
		String[] values = new String[] { getString(R.string.GL_Action), getString(R.string.GL_Blackboard), getString(R.string.GL_BPEL), getString(R.string.GL_Bucket), getString(R.string.GL_Bulk), getString(R.string.GL_Bulkbuilder), getString(R.string.GL_Crawler), getString(R.string.GL_Data_Object), getString(R.string.GL_DeltaChecker), getString(R.string.GL_Delta_indexing), getString(R.string.GL_Eclipse), getString(R.string.GL_Fetcher), getString(R.string.GL_ID), getString(R.string.GL_JMX), getString(R.string.GL_Job), getString(R.string.GL_Job_Run), getString(R.string.GL_Micro_bulk), getString(R.string.GL_ODE), getString(R.string.GL_OSGi), getString(R.string.GL_Pipelet), getString(R.string.GL_Record), getString(R.string.GL_Record_Bulk), getString(R.string.GL_Slot), getString(R.string.GL_SNMP), getString(R.string.GL_Task), getString(R.string.GL_UpdatePusher), getString(R.string.GL_Worker), getString(R.string.GL_Worker_Description), getString(R.string.GL_Workflow__asynchronous_), getString(R.string.GL_Workflow__synchronous_BPEL_), getString(R.string.GL_Workflow_run), getString(R.string.GL_WSDL), getString(R.string.GL_WS_BPEL), "__GLOSSARY_ID__" };

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0)
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								list.remove(item);
								adapter.notifyDataSetChanged();
								view.setAlpha(1);
							}
						});
			}

		});
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

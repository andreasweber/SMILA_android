package aweber.smila;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CommitterActivity extends Activity {
	List<String> committers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_committer);

		final ListView listview = (ListView) findViewById(R.id.listview_committer);

		committers = new ArrayList<String>();
		// add("__COMMITTER_ID__", "__COMMITTER_DETAILS_ID__");
		
		final StableArrayAdapter adapter = new StableArrayAdapter(
				this, android.R.layout.simple_list_item_1, committers);
		listview.setAdapter(adapter);
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
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
	
	private void add(String committerId, String committerDetailsId) {
		int id = getResources().getIdentifier(committerId, "string", getPackageName());
		String committer = getResources().getString(id);
		committers.add(committer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

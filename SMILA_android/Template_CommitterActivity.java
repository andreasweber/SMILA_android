package aweber.smila;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import aweber.smila.R;

public class CommitterActivity extends Activity {
	List<Map<String, String>> committersData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_committer);

		final ListView listview = (ListView) findViewById(R.id.listview_committer);

		committersData = new ArrayList<Map<String, String>>();
		// add("__COMMITTER_ID__", "__COMMITTER_DETAILS_ID__");
		
		final String[] fromMapKey = new String[] { "name", "details" };
		final int[] toLayoutId = new int[] { android.R.id.text1, android.R.id.text2 };

		ListAdapter listAdapter = new SimpleAdapter(this, committersData, android.R.layout.simple_list_item_2,
				fromMapKey, toLayoutId);
		
		listview.setAdapter(listAdapter);
	}

	private void add(String committerId, String committerDetailsId) {
		int id = getResources().getIdentifier(committerId, "string", getPackageName());
		String committer = getResources().getString(id);
		Map<String, String> entry = new HashMap<String, String>();
		entry.put("name", committer);
		entry.put("details", committer + " details");
		committersData.add(entry);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

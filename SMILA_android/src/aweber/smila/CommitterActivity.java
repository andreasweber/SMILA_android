package aweber.smila;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CommitterActivity extends Activity {
	List<Map<String, String>> committersData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_committer);

		final ListView listview = (ListView) findViewById(R.id.listview_committer);

		committersData = new ArrayList<Map<String, String>>();
		add("C_Andreas_Weber", "C_D_Andreas_Weber");
add("C_Peter_Wissel", "C_D_Peter_Wissel");
add("C_J_rgen_Schumacher", "C_D_J_rgen_Schumacher");
add("C_Daniel_Stucky", "C_D_Daniel_Stucky");
add("C_Andreas_Schank", "C_D_Andreas_Schank");
add("C_Thomas_Menzel", "C_D_Thomas_Menzel");
add("C_Marco_Strack", "C_D_Marco_Strack");
add("C_Peter_Palmar", "C_D_Peter_Palmar");
add("C_Tobias_Liefke", "C_D_Tobias_Liefke");
add("C_Drazen_Cindric", "C_D_Drazen_Cindric");
// add("__COMMITTER_ID__", "__COMMITTER_DETAILS_ID__");
		
		final String[] fromMapKey = new String[] { "name", "details" };
		final int[] toLayoutId = new int[] { R.id.committerName, R.id.committerDetails };

		ListAdapter listAdapter = new SimpleAdapter(this, committersData, R.layout.committers_list_row,
				fromMapKey, toLayoutId);

		listview.setAdapter(listAdapter);
	}

	private void add(String committerId, String committerDetailsId) {
		int id = getResources().getIdentifier(committerId, "string", getPackageName());
		String committer = getResources().getString(id);
		int detailsId = getResources().getIdentifier(committerDetailsId, "string", getPackageName());
		String committerDetails = getResources().getString(detailsId);
		Map<String, String> entry = new HashMap<String, String>();
		entry.put("name", committer);
		entry.put("details", committerDetails);
		committersData.add(entry);
	}

}

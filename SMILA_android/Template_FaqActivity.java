package aweber.smila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class FaqActivity extends Activity {
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faq);

		// get the listview
		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.listview_faq);

		// preparing list data
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();
		// add("__FAQ_ID__", "__FAQ__");

		ExpandableListAdapter listAdapter = new FaqExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	private void add(String idGroup, String idDetails) {
		int idG = getResources().getIdentifier(idGroup, "string", getPackageName());
		String group = getResources().getString(idG);

		int idD = getResources().getIdentifier(idDetails, "string", getPackageName());
		CharSequence details = Html.fromHtml(getResources().getString(idD)); 

		listDataHeader.add(group);
		listDataChild.put(group, Arrays.asList(details.toString()));
	}

}

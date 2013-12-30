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
		add("FAQ_Q_1", "FAQ_A_1");
add("FAQ_Q_2", "FAQ_A_2");
add("FAQ_Q_3", "FAQ_A_3");
add("FAQ_Q_4", "FAQ_A_4");
add("FAQ_Q_5", "FAQ_A_5");
add("FAQ_Q_6", "FAQ_A_6");
add("FAQ_Q_7", "FAQ_A_7");
add("FAQ_Q_8", "FAQ_A_8");
add("FAQ_Q_9", "FAQ_A_9");
add("FAQ_Q_10", "FAQ_A_10");
add("FAQ_Q_11", "FAQ_A_11");
add("FAQ_Q_12", "FAQ_A_12");
add("FAQ_Q_13", "FAQ_A_13");
add("FAQ_Q_14", "FAQ_A_14");
add("FAQ_Q_16", "FAQ_A_16");
add("FAQ_Q_17", "FAQ_A_17");
// add("__FAQ_ID__", "__FAQ__");

		ExpandableListAdapter listAdapter = new FaqExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	private void add(String idGroup, String idDetails) {
		int idG = getResources().getIdentifier(idGroup, "string", getPackageName());
		String group = Html.fromHtml(getResources().getString(idG)).toString();

		int idD = getResources().getIdentifier(idDetails, "string", getPackageName());
		String details = Html.fromHtml(getResources().getString(idD)).toString(); 

		listDataHeader.add(group);
		listDataChild.put(group, Arrays.asList(details));
	}

}

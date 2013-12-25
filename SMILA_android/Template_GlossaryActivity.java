package aweber.smila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class GlossaryActivity extends Activity {
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glossary);

		// get the listview
		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.listview_glossary);

		// preparing list data
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();
		// add("__GLOSSARY_TERM_ID__", "__GLOSSARY_TEXT_ID__");

		ExpandableListAdapter listAdapter = new GlossaryExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	private void add(String glossaryTermId, String glossaryTextId) {
		int idTerm = getResources().getIdentifier(glossaryTermId, "string", getPackageName());
		String term = getResources().getString(idTerm);

		int idText = getResources().getIdentifier(glossaryTextId, "string", getPackageName());
		String text = getResources().getString(idText);

		listDataHeader.add(term);
		listDataChild.put(term, Arrays.asList(text));
	}

}

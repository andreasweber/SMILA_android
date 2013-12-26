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
		add("GL_Action", "GL_TXT_Action");
add("GL_Attachment", "GL_TXT_Attachment");
add("GL_Attribute", "GL_TXT_Attribute");
add("GL_Blackboard", "GL_TXT_Blackboard");
add("GL_BPEL", "GL_TXT_BPEL");
add("GL_Bucket", "GL_TXT_Bucket");
add("GL_Bulk", "GL_TXT_Bulk");
add("GL_Bulkbuilder", "GL_TXT_Bulkbuilder");
add("GL_Crawler", "GL_TXT_Crawler");
add("GL_Data_Object", "GL_TXT_Data_Object");
add("GL_DeltaChecker", "GL_TXT_DeltaChecker");
add("GL_Delta_indexing", "GL_TXT_Delta_indexing");
add("GL_Eclipse", "GL_TXT_Eclipse");
add("GL_EILF", "GL_TXT_EILF");
add("GL_Equinox", "GL_TXT_Equinox");
add("GL_Fetcher", "GL_TXT_Fetcher");
add("GL_ID", "GL_TXT_ID");
add("GL_JMX", "GL_TXT_JMX");
add("GL_Job", "GL_TXT_Job");
add("GL_Job_Run", "GL_TXT_Job_Run");
add("GL_Micro_bulk", "GL_TXT_Micro_bulk");
add("GL_ODE", "GL_TXT_ODE");
add("GL_OSGi", "GL_TXT_OSGi");
add("GL_Pipelet", "GL_TXT_Pipelet");
add("GL_Pipeline", "GL_TXT_Pipeline");
add("GL_Record", "GL_TXT_Record");
add("GL_Record_Bulk", "GL_TXT_Record_Bulk");
add("GL_Slot", "GL_TXT_Slot");
add("GL_SNMP", "GL_TXT_SNMP");
add("GL_Task", "GL_TXT_Task");
add("GL_Tika", "GL_TXT_Tika");
add("GL_UpdatePusher", "GL_TXT_UpdatePusher");
add("GL_Worker", "GL_TXT_Worker");
add("GL_Worker_Description", "GL_TXT_Worker_Description");
add("GL_Workflow__asynchronous_", "GL_TXT_Workflow__asynchronous_");
add("GL_Workflow__synchronous_BPEL_", "GL_TXT_Workflow__synchronous_BPEL_");
add("GL_Workflow_run", "GL_TXT_Workflow_run");
add("GL_WSDL", "GL_TXT_WSDL");
add("GL_WS_BPEL", "GL_TXT_WS_BPEL");
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

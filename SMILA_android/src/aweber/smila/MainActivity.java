package aweber.smila;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ListView listview = (ListView) findViewById(R.id.listview_main);
		String[] values = new String[] { "Description  ", //
				"Glossary     ", //
				"FAQ          ", //
				"Committers   ", //
				"Monitoring   ", //
		};

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.main_list_row, list);
		listview.setAdapter(adapter);

		final Intent glossaryIntent = new Intent(this, GlossaryActivity.class);
		final Intent committerIntent = new Intent(this, CommitterActivity.class);
		final Intent descriptionIntent = new Intent(this, DescriptionActivity.class);
		final Intent faqIntent = new Intent(this, FaqActivity.class);
		final Intent monitoringIntent = new Intent(this, MonitoringActivity.class);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final String item = ((String) parent.getItemAtPosition(position)).trim();
				if ("Description".equals(item)) {
					startActivity(descriptionIntent);
				} else if ("Glossary".equals(item)) {
					startActivity(glossaryIntent);
				} else if ("FAQ".equals(item)) {
					startActivity(faqIntent);
				} else if ("Committers".equals(item)) {
					startActivity(committerIntent);
				} else if ("Monitoring".equals(item)) {
					startActivity(monitoringIntent);
				}
			}

		});
	}

}

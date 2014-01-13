package aweber.smila.tasks;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.os.AsyncTask;

public abstract class AbstractTask extends AsyncTask<Void, Void, String> {
	protected Activity _activity;

	public AbstractTask(Activity activity) {
		_activity = activity;
	}

	protected String executeGET(String path) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet("http://192.168.178.22:8080/smila/" + path);
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpResponse response = httpClient.execute(request, localContext);
		HttpEntity entity = response.getEntity();
		String jsonResult = getASCIIContentFromEntity(entity);
		return jsonResult;
	}
	
	protected String executePOST(String path) throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost("http://192.168.178.22:8080/smila/" + path);
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpResponse response = httpClient.execute(request, localContext);
		HttpEntity entity = response.getEntity();
		String jsonResult = getASCIIContentFromEntity(entity);
		return jsonResult;
	}

	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}
}

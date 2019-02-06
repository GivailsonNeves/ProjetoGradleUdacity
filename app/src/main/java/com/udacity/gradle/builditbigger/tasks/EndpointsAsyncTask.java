package com.udacity.gradle.builditbigger.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, EndpointsAsyncTask.OnEndPointCallBack>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private OnEndPointCallBack callBack;


    @Override
    protected String doInBackground(Pair<Context, OnEndPointCallBack>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        context = params[0].first;
        callBack = params[0].second;

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            if (this.callBack != null)
                this.callBack.onEndPointError(e.getMessage());

            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(this.callBack != null)
            this.callBack.onEndPointBack(result);
    }

    public interface OnEndPointCallBack {
        void onEndPointBack(String response);
        void onEndPointError(String error);
    }
}

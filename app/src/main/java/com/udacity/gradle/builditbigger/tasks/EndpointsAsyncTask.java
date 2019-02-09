package com.udacity.gradle.builditbigger.tasks;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.apache.GoogleApacheHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.OnEndPointCallBack, Void, String> {

    private static MyApi myApiService = null;
    private OnEndPointCallBack callBack;



    @Override
    protected String doInBackground(OnEndPointCallBack... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = null;

            builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new JacksonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setApplicationName("androidjoke")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                          //  abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();


        }

        callBack = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return  null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(this.callBack != null)
            this.callBack.onEndPointBack(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if(this.callBack != null)
            this.callBack.onEndPointBack(null);
    }

    public interface OnEndPointCallBack {
        void onEndPointBack(String response);
    }
}

package com.udacity.gradle.builditbigger.tasks;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.OnEndPointCallBack, Void, String> {

    private static MyApi myApiService = null;
    private OnEndPointCallBack callBack;



    @Override
    protected String doInBackground(OnEndPointCallBack... params) {

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

        callBack = params[0];

        DataReturn dataReturn = new DataReturn();

        try {
            dataReturn.data = myApiService.getJoke().execute().getData();
            dataReturn.result = true;
        } catch (IOException e) {
            return  null;
        }
        return dataReturn;
    }

    @Override
    protected void onPostExecute(DataReturn result) {
        if(this.callBack != null)
            this.callBack.onEndPointBack(result);
    }

    public interface OnEndPointCallBack {
        void onEndPointBack(String response);
    }
}

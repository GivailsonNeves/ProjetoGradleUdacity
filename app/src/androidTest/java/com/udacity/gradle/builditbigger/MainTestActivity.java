package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;

public class MainTestActivity extends MainActivity {

    private LoadTestCallBack loadCallBack;

    public interface LoadTestCallBack{
        void onLoadCallBack(String response);
    }

    public void setLoadCallBack(LoadTestCallBack loadCallBack) {
        this.loadCallBack = loadCallBack;
    }

    @Override
    public void onEndPointBack(String response) {
        super.onEndPointBack(response);
        loadCallBack.onLoadCallBack(response);
    }
}

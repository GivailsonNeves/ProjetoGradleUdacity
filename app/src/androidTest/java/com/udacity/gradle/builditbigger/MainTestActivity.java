package com.udacity.gradle.builditbigger;

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
        loadCallBack.onLoadCallBack(response);
    }
}

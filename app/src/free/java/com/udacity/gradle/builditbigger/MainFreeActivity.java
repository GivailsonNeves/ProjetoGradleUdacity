package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;

import br.com.givailson.androidjokes.AndroidJokeActivity;


public class MainFreeActivity extends AppCompatActivity implements EndpointsAsyncTask.OnEndPointCallBack {

    private View formContent;
    private View loaderContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_main);
        prepareView();
    }

    private void prepareView() {
        formContent = findViewById(R.id.formContent);
        loaderContent = findViewById(R.id.progressBar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        showLoader();
        new EndpointsAsyncTask()
                .execute(this);
    }

    @Override
    public void onEndPointBack(String response) {
        showJokeView(response);
    }

    private void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void showLoader() {
        this.formContent.setVisibility(View.GONE);
        this.loaderContent.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        this.formContent.setVisibility(View.VISIBLE);
        this.loaderContent.setVisibility(View.GONE);
    }

    private void showJokeView(String response) {
        hideLoader();
        if (response != null) {
            Intent intent = new Intent(this, AndroidJokeActivity.class);
            intent.putExtra(getString(R.string.joke_param), response);
            startActivity(intent);
        } else {
            showError("Verifique se o servidor está conectado!");
        }
    }


    @Override
    public void onEndPointBack(EndpointsAsyncTask.DataReturn response) {

        if (response.result) {
            showJokeView(response.data);
        } else {
            hideLoader();
           showError(response.data);
        }
    }

}

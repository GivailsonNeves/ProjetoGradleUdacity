package br.com.givailson.androidjokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.givailson.javajokes.Jokes;

public class AndroidJokeActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_joke_activity);

        prepareUI();
        prepareNavBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareNavBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.cancel);
    }

    private void prepareUI() {

        this.tvJoke = findViewById(R.id.tvJoke);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String joke = bundle.getString(getString(R.string.joke_param));
            if (joke != null) {
                this.tvJoke.setText(joke);
            } else {
                Toast.makeText(this, R.string.erro_no_joke_data, Toast.LENGTH_LONG);
            }
        } else {
            Toast.makeText(this, R.string.erro_no_joke, Toast.LENGTH_LONG);
        }

    }

}

package br.com.givailson.androidjokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.givailson.javajokes.models.JokeModel;

public class AndroidJokeActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvJoke;
    private ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_joke_activity);

        prepareUI();
    }

    private void prepareUI() {

        this.tvJoke = findViewById(R.id.tvJoke);
        this.tvTitle = findViewById(R.id.tvTitle);
        this.btnClose = findViewById(R.id.btClose);

        this.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        JokeModel jokeModel = (JokeModel) bundle.getSerializable("joke");
        this.tvTitle.setText(jokeModel.getTitle());
        this.tvJoke.setText(jokeModel.getJoke());
    }

}

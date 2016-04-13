package xyz.juancastano.listas.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import xyz.juancastano.listas.R;
import xyz.juancastano.listas.models.Post;

public class DescripcionPostActivity extends AppCompatActivity {

    private TextView mDescPostId;
    private TextView mDescPostTitle;
    private TextView mDescPostBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_post);

        mDescPostId = (TextView) findViewById(R.id.descPostId);
        mDescPostTitle = (TextView) findViewById(R.id.descPostTitle);
        mDescPostBody = (TextView) findViewById(R.id.descPostBody);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        Post post = (Post) bundle.get("POST");

        mDescPostId.setText("ID: " + post.getId());
        mDescPostTitle.setText(post.getTitle());
        mDescPostBody.setText(post.getBody());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

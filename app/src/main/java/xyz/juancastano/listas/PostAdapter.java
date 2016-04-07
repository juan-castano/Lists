package xyz.juancastano.listas;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jcastano on 06/04/16.
 */
public class PostAdapter extends ArrayAdapter<Post> {

    public PostAdapter(Context context, int resource, List<Post> listaPost) {
        super(context, resource, listaPost);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
        }

        Post post = (Post) getItem(position);

        TextView mPostTitle = (TextView) convertView.findViewById(R.id.postTitle);
        TextView mPostBody = (TextView) convertView.findViewById(R.id.postBody);

        mPostTitle.setText(post.getTitle());
        mPostBody.setText(post.getBody());

        return convertView;
    }

}

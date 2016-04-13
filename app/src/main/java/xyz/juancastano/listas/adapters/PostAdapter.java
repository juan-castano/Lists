package xyz.juancastano.listas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xyz.juancastano.listas.R;
import xyz.juancastano.listas.models.Post;

/**
 * Created by jcastano on 06/04/16.
 */
public class PostAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Post> listaPost;

    public PostAdapter(Context context, List<Post> listaPost) {
        this.listaPost = listaPost;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.listaPost.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaPost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_post, parent, false);
        }

        Post post = (Post) getItem(position);

        TextView mPostTitle = (TextView) convertView.findViewById(R.id.postTitle);
        TextView mPostBody = (TextView) convertView.findViewById(R.id.postBody);

        mPostTitle.setText(post.getTitle());
        mPostBody.setText(post.getBody());

        return convertView;
    }



}

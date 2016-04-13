package xyz.juancastano.listas.fragments;


import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import xyz.juancastano.listas.activities.DescripcionPostActivity;
import xyz.juancastano.listas.loaders.PostLoader;
import xyz.juancastano.listas.R;
import xyz.juancastano.listas.adapters.PostAdapter;
import xyz.juancastano.listas.models.Post;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Post>> {


    // PostAdapter postAdapter;

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new PostAdapter(getActivity(), new LinkedList<Post>()));


        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public Loader<List<Post>> onCreateLoader(int id, Bundle args) {

        return new PostLoader(PostFragment.this.getActivity());

    }

    @Override
    public void onLoadFinished(Loader<List<Post>> loader, List<Post> postList) {

        System.out.println("=== OnLoadFinished ===");
        System.out.println(postList.size());
        System.out.println("=== OnLoadFinished ===");

        setListAdapter(new PostAdapter(getActivity(), postList));

    }

    @Override
    public void onLoaderReset(Loader loader) {

        setListAdapter(new PostAdapter(getActivity(), new LinkedList<Post>()));

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Post post = (Post) getListAdapter().getItem(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("POST", post);

        Intent intent = new Intent(getActivity(), DescripcionPostActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);

        Toast.makeText(getActivity(), "Item " + position, Toast.LENGTH_SHORT).show();
    }
}

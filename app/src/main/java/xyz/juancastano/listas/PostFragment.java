package xyz.juancastano.listas;


import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;


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
}

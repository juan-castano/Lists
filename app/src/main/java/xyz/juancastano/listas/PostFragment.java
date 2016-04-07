package xyz.juancastano.listas;


import android.app.ListFragment;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends ListFragment implements AdapterView.OnItemClickListener {


    private List<Post> listaPost;
    private PostAdapter postAdapter;

    public PostFragment() {
        // Required empty public constructor
        listaPost = new LinkedList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.item_post,container);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Colegios en la Web");
        progressDialog.setMessage("Descargando información");
        progressDialog.setIndeterminate(true);

        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "http://jsonplaceholder.typicode.com/posts",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        progressDialog.dismiss();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                Post post = new Post();
                                post.setBody(jsonObject.getString("body"));
                                post.setTitle(jsonObject.getString("title"));

                                Log.i("PostFragment", post.getBody() + post.getTitle());

                                listaPost.add(post);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        postAdapter = new PostAdapter(getActivity(), 0, listaPost);
                        setListAdapter(postAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),
                                "Ocurrió un error al descargar la información",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

        queue.add(arrayRequest);

        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(), listaPost.get(position).getTitle(), Toast.LENGTH_SHORT).show();

    }

}

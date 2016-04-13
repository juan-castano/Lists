package xyz.juancastano.listas.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import xyz.juancastano.listas.models.Post;

/**
 * Created by jcastano on 07/04/16.
 */
public class PostLoader extends AsyncTaskLoader<List<Post>> {
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */

    private final static String TAG = "PostLoader";

    public PostLoader(Context context) {
        super(context);
    }

    @Override
    public List<Post> loadInBackground() {

        List<Post> listaPost = new LinkedList<>();

        HttpURLConnection urlConnection = null;

        try {

            URL url = new URL("http://jsonplaceholder.typicode.com/posts");
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");

            int reponse = urlConnection.getResponseCode();

            if (reponse == HttpURLConnection.HTTP_OK) {

                BufferedReader inStream = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));

                String inputLine = "";

                StringBuffer buffer = new StringBuffer();

                while ( (inputLine = inStream.readLine()) != null) {
                    buffer.append(inputLine);
                }
                inStream.close();

                Log.i(TAG, buffer.toString());

                JSONArray jsonArray = new JSONArray(buffer.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonPost = jsonArray.getJSONObject(i);

                    Log.i(TAG, jsonPost.getInt("id") + " - " + jsonPost.getString("title"));

                    listaPost.add(new Post(jsonPost.getInt("id"), jsonPost.getString("title"), jsonPost.getString("body")));

                }

            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return listaPost;

    }

}

package com.kryptkode.cyberman.djtech.ui.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kryptkode.cyberman.djtech.R;
import com.kryptkode.cyberman.djtech.adapters.HomeScreenAdapter;
import com.kryptkode.cyberman.djtech.models.BlogPosts;
import com.kryptkode.cyberman.djtech.models.Posts;
import com.kryptkode.cyberman.djtech.utils.HomeScreenFragmentHelper;
import com.kryptkode.cyberman.djtech.utils.ItemDivider;
import com.kryptkode.cyberman.djtech.utils.JsonHelper;
import com.kryptkode.cyberman.djtech.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeScreenFragment extends Fragment implements HomeScreenAdapter.HomeScreenAdapterListener,
        LoaderManager.LoaderCallbacks<String>{

    public static final int POST_LOADER_ID = 100;
    public static final String TAG = HomeScreenFragment.class.getSimpleName();

    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String LINK = "link";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String AUTHOR = "author";
    private static final String RENDERED = "rendered";
    public static final String POSTS = "dj_tech_posts";

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;

    private TextView errorTextView;
    private ImageView errorImageView;
    private ProgressBar progressBar;
    private TextView loadingTextView;

    private LinearLayoutManager linearLayoutManager;
    private HomeScreenAdapter adapter;
    private HomeScreenFragmentListener homeScreenFragmentListener;

    private ArrayList<BlogPosts> postsArrayList;


    private ConnectivityChangeReceiver receiver;


    public interface HomeScreenFragmentListener{
        void onItemClicked(int position, BlogPosts blogPostsArrayList);
        void onAuthorAvatarClicked(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeScreenFragmentListener = (HomeScreenFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeScreenFragmentListener = null;
    }

    public HomeScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        this.view = view;
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.post_swipe_refresh_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.post_recycler_view);

        if (savedInstanceState != null && savedInstanceState.containsKey(POSTS)) {
            postsArrayList = savedInstanceState.getParcelableArrayList(POSTS);
        }else{

            postsArrayList = new ArrayList<>();
        }


        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new HomeScreenAdapter(getContext(), postsArrayList);
        adapter.setHomeScreenAdapterListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new ItemDivider(getContext()));
        swipeRefreshLayout.setOnRefreshListener(refreshListener);

        errorImageView = (ImageView) view.findViewById(R.id.error_image_view);
        errorTextView = (TextView) view.findViewById(R.id.error_text_view);
        loadingTextView = (TextView) view.findViewById(R.id.loading_text_view);
        progressBar = (ProgressBar) view.findViewById(R.id.loading_progress_bar);


        if(NetworkUtils.isOnline(getContext())){
            getLoaderManager().initLoader(POST_LOADER_ID, null, this);
        }else{
            //if the device is not online show the error indicatiors
            HomeScreenFragmentHelper.indicatorsAppear(new View[]{errorTextView, errorImageView}, true);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(POSTS, postsArrayList);

        super.onSaveInstanceState(outState);

    }

    //swipe to refresh
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshRecyclerView();
        }
    };

    private void refreshRecyclerView() {
       // addNewItemsToList(10);
        onItemsLoaded();
        Snackbar.make(view.findViewById(R.id.post_root), R.string.refreshing, Snackbar.LENGTH_SHORT).show();
    }

    private void addNewItemsToList(BlogPosts [] arrayList) {
        for (BlogPosts post:arrayList ) {
            postsArrayList.add(post);
            Log.i(TAG, "addNewItemsToList: -->" + post.getId());
        }


    }

    private void onItemsLoaded() {
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    //checks for network connectivity //TODO Implemnt the receiver
    public class ConnectivityChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkUtils.isOnline(getContext())) {
               /* if (state == null){
                    getData();
                    Snackbar.make(findViewById(R.id.root_main), R.string.is_connected_text, Snackbar.LENGTH_SHORT).show();
                }
                showRecyclerView();
            } else {
                Snackbar.make(findViewById(R.id.root_main), R.string.is_not_connected_text, Snackbar.LENGTH_SHORT).show();
                if (!dataLoaded){
                    displayError();
                }*/
            }
        }
    }

    @Override
    public void onItemClicked(int position) {
        homeScreenFragmentListener.onItemClicked(position, postsArrayList.get(position));
    }

    @Override
    public void onAuthorAvatarClicked(int position) {
        homeScreenFragmentListener.onAuthorAvatarClicked(position);

    }

    //loader methods
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        HomeScreenFragmentHelper.indicatorsAppear(new View[]{progressBar, loadingTextView}, true);
        HomeScreenFragmentHelper.indicatorsAppear(new View[]{recyclerView}, false);
        switch(id){
            case POST_LOADER_ID:
                return new HomeScreenFragmentHelper.HomeAsyncTaskLoader(getContext());
            default:
                return null;
        }

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        HomeScreenFragmentHelper.indicatorsAppear(new View[]{progressBar, loadingTextView}, false);
        HomeScreenFragmentHelper.indicatorsAppear(new View[]{recyclerView}, true);
        addNewItemsToList(JsonHelper.parseJson(data));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }




}

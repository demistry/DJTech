package com.kryptkode.cyberman.djtech.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryptkode.cyberman.djtech.R;
import com.kryptkode.cyberman.djtech.adapters.HomeScreenAdapter;
import com.kryptkode.cyberman.djtech.models.Posts;
import com.kryptkode.cyberman.djtech.utils.ItemDivider;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeScreenFragment extends Fragment implements HomeScreenAdapter.HomeScreenAdapterListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private HomeScreenAdapter adapter;
    private HomeScreenFragmentListener homeScreenFragmentListener;

    private ArrayList<Posts> postsArrayList;

    public interface HomeScreenFragmentListener{
        void onItemClicked(int position);
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

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.post_swipe_refresh_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.post_recycler_view);
        postsArrayList = Posts.generateDummyPosts(10);
        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new HomeScreenAdapter(getContext(), postsArrayList);
        adapter.setHomeScreenAdapterListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new ItemDivider(getContext()));
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        return view;
    }

    //swipe to refresh
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshRecyclerView();
        }
    };

    private void refreshRecyclerView() {
        addNewItemsToList(10);
        onItemsLoaded();
    }

    private void addNewItemsToList(int i) {
        ArrayList<Posts> arrayList = Posts.generateDummyPosts(i);
        for (Posts post:arrayList ) {
            postsArrayList.add(post);
        }
    }

    private void onItemsLoaded() {
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClicked(int position) {
        homeScreenFragmentListener.onItemClicked(position);
    }

    @Override
    public void onAuthorAvatarClicked(int position) {
        homeScreenFragmentListener.onAuthorAvatarClicked(position);

    }
}

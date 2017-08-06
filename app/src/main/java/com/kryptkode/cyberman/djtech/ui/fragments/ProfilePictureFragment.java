package com.kryptkode.cyberman.djtech.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kryptkode.cyberman.djtech.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePictureFragment extends Fragment {


    public ProfilePictureFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_picture_layout, container, false);
        ImageView profileImage = (ImageView) view.findViewById(R.id.fragment_profile_picture);
        int position = getArguments().getInt("Position");
        switch (position){
            case 0: profileImage.setImageResource(R.drawable.david); break;
            case 1: profileImage.setImageResource(R.drawable.john_esan); break;
            case 2: profileImage.setImageResource(R.drawable.paul); break;
            case 3: profileImage.setImageResource(R.drawable.marvex); break;
            case 4: profileImage.setImageResource(R.drawable.isaiah); break;
            case 5: profileImage.setImageResource(R.drawable.udoka); break;
            case 6: profileImage.setImageResource(R.drawable.mirabello); break;
            case 7: profileImage.setImageResource(R.drawable.stone); break;
        }
        return view;
    }

}

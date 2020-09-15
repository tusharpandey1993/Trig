package com.trig.trigapp.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trig.trigapp.Adapter.DynamicSliderAdapter;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoListFragment extends Fragment implements OnClickInterface {

    RecyclerView videoListRecycler;

    FragmentActivity mActivity;
    View mView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    public VideoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_video_list, container, false);

        init(mView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        videoListRecycler.setLayoutManager(mLayoutManager);
        videoListRecycler.setNestedScrollingEnabled(false);
        videoListRecycler.setAdapter(new DynamicSliderAdapter(mActivity,this));


        return mView;

    }

    private void init(View view) {

        videoListRecycler = (RecyclerView) view.findViewById(R.id.videoListRecycler);
    }

    @Override
    public void onClick(View view, int position) {
        Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                .navigate(R.id.action_VideoListFrag_to_VideoStreamingFrag);
    }
}

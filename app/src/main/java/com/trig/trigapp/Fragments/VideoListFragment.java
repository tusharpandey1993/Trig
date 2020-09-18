package com.trig.trigapp.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    ImageView backIcon;
    TextView toolBarText;


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

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_VideoListFrag_to_DashbordFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(mActivity, callback);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_VideoListFrag_to_DashbordFragment);
            }
        });



        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        videoListRecycler.setLayoutManager(mLayoutManager);
        videoListRecycler.setNestedScrollingEnabled(false);
        videoListRecycler.setAdapter(new DynamicSliderAdapter(mActivity,this));


        return mView;

    }

    private void init(View view) {

        videoListRecycler =  view.findViewById(R.id.videoListRecycler);
        backIcon = view.findViewById(R.id.backIcon);
        toolBarText = view.findViewById(R.id.toolBarText);
        toolBarText.setText("Induction Training");
    }

    @Override
    public void onClick(View view, int position) {
        Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                .navigate(R.id.action_VideoListFrag_to_VideoStreamingFrag);
    }
}

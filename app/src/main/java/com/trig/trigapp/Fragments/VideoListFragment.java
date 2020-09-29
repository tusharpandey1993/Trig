package com.trig.trigapp.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.trig.trigapp.Adapter.DynamicSliderAdapter;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Request.getCourseDetailsReq;
import com.trig.trigapp.api.Response.getAssessmentListRes;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;

import java.util.ArrayList;

import static com.trig.trigapp.Fragments.DashboardFragment.fromCourses;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoListFragment extends Fragment implements IPresenter, OnClickInterface {

    RecyclerView videoListRecycler;

    FragmentActivity mActivity;
    View mView;
    ImageView backIcon;
    TextView toolBarText;
    private ViewModel viewModel;
    private ArrayList<getCourseListRes> getAssessmentListResArray;
    private static final String TAG = "VideoListFragment";

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
        getCourseDetailsReq courseDetailsReq = new getCourseDetailsReq();
        courseDetailsReq.setUserid("9919");
        courseDetailsReq.setTopic_id(1);
        viewModel.callCourses(courseDetailsReq);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_VideoListFrag_to_topics);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(mActivity, callback);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_VideoListFrag_to_topics);
            }
        });



        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        videoListRecycler.setLayoutManager(mLayoutManager);
        videoListRecycler.setNestedScrollingEnabled(false);
        videoListRecycler.setAdapter(new DynamicSliderAdapter(mActivity,this));


        return mView;

    }

    private void init(View view) {
        viewModel = new ViewModel(mActivity, this);
        videoListRecycler =  view.findViewById(R.id.videoListRecycler);
        backIcon = view.findViewById(R.id.backIcon);
        toolBarText = view.findViewById(R.id.toolBarText);

        if(fromCourses) {
            toolBarText.setText("My Courses");
        } else {
            toolBarText.setText("My Assessments");
        }
    }

    @Override
    public void onClick(View view, int position) {
        if(fromCourses) {
            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_VideoListFrag_to_VideoStreamingFrag);
        } else {
            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_VideoListFrag_to_AssessmentFragment);
        }
    }

    @Override
    public void onResponseVideoList(JsonArray jsonArray) {
        getAssessmentListResArray = new ArrayList<>();
        for(int i =0; i < jsonArray.size(); i++) {
            getCourseListRes getAssessmentListRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), getCourseListRes.class);
            getAssessmentListResArray.add(getAssessmentListRes);
        }
    }
}

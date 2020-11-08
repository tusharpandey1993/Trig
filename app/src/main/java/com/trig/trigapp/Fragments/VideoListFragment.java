package com.trig.trigapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
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
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.Adapter.VideoListAdapter;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Request.getCourseDetailsReq;
import com.trig.trigapp.api.Response.VideoListResponse;
import com.trig.trigapp.api.Response.getAssessmentListRes;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.trig.trigapp.Fragments.DashboardFragment.fromCourses;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoListFragment extends BaseFragment implements IPresenter, OnClickInterface,  VideoListAdapter.ItemListener {

    private static final String TAG = VideoListFragment.class.getSimpleName();
    private RecyclerView videoListRecycler;
    private FragmentActivity mActivity;
    private View mView;
    private ImageView backIcon;
    private TextView toolBarText;
    private ViewModel viewModel;
    private ArrayList<VideoListResponse> videoListResponses;
    private int item_id_from_bundle;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        item_id_from_bundle = Constants.getInstance().Courses_1st_item;
    }

    public VideoListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (getArguments() != null) {
                item_id_from_bundle = Constants.getInstance().Courses_1st_item;
            }
        } catch (Exception e){
            Log.e(TAG, "onCreate: exception" + e.getMessage() );
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            mView = inflater.inflate(R.layout.fragment_video_list, container, false);

            init(mView);

            hitListApi();

            OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
                @Override
                public void handleOnBackPressed() {
                    // Handle the back button event
                    navigationCode(R.id.action_VideoListFrag_to_topics);
                }
            };
            requireActivity().getOnBackPressedDispatcher().addCallback(mActivity, callback);

        } catch (Exception e) {
            Log.e(TAG, "onCreateView: exception" + e.getMessage());
        }
        return mView;
    }

    private void setDataToRecyclerView() {
        try {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
            videoListRecycler.setLayoutManager(mLayoutManager);
            videoListRecycler.setNestedScrollingEnabled(false);
            videoListRecycler.setAdapter(new VideoListAdapter(mActivity, videoListResponses,this));
        } catch (Exception e) {
            Log.e(TAG, "setDataToRecyclerView: exception" + e.getMessage());
        }
    }

    private void hitListApi() {
        try {
            getCourseDetailsReq courseDetailsReq = new getCourseDetailsReq();
            courseDetailsReq.setUserid(TrigAppPreferences.getUserId(mActivity));
            courseDetailsReq.setTopic_id(item_id_from_bundle);
            viewModel.callCourses(courseDetailsReq);
        } catch (Exception e) {
            Log.e(TAG, "hitListApi: exception" + e.getMessage());
        }
    }

    private void navigationCode(int nav) {
        try {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(nav);
        } catch (Exception e) {
            Log.e(TAG, "setDataToRecyclerView: exception" + e.getMessage());
        }
    }

    private void init(View view) {
        try {
            viewModel = new ViewModel(mActivity, this);
            videoListRecycler =  view.findViewById(R.id.videoListRecycler);
            backIcon = view.findViewById(R.id.backIcon);
            toolBarText = view.findViewById(R.id.toolBarText);

            backIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigationCode(R.id.action_VideoListFrag_to_topics);
                }
            });

            if(fromCourses) {
                toolBarText.setText(Constants.getInstance().My_Courses);
            } else {
                toolBarText.setText(Constants.getInstance().My_Assessments);
            }
        } catch (Exception e) {
            Log.e(TAG, "init: exception" + e.getMessage());
        }
    }

    @Override
    public void onClick(View view, int position) {
        try {
            if(fromCourses) {
                navigationCode(R.id.action_VideoListFrag_to_VideoStreamingFrag);
            } else {
                navigationCode(R.id.action_VideoListFrag_to_AssessmentFragment);
            }
        } catch (Exception e) {
            Log.e(TAG, "onClick: exception" + e.getMessage());
        }
    }

    @Override
    public void onResponseVideoList(JsonArray jsonArray) {
        try {
            videoListResponses = new ArrayList<>();
            for(int i =0; i < jsonArray.size(); i++) {
                VideoListResponse getAssessmentListRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), VideoListResponse.class);
                videoListResponses.add(getAssessmentListRes);

                setDataToRecyclerView();
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseVideoList: exception" + e.getMessage());
        }

    }

    @Override
    public void onItemClick(VideoListResponse videoListResponse) {
        Constants.getInstance().Courses_2st_item = Integer.parseInt(videoListResponse.getCourse_id());
        if(fromCourses) {
            navigationCode(R.id.action_VideoListFrag_to_VideoStreamingFrag);
        } else {
            navigationCode(R.id.action_VideoListFrag_to_AssessmentFragment);
        }
    }
}

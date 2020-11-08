package com.trig.trigapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonArray;
import com.trig.trigapp.Adapter.AssessmentTopicsAdapter;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;

import java.util.ArrayList;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.fragment.app.FragmentActivity;
import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.Adapter.CourseTopicAdapter;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Response.getAssessmentListRes;
import com.trig.trigapp.api.Response.getCourseListRes;

import androidx.recyclerview.widget.GridLayoutManager;

import static com.trig.trigapp.Fragments.DashboardFragment.fromCourses;

public class CoursesTopics extends BaseFragment implements IPresenter, CourseTopicAdapter.ItemListener, AssessmentTopicsAdapter.ItemListener {

    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    private RecyclerView recyclerView;
    private TextView toolBarText;
    private ImageView backIcon;
    private ViewModel viewModel;
    private ArrayList<getCourseListRes> arrayListOfgetCousreList;
    private ArrayList<getAssessmentListRes> assessmentListResArrayList;
    private Bundle bundle;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    public CoursesTopics() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.course_topic, container, false);
        try {
            init(mView);
            hitApi();
        } catch (Exception e) {
            Log.e(TAG, "onResponseAssessmentTopicList: exception " + e.getMessage());
        }

        return mView;
    }

    private void hitApi() {
        try {
            if (Utility.getInstance().isNetworkAvailable(mActivity)) {
                if (fromCourses) {
                    CommonReq commonReq = new CommonReq();
                    commonReq.setUserid(TrigAppPreferences.getUserId(mActivity));
                    viewModel.callgetCourseTopics(commonReq);
                } else {
                    viewModel.callgetAssessmentList(TrigAppPreferences.getUserId(mActivity));
                }
            } else {
                Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.no_internet_message));
            }
        } catch (Exception e) {
            Log.e(TAG, "hitApi: exception " + e.getMessage());
        }
    }

    private void init(View mView) {
        try {
            viewModel = new ViewModel(mActivity, this);
            toolBarText = mView.findViewById(R.id.toolBarText);
            backIcon = mView.findViewById(R.id.backIcon);
            recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
            if (fromCourses) {
                toolBarText.setText(Constants.getInstance().Courses);
            } else {
                toolBarText.setText(Constants.getInstance().Assessments);
            }
            OnBackPressedCallback callback = new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_topics_to_dashboardFragment);
                }
            };
            requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
            backIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_topics_to_dashboardFragment);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "init: exception " + e.getMessage());
        }
    }

    @Override
    public void onResponseCourseTopicList(JsonArray jsonArray) {
        try {
            if (jsonArray != null) {
                arrayListOfgetCousreList = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    getCourseListRes getCourseListRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), getCourseListRes.class);
                    arrayListOfgetCousreList.add(getCourseListRes);

                }
                Log.d(TAG, "onResponseCourseTopicList: " + jsonArray);
//                TrigAppPreferences.setVideoApiResponse(mActivity, arrayListOfgetCousreList);
                CourseTopicAdapter adapter = new CourseTopicAdapter(mActivity, arrayListOfgetCousreList, this);
                recyclerView.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseCourseTopicList: exception " + e.getMessage());
        }
    }

    @Override
    public void onResponseAssessmentTopicList(JsonArray jsonArrayAssessment) {
        Log.d(TAG, "onResponseCourseTopicList: " + jsonArrayAssessment);
        try {
            assessmentListResArrayList = new ArrayList<>();
            if (jsonArrayAssessment != null) {
                assessmentListResArrayList = new ArrayList<>();
                for (int i = 0; i < jsonArrayAssessment.size(); i++) {
                    getAssessmentListRes getAssessmentListRes = Utility.getInstance().getG().fromJson(jsonArrayAssessment.get(i), getAssessmentListRes.class);
                    assessmentListResArrayList.add(getAssessmentListRes);
                }
                Log.d(TAG, "onResponseAssessmentTopicList: size " + assessmentListResArrayList.size());

                AssessmentTopicsAdapter adapter2 = new AssessmentTopicsAdapter(mActivity, assessmentListResArrayList, this);
                recyclerView.setAdapter(adapter2);
                LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(mActivity);
                recyclerView.setLayoutManager(mLayoutManager2);
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseAssessmentTopicList: exception " + e.getMessage());
        }
    }

    @Override
    public void onItemClick(getCourseListRes getCourseListRes) {
        try {
            Constants.getInstance().Courses_1st_item = getCourseListRes.getTopic_id();

            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_topics_to_VideoFragment, bundle);
        } catch (Exception e) {
            Log.e(TAG, "onItemClick:getCourseListRes exception " + e.getMessage());
        }

    }

    @Override
    public void onItemClick(getAssessmentListRes item) {
        try {
            if(item != null) {
                if(!item.getStatus().isEmpty() && item.getStatus().equalsIgnoreCase(Constants.getInstance().completed)){
                    Constants.getInstance().Assessment_1st_status = Constants.getInstance().completed;
                } else {
                    Constants.getInstance().Assessment_1st_status = Constants.getInstance().not_started;
                }
                Constants.getInstance().Assessment_1st_item = item.getAssestment_id();

                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_topics_to_AssessmentFragment, bundle);
            }

        } catch (Exception e) {
            Log.e(TAG, "onItemClick:getAssessmentListRes exception " + e.getMessage());
        }
    }
}
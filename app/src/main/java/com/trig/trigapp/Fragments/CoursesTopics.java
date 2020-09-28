package com.trig.trigapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
import com.trig.trigapp.Model.DataModel;
import androidx.fragment.app.FragmentActivity;
import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.Adapter.CourseTopicAdapter;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.trig.trigapp.api.Response.getLoginRes;
import com.trig.trigapp.api.Response.ProfileResponse;

import androidx.recyclerview.widget.GridLayoutManager;
import static com.trig.trigapp.Fragments.DashboardFragment.fromCourses;

public class CoursesTopics extends Fragment implements IPresenter, CourseTopicAdapter.ItemListener{

    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    RecyclerView recyclerView;
    ArrayList arrayList;
    TextView toolBarText;
    ImageView backIcon;
    ViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    public CoursesTopics() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.course_topic, container, false);
        viewModel = new ViewModel(mActivity,this);
        viewModel.callCourses(1);
        init(mView);


        arrayList = new ArrayList();
        arrayList.add(new DataModel("TRIG Introduction",1,1, "#09A9FF"));
        arrayList.add(new DataModel("Skill Training",1,1, "#09A9FF"));

        CourseTopicAdapter adapter = new CourseTopicAdapter(mActivity, arrayList, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        return mView;
    }

    private void init(View mView) {
        toolBarText = mView.findViewById(R.id.toolBarText);
        backIcon = mView.findViewById(R.id.backIcon);
        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        if(fromCourses){
            toolBarText.setText("Courses");
        } else {
            toolBarText.setText("Assessment");
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
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
    }

    @Override
    public void onItemClick(DataModel item) {

        if(fromCourses){
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_topics_to_VideoFragment);
        } else {
            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_topics_to_AssessmentFragment);
        }

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onError(String error, int code) {

    }

    @Override
    public void onError(Object error) {

    }

    @Override
    public void onError(Object error, int Code) {

    }

    @Override
    public void onResponse(getLoginRes loginResponse) {

    }

    @Override
    public void onResponseProfile(ProfileResponse profileResponse) {

    }

    @Override
    public void onResponseProfile(getDashboardRes dashboardResponse) {

    }

    @Override
    public void onResponseCourseList(JsonArray jsonArray) {
        for(int i =0; i < jsonArray.size(); i++) {
            new Gson().fromJson(jsonArray.get(i), getCourseListRes.class);
            Log.d(TAG, "onResponseCourseList: ");
            Log.d(TAG, "onResponseCourseList: " + jsonArray.get(i));
        }
    }
}
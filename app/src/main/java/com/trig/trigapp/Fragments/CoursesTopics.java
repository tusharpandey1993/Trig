package com.trig.trigapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.gson.JsonArray;
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
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.Adapter.CourseTopicAdapter;
import com.trig.trigapp.api.Response.getCourseListRes;

import androidx.recyclerview.widget.GridLayoutManager;
import static com.trig.trigapp.Fragments.DashboardFragment.fromCourses;

public class CoursesTopics extends BaseFragment implements IPresenter, CourseTopicAdapter.ItemListener{

    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    private RecyclerView recyclerView;
    private TextView toolBarText;
    private ImageView backIcon;
    private ViewModel viewModel;
    private ArrayList<getCourseListRes> arrayListOfgetCousreList;
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

        init(mView);

        hitApi();

        return mView;
    }

    private void hitApi() {
        viewModel.callgetCourseTopics(TrigAppPreferences.getUserId(mActivity));
    }

    private void init(View mView) {
        viewModel = new ViewModel(mActivity,this);
        toolBarText = mView.findViewById(R.id.toolBarText);
        backIcon = mView.findViewById(R.id.backIcon);
        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        if(fromCourses){
            toolBarText.setText(Constants.getInstance().Courses);
        } else {
            toolBarText.setText(Constants.getInstance().Assessments);
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
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
    public void onResponseCourseTopicList(JsonArray jsonArray) {
        arrayListOfgetCousreList = new ArrayList<>();
        for(int i =0; i < jsonArray.size(); i++) {
            getCourseListRes getCourseListRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), getCourseListRes.class);
            arrayListOfgetCousreList.add(getCourseListRes);

        }

        CourseTopicAdapter adapter = new CourseTopicAdapter(mActivity, arrayListOfgetCousreList, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(getCourseListRes getCourseListRes) {
        bundle.putInt(Constants.getInstance().item_id, getCourseListRes.getTopic_id());

        if(fromCourses){
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_topics_to_VideoFragment, bundle);
        } else {
            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_topics_to_AssessmentFragment, bundle);
        }
    }
}
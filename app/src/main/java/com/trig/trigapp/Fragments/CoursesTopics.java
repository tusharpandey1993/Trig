package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.Adapter.CourseTopicAdapter;
import com.trig.trigapp.Model.DataModel;
import com.trig.trigapp.R;
import java.util.ArrayList;

import static com.trig.trigapp.Fragments.DashboardFragment.fromCourses;

public class CoursesTopics extends Fragment implements CourseTopicAdapter.ItemListener{

    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    RecyclerView recyclerView;
    ArrayList arrayList;
    TextView toolBarText;
    ImageView backIcon;

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
        init(mView);


        arrayList = new ArrayList();
        arrayList.add(new DataModel("Induction Training",10,10, "#09A9FF"));
        arrayList.add(new DataModel("Functional Training",10,7, "#09A9FF"));
        arrayList.add(new DataModel("Skill Training",10,6, "#09A9FF"));
        arrayList.add(new DataModel("Other Training",10,2, "#09A9FF"));
        arrayList.add(new DataModel("Other Training 2",10,5, "#09A9FF"));
        arrayList.add(new DataModel("Other Training 3",10,9, "#09A9FF"));

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
}
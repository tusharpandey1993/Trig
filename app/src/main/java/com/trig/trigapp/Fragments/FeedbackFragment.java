package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.trig.trigapp.R;

public class FeedbackFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    private TextView toolBarText;
    private Button btn_submit;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_feedback, container, false);

        init(mView);

        return mView;
    }

    private void init(View mView) {
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Feedback");
        btn_submit = mView.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_SuccessFragment);
                break;
            default:
                break;
        }
    }
}

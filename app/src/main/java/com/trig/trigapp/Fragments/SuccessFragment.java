package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.R;

public class SuccessFragment extends Fragment {

    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    private TextView toolBarText, SuccessTitle;
    private final int ANIMATE_DURATION = 1500;
    private LottieAnimationView lottieAnimationView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    public SuccessFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_success, container, false);
        init(mView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                proceed();
            }
        },ANIMATE_DURATION);

        return mView;
    }

    private void init(View mView) {
        lottieAnimationView = mView.findViewById(R.id.lottieAnimationView);
        SuccessTitle = mView.findViewById(R.id.SuccessTitle);

        if(TrigAppPreferences.getSource_To_Desitnation(mActivity) == Constants.getInstance().loginScreen) {
            SuccessTitle.setText("Login Success");
        } else if(TrigAppPreferences.getSource_To_Desitnation(mActivity) == Constants.getInstance().feedback){
            SuccessTitle.setText("Feedback Successfully Submitted!");
        } else {
            SuccessTitle.setText("Success");
        }

        lottieAnimationView.setAnimation("done_tick.json");
        lottieAnimationView.playAnimation();
    }

    public void proceed() {
        if(TrigAppPreferences.getSource_To_Desitnation(mActivity) == Constants.getInstance().loginScreen) {
            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_fragment_success_to_dashboardFragment);
        } else if(TrigAppPreferences.getSource_To_Desitnation(mActivity) == Constants.getInstance().loginScreen) {
            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_fragment_success_to_dashboardFragment);
        } else {
            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_fragment_success_to_dashboardFragment);
        }
    }
}

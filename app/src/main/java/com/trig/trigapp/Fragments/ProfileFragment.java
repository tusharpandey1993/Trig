package com.trig.trigapp.Fragments;

import android.os.Bundle;
import android.view.View;
import com.trig.trigapp.R;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import androidx.navigation.Navigation;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.activity.OnBackPressedCallback;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    FragmentActivity mActivity;
    View mView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.profile, container, false);
        init(mView);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_profile_to_dashboardFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        return mView;
    }

    private void init(View mView) {
    }

}
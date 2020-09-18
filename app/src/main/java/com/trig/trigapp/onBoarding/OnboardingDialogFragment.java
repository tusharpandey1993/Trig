package com.trig.trigapp.onBoarding;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;


import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.R;

import java.util.ArrayList;

public class OnboardingDialogFragment extends DialogFragment {

    private View mView;
    private TextView tv_next;
    private TextView tv_skip;
    private ViewPager view_pager_onboarding;
    Activity mActivity;
    private static final String TAG = "OnboardingDialogFragmen";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        mView = inflater.inflate(R.layout.activity_onboarding, container, false);

        initView(mView);
        bindEvents();
        setData();

        return mView;
    }

    private void setData() {
        ArrayList<OnBoarding> onBoardingArrayList = new ArrayList<>();

        onBoardingArrayList.add(new OnBoarding(R.drawable.trig_logo, "Page 1", "Affiliate Marketing Has Made Businesses Millions And Ordinary People Millionaires.\n" +
                "Affiliate Marketing Mybe Your Next Best Career Move. \n" +
                "― Larry Bussey"));
        onBoardingArrayList.add(new OnBoarding(R.drawable.trig_logo, "Page 2", "Networking isn't how many people you know, it's how many people know you. \n" +
                "― Amit Kalantri, Wealth of Words\n"));
        onBoardingArrayList.add(new OnBoarding(R.drawable.trig_logo, "Page 3", "Freedom of Speech doesn't justify online bullying. Words have power, be careful how you use them. \n" +
                "― Germany Kent"));
        onBoardingArrayList.add(new OnBoarding(R.drawable.trig_logo, "Page 4", "Some people have a lot of time, but no money--\n" +
                "It's because they don't work hard enough.\n" +
                "Some people have a lot of money, but no time--\n" +
                "It's because they don't work smart enough.\n" +
                "The most successful people have both.” \n" +
                "― Bob Sharpe"));

        OnBoardingPagerAdapter adapter = new OnBoardingPagerAdapter(mActivity, onBoardingArrayList);
        view_pager_onboarding.setAdapter(adapter);
    }

    private void bindEvents() {
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrigAppPreferences.setIsShowedShowcaseAppStore(mActivity, true);
               Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_splashFragment_to_loginFragment);
                dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view_pager_onboarding.getCurrentItem() < 3) {
                    view_pager_onboarding.setCurrentItem(view_pager_onboarding.getCurrentItem() + 1);
                } else {
                    TrigAppPreferences.setIsShowedShowcaseAppStore(mActivity, true);
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_splashFragment_to_loginFragment);
                    dismiss();
                }
            }
        });
        view_pager_onboarding.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 3) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        tv_next.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.chip_view_shape_like));
                    }
                    tv_next.setText(getString(R.string.lets_started));
                    tv_next.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                } else {
                    if (tv_next.getText() == getString(R.string.lets_started)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            tv_next.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.chipview_shape_like_stroke));
                        }
                        tv_next.setText(getString(R.string.next));
                        tv_next.setTextColor(ContextCompat.getColor(mActivity, R.color.bg_chipview_likes));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView(View view) {
        tv_next = view.findViewById(R.id.tv_next);
        tv_skip = view.findViewById(R.id.tv_skip);
        view_pager_onboarding = view.findViewById(R.id.view_pager_onboarding);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set click listener
    }

    @NonNull
    @Override
    // To remove the titlebar in dialogfragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            Log.d("ABSDIALOGFRAG", "Exception", e);
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }



}
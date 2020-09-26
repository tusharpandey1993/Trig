package com.trig.trigapp.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.CommonFiles.MobileConnectPermissions;
import com.trig.trigapp.CommonFiles.PermissionCallback;
import com.trig.trigapp.R;

import static com.trig.trigapp.Fragments.DashboardFragment.goToContactTrainer;


public class Contact  extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";
    View mView; FragmentActivity mActivity;
    private TextView mail, mail2, callNumber, callNumber2,toolBarText;
    private ImageView backIcon, contactUsIcon ;
    private RecyclerView contactUsRecyclerView;
    private ConstraintLayout contactTrainerContainer, contactUsContainer;

    public Contact() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.contact, container, false);
        MobileConnectPermissions.init(mActivity);
        init(mView);
        Log.d(TAG, "onCreateView:goToContactTrainer " + goToContactTrainer);
        if(goToContactTrainer) {
            toolBarText.setText("Contact Trainer");
            contactTrainerContainer.setVisibility(View.VISIBLE);
            contactUsContainer.setVisibility(View.GONE);
            contactUsIcon.setBackground(getResources().getDrawable(R.drawable.ic_contact_trainer));
        } else {
            toolBarText.setText("Contact Us");
            contactTrainerContainer.setVisibility(View.GONE);
            contactUsContainer.setVisibility(View.VISIBLE);
            contactUsIcon.setBackground(getResources().getDrawable(R.drawable.ic_contact));
        }
        backButtonHandling();

        return mView;
    }

    private void backButtonHandling() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_contact_to_dashboardFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_contact_to_dashboardFragment);
            }
        });
    }

    private void init(View mView) {
        mail = mView.findViewById(R.id.mail);
        mail2 = mView.findViewById(R.id.mail2);
        callNumber = mView.findViewById(R.id.callNumber);
        callNumber2 = mView.findViewById(R.id.callNumber2);
        contactUsIcon = mView.findViewById(R.id.contactUsIcon);
        contactTrainerContainer = mView.findViewById(R.id.contactTrainerContainer);
        contactUsContainer = mView.findViewById(R.id.contactUsContainer);

        mail.setOnClickListener(this);
        mail2.setOnClickListener(this);
        callNumber.setOnClickListener(this);
        callNumber2.setOnClickListener(this);
        backIcon = mView.findViewById(R.id.backIcon);
        toolBarText = mView.findViewById(R.id.toolBarText);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mail:
                sendEmail();
            break;
            case R.id.mail2:
                sendEmail();
            break;
            case R.id.callNumber:
                checkPermissionAndThenLoad();
            break;
            case R.id.callNumber2:
                checkPermissionAndThenLoad();
            break;
        }
    }

    private boolean checkPermissionAndThenLoad() {
        if (MobileConnectPermissions.checkPermission(Manifest.permission.CALL_PHONE)) {
            callContact();
        } else {
            if (MobileConnectPermissions.shouldShowRequestPermissionRationale(mActivity, android.Manifest.permission.CALL_PHONE)) {
                MobileConnectPermissions.askForPermission(mActivity,
                        new String[]{android.Manifest.permission.CALL_PHONE}, permissionReadstorageCallback);
            } else {
                MobileConnectPermissions.askForPermission(mActivity, new String[]{
                                android.Manifest.permission.CALL_PHONE
                        },
                        permissionReadstorageCallback);
            }
        }
        return false;
    }

    private final PermissionCallback permissionReadstorageCallback = new PermissionCallback() {
        @Override
        public void permissionGranted() {
            callContact();
        }

        @Override
        public void permissionRefused() {
            checkPermissionAndThenLoad();
        }
    };

    public void sendEmail() {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"info@triggroup.in"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        mActivity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    public void callContact() {
        Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "022 66783333"));
        mActivity.startActivity(intent2);
    }
}

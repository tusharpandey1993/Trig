package com.trig.trigapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.trig.trigapp.R;


public class Contact  extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";
    FragmentActivity mActivity;
    View mView;
    private TextView mail, mail2, callNumber, callNumber2;

    public Contact() {
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
        mView = inflater.inflate(R.layout.contact, container, false);

        init(mView);
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
    }

    private void init(View mView) {
        mail = mView.findViewById(R.id.mail);
        mail2 = mView.findViewById(R.id.mail2);
        callNumber = mView.findViewById(R.id.callNumber);
        callNumber2 = mView.findViewById(R.id.callNumber2);

        mail.setOnClickListener(this);
        mail2.setOnClickListener(this);
        callNumber.setOnClickListener(this);
        callNumber2.setOnClickListener(this);
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
                callContact();
            break;
            case R.id.callNumber2:
                callContact();
            break;
        }
    }

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

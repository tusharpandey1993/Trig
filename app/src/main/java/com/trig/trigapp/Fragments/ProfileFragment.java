package com.trig.trigapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.trig.trigapp.R;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.activity.OnBackPressedCallback;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";
    FragmentActivity mActivity;
    View mView;
    private ImageView backgroundImageChange2;
    public static ImageView profileImg;
    ImageView backIcon;
    TextView toolBarText;

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

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_profile_to_dashboardFragment);
            }
        });

        return mView;
    }

    private void init(View mView) {
        backgroundImageChange2 = mView.findViewById(R.id.backgroundImageChange2);
        profileImg = mView.findViewById(R.id.profileImg);
        backIcon = mView.findViewById(R.id.backIcon);
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("User Profile");
        backgroundImageChange2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backgroundImageChange2:
                selectImage(mActivity);
            break;

        }
    }

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


}

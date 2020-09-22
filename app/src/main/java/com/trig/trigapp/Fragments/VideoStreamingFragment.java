package com.trig.trigapp.Fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoStreamingFragment extends Fragment {

    private static final String TAG = "VideoStreamingFragment";

    private View mView;
    private FragmentActivity mActivity;
    private TextView toolBarText;
    private ImageView backIcon;
    private SimpleExoPlayer player;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    public VideoStreamingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_video_streaming, container, false);

        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Video");
        backIcon = mView.findViewById(R.id.backIcon);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_VideoStreamingFrag_to_VideoListFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(mActivity, callback);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_VideoStreamingFrag_to_VideoListFragment);
            }
        });
        if(Utility.getInstance().isNetworkAvailable(mActivity)) {
            startVideoStreaming(mActivity);
        } else {
            Utility.getInstance().showSnackbar(mView, mActivity.getResources().getString(R.string.no_internet_message));
        }
        return mView;
    }

    private void startVideoStreaming(Context mActivity) {
        // Estimates bandwidth
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);

        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // Controls buffering of media
        LoadControl loadControl = new DefaultLoadControl();

        player = ExoPlayerFactory.newSimpleInstance(mActivity, trackSelector, loadControl);

        SimpleExoPlayerView simpleExoPlayerView = (SimpleExoPlayerView) mView.findViewById(R.id.video_player);

        // Bind the player to the view.
        simpleExoPlayerView.setPlayer(player);

        // DataSource instance through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mActivity,
                Util.getUserAgent(mActivity, getString(R.string.app_name)));

        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        String mp4VideoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

        Uri mp4VideoUri = Uri.parse(mp4VideoUrl);

        // MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri,
                dataSourceFactory, extractorsFactory, null, null);

        // Prepare the player with the source.
        player.prepare(videoSource);

        // Start to play when player is ready
        player.setPlayWhenReady(true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            if (player != null) {
                player.stop();
                player.release();
            }
        } catch (Exception e) {
            Log.e(TAG, "onDestroyView: exception" + e.getMessage());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            if (player != null) {
                player.stop();
                player.release();
            }
        } catch (Exception e) {
            Log.e(TAG, "onDestroyView: exception" + e.getMessage());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            if (player != null) {
                player.stop();
                player.release();
            }
        } catch (Exception e) {
            Log.e(TAG, "onDestroyView: exception" + e.getMessage());
        }
    }
}

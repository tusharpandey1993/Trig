package com.trig.trigapp.Fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private View mView;
    private FragmentActivity mActivity;
    public TextView toolBarText;


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

        // TrackSelector is used When a piece of media contains multiple tracks of a given type,
        // for example multiple video tracks in different qualities or multiple audio tracks in
        // different languages
        // Adaptative: selected track is updated to be the one of highest quality given the
        // current network conditions and the state of the buffer
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);

        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // Controls buffering of media
        LoadControl loadControl = new DefaultLoadControl();

        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(mActivity, trackSelector, loadControl);

        SimpleExoPlayerView simpleExoPlayerView = (SimpleExoPlayerView) mView.findViewById(R.id.video_player);

        // Bind the player to the view.
        simpleExoPlayerView.setPlayer(player);

        // DataSource instance through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mActivity,
                Util.getUserAgent(mActivity, getString(R.string.app_name)));

        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        String mp4VideoUrl = "https://www.radiantmediaplayer.com/media/bbb-360p.mp4";

        Uri mp4VideoUri = Uri.parse(mp4VideoUrl);

        // MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri,
                dataSourceFactory, extractorsFactory, null, null);

        // Prepare the player with the source.
        player.prepare(videoSource);

        // Start to play when player is ready
        player.setPlayWhenReady(true);

    }


}

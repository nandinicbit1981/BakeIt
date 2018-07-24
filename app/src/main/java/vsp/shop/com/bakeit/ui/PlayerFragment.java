package vsp.shop.com.bakeit.ui;


import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.model.Step;
import vsp.shop.com.bakeit.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends android.support.v4.app.Fragment {
    List<Step> steps;
    int currentStep;

    @BindView(R.id.next_step)
    ImageButton nextStep;

    @BindView(R.id.prev_step)
    ImageButton prevStep;

    @BindView(R.id.step_number)
    TextView stepNumber;

    long playerPostion;
    public PlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, view);

        if(getArguments() != null) {
            steps = getArguments().getParcelableArrayList(Constant.STEPS);
            currentStep = getArguments().getInt(Constant.CURRENTSTEP);
        } else {
            steps = getActivity().getIntent().getParcelableArrayListExtra(Constant.STEPS);
            currentStep = getActivity().getIntent().getIntExtra(Constant.CURRENTSTEP, 0);
        }


        if(savedInstanceState != null && Long.valueOf(savedInstanceState.getLong(Constant.CURRENTPOSITION)) != null) {
            playerPostion = savedInstanceState.getLong(Constant.CURRENTPOSITION);
        }
        updateUi();
        return view;
    }


    @BindView(R.id.step_description)
    TextView stepDescriptionTV;

    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView simpleExoPlayer;
    private SimpleExoPlayer mExoPlayer;

    /**
     * Initialize ExoPlayer.
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {

        if(mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
        }
        if (mediaUri.toString() != "") {
            // Create an instance of the ExoPlayer.

            simpleExoPlayer.setPlayer(mExoPlayer);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getActivity(), "Bake App");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);

            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            if(playerPostion > -1) {
                mExoPlayer.seekTo(playerPostion);
            }
        }
    }

    @OnClick(R.id.prev_step)
    public void prevStepClick() {
        if(currentStep > 0) {
            currentStep -= 1;
            updateUi();
        }
    }

    @OnClick(R.id.next_step)
    public void nextStepClick() {
        if(currentStep < steps.size() - 2) {
            currentStep += 1;
            updateUi();
        }
    }

    public void updateUi() {
        Step step = steps.get(currentStep);
        stepDescriptionTV.setText(step.getDescription());
        initializePlayer(Uri.parse(step.getVideoURL()));
        stepNumber.setText(String.valueOf(step.getId()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        playerPostion =  mExoPlayer.getCurrentPosition();
        outState.putLong(Constant.CURRENTPOSITION, playerPostion);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playerPostion = mExoPlayer.getCurrentPosition();
        releasePlayer();
    }

    public void releasePlayer() {
        if(mExoPlayer != null) {
            mExoPlayer.stop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        playerPostion = mExoPlayer.getCurrentPosition();
        if(mExoPlayer != null) {
            mExoPlayer.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }
}

/**
 * 作者：developerHaoz
 * 链接：http://www.jianshu.com/p/6bbb51ac4938
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
package hunter.com.myuiproject.fragments.uis.soundrecord;

import android.app.Dialog;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.concurrent.TimeUnit;

import hunter.com.myuiproject.R;

/**
 * Description 播放录音的
 * Created by hcgan on 2017/8/23.
 */

public class PlaybackDialogFragment extends DialogFragment {

    private static final String LOG_TAG = "PlaybackFragment";

    private static final String ARG_ITEM = "recording_item";
    private RecordingItem item;

    private Handler mHandler = new Handler();

    private MediaPlayer mMediaPlayer = null;

    private SeekBar mSeekBar = null;
    private FloatingActionButton mPlayButton = null;
    private TextView mCurrentProgressTextView = null;
    private TextView mFileNameTextView = null;
    private TextView mFileLengthTextView = null;

    //stores whether or not the mediaplayer is currently playing audio
    private boolean isPlaying = false;

    //stores minutes and seconds of the length of the file.
    long minutes = 0;
    long seconds = 0;
    private static long mFileLength = 0;

    public static PlaybackDialogFragment newInstance(RecordingItem item){
        PlaybackDialogFragment fragment = new PlaybackDialogFragment();
        Bundle b = new Bundle();
        b.putParcelable(ARG_ITEM, item);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = getArguments().getParcelable(ARG_ITEM);
        long itemDuration = item.getLength();

        mFileLength = itemDuration;
        minutes = TimeUnit.MILLISECONDS.toMinutes(itemDuration);
        seconds = TimeUnit.MILLISECONDS.toSeconds(itemDuration)
                - TimeUnit.MINUTES.toSeconds(minutes);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_media_playback, null);
        initView(view);



        builder.setView(view);
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return builder.create();
    }

    private void initView(View view){
        mFileNameTextView = (TextView) view.findViewById(R.id.file_name_text_view);
        mFileLengthTextView = (TextView) view.findViewById(R.id.file_length_text_view);
        mCurrentProgressTextView = (TextView) view.findViewById(R.id.current_progress_text_view);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);

        ColorFilter filter = new LightingColorFilter
                (getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimary));

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

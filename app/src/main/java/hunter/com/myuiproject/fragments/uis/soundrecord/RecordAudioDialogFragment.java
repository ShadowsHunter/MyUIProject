/**
 * 作者：developerHaoz
 * 链接：http://www.jianshu.com/p/6bbb51ac4938
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
package hunter.com.myuiproject.fragments.uis.soundrecord;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.io.File;

import hunter.com.myuiproject.R;

/**
 * Description 录音弹框界面
 * Created by hcgan on 2017/8/23.
 */

public class RecordAudioDialogFragment extends DialogFragment {
    private static final String TAG = "RecordAudioDialogFragme";

    private static final String MAXDURATION = "MaxDuration";

    private boolean mStartRecording = true;

    long timeWhenPaused = 0;

    private FloatingActionButton mFabRecord;
    private Chronometer mChronometerTime;
    private ImageView mIvClose;

    private OnAudioCancelListener mListener;

    public static RecordAudioDialogFragment newInstance(int maxDuration){
        RecordAudioDialogFragment fragment = new RecordAudioDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MAXDURATION,maxDuration);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_record_audio,null);
        initView(v);

        mFabRecord.setColorNormal(getResources().getColor(R.color.colorPrimary));
        mFabRecord.setColorPressed(getResources().getColor(R.color.colorPrimaryDark));

        mFabRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity()
                            , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1);
                }else{
                    onRecord(mStartRecording);
                    mStartRecording = !mStartRecording;
                }

            }
        });

        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCancel();
            }
        });
        builder.setCancelable(false);
        builder.setView(v);
        return builder.create();
    }

    private void initView(View view){
        mChronometerTime = (Chronometer) view.findViewById(R.id.record_audio_chronometer_time);
        mFabRecord = (FloatingActionButton) view.findViewById(R.id.record_audio_fab_record);
        mIvClose = (ImageView) view.findViewById(R.id.record_audio_iv_close);
    }

    private void onRecord(boolean start){
        Intent intent = new Intent(getActivity(), RecordingService.class);

        if(start){
            mFabRecord.setImageResource(R.mipmap.ic_media_stop);
            Toast.makeText(getActivity(), "开始录音...", Toast.LENGTH_SHORT).show();
            File file = new File(Environment.getExternalStorageDirectory() + "/SoundRecorder");
            if(!file.exists()){
                file.mkdirs();
            }

            mChronometerTime.setBase(SystemClock.elapsedRealtime());
            mChronometerTime.start();
            int maxDuration = getArguments().getInt(MAXDURATION);
            intent.putExtra("MaxDuration",maxDuration);
            getActivity().startService(intent);
        }else{
            mFabRecord.setImageResource(R.mipmap.ic_mic_white_36dp);

            mChronometerTime.stop();
            timeWhenPaused = 0 ;
            Toast.makeText(getActivity(), "录音结束...", Toast.LENGTH_SHORT).show();

            getActivity().stopService(intent);
            //allow the screen to turn off again once recording is finished
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    public void setOnCancelListener(OnAudioCancelListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    onRecord(mStartRecording);
                }
                break;
        }
    }


    public interface OnAudioCancelListener {
        void onCancel();
    }
}

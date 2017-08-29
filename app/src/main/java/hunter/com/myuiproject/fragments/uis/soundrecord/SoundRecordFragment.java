package hunter.com.myuiproject.fragments.uis.soundrecord;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hunter.com.myuiproject.R;
import hunter.com.myuiproject.fragments.BaseFragment;

/**
 * Description
 * Created by hcgan on 2017/8/23.
 */

public class SoundRecordFragment extends BaseFragment {
    private View v;
    private Button btn_record,btn_play;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_soundrecord,null);
        super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    @Override
    protected void initView() {
        btn_record = (Button)v.findViewById(R.id.btn_record);
        btn_play = (Button)v.findViewById(R.id.btn_play);

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RecordAudioDialogFragment fragment = RecordAudioDialogFragment.newInstance(30);
                fragment.show(getActivity().getSupportFragmentManager(), RecordAudioDialogFragment.class.getSimpleName());
                fragment.setOnCancelListener(new RecordAudioDialogFragment.OnAudioCancelListener() {
                    @Override
                    public void onCancel() {
                        fragment.dismiss();
                    }
                });
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordingItem recordingItem = new RecordingItem();
                SharedPreferences sharePreferences = getActivity().getSharedPreferences("sp_name_audio", getActivity().MODE_PRIVATE);
                final String filePath = sharePreferences.getString("audio_path", "");
                long elpased = sharePreferences.getLong("elpased", 0);
                recordingItem.setFilePath(filePath);
                recordingItem.setLength((int) elpased);
                PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
                fragmentPlay.show(getActivity().getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initData() {

    }
}

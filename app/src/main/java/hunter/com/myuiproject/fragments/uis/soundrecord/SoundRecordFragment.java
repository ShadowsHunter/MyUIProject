package hunter.com.myuiproject.fragments.uis.soundrecord;

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
        super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.fragment_soundrecord,null);
        return v;
    }

    @Override
    protected void initView() {
        btn_record = getView(v,R.id.btn_record);
        btn_play = getView(v,R.id.btn_play);
    }

    @Override
    protected void initData() {

    }
}

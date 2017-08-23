/**
 * 作者：developerHaoz
 * 链接：http://www.jianshu.com/p/6bbb51ac4938
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
package hunter.com.myuiproject.fragments.uis.soundrecord;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.File;

import hunter.com.myuiproject.R;

/**
 * Description
 * Created by hcgan on 2017/8/23.
 */

public class RecordingService extends Service {
    private String mFileName;
    private String mFilePath;

    private MediaRecorder mRecorder;

    private long mStartingTimeMillis;
    private long mElapsedMillis;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if(mRecorder!=null){
            stopRecording();
        }
        super.onDestroy();
    }

    // 开始录音
    private void startRecording(){
        setFileNameAndPath();

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//录音文件保存的格式，这里保存为 mp4
        mRecorder.setOutputFile(mFilePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);


    }

    //设置录音文件的名字和保存路径
    private void setFileNameAndPath(){
        int count = 0;
        File f;

        do {
            count++;
            mFileName = getString(R.string.default_file_name)
                    + "_" + (System.currentTimeMillis()) + ".mp4";
            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFilePath += "/SoundRecorder/" + mFileName;
            f = new File(mFilePath);
        } while (f.exists() && !f.isDirectory());
    }

    // 停止录音
    public void stopRecording() {

    }
}

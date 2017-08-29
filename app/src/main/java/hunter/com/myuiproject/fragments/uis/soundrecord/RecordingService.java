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
import java.io.IOException;
import java.util.TimerTask;

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

    private TimerTask mIncrementTimerTask = null;

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
        int maxDuration = intent.getIntExtra("MaxDuration",0);
        startRecording(maxDuration);
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
    private void startRecording(int maxDuration){
        setFileNameAndPath();

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//录音文件保存的格式，这里保存为 mp4
        mRecorder.setOutputFile(mFilePath);
//        if(maxDuration!=0){//如果不为0，设置最大时长
//            mRecorder.setMaxDuration(maxDuration);
//        }
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setAudioChannels(1);
        // 设置录音文件的清晰度
        mRecorder.setAudioSamplingRate(44100);
        mRecorder.setAudioEncodingBitRate(192000);

        try
        {
            mRecorder.prepare();
            mRecorder.start();
            mStartingTimeMillis = System.currentTimeMillis();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


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
        mRecorder.stop();
        mElapsedMillis = System.currentTimeMillis() - mStartingTimeMillis;
        mRecorder.release();

        getSharedPreferences("sp_name_audio", MODE_PRIVATE)
                .edit()
                .putString("audio_path", mFilePath)
                .putLong("elpased", mElapsedMillis)
                .apply();
        if (mIncrementTimerTask != null) {
            mIncrementTimerTask.cancel();
            mIncrementTimerTask = null;
        }

        mRecorder = null;


    }
}

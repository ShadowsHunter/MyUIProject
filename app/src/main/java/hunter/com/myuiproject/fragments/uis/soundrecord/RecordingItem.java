/**
 * 作者：developerHaoz
 * 链接：http://www.jianshu.com/p/6bbb51ac4938
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
package hunter.com.myuiproject.fragments.uis.soundrecord;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description 录音文件的实体类
 * Created by hcgan on 2017/8/23.
 */

public class RecordingItem implements Parcelable {
    private String mName;
    private String mFilePath;
    private int mId;
    private int mLength;
    private long mTime;

    public RecordingItem() {
    }

    public RecordingItem(Parcel in) {
        mName = in.readString();
        mFilePath = in.readString();
        mId = in.readInt();
        mLength = in.readInt();
        mTime = in.readLong();
    }

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        mLength = length;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public static final Creator<RecordingItem> CREATOR = new Creator<RecordingItem>() {
        public RecordingItem createFromParcel(Parcel in) {
            return new RecordingItem(in);
        }

        public RecordingItem[] newArray(int size) {
            return new RecordingItem[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mLength);
        dest.writeLong(mTime);
        dest.writeString(mFilePath);
        dest.writeString(mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

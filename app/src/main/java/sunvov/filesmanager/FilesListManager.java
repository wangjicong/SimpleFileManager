package sunvov.filesmanager;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class FilesListManager {
    private static final String TAG = FilesListManager.class.getName();
    public static FilesListManager mInstance;


    private List<FileInfo> mSaveFilesList = new ArrayList<>();

    public static synchronized FilesListManager getInstance(){
        if (null==mInstance){
            mInstance = new FilesListManager();
        }
        return mInstance;
    };

    public List<FileInfo> getmSaveFilesList() {
        return mSaveFilesList;
    }

    public void setmSaveFilesList(List<FileInfo> mSaveFilesList) {
        this.mSaveFilesList = mSaveFilesList;
    }

    public void addMountList(List<MoutManager.MountPoint> mMountPathList) {
        mSaveFilesList.clear();
        for (MoutManager.MountPoint mountPoint:mMountPathList){
            Log.d(TAG,"wangjicong 11");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(mountPoint.mPath);
            fileInfo.setFilename(mountPoint.mFileName);
            fileInfo.setmIsExternal(mountPoint.mIsExternal);
            fileInfo.setmIsMounted(mountPoint.mIsMounted);
            mSaveFilesList.add(fileInfo);
        }
        Log.d(TAG,"wangjicong list is "+mSaveFilesList);
    }
}

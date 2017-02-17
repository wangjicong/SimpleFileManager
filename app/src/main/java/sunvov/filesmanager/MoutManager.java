package sunvov.filesmanager;

import android.content.Context;
import android.os.storage.StorageManager;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import wjc.storage.tools.StorageInfo;
import wjc.storage.tools.StorageUtils;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class MoutManager {
    private static final String TAG = MoutManager.class.getName();
    public static final String ROOT_PATH = "ROOT PATH";
    private static MoutManager mInstance;
    private static List<MountPoint> mMountPathList = new ArrayList<>();
    private StorageManager mStorageManager;
    public MoutManager(Context context) {
        init(context);
    }


    private void init(Context context) {
        mMountPathList.clear();
        mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);

        List<StorageInfo> storageInfoList = StorageUtils.listAvaliableStorage(context);

        if (null!=storageInfoList){
            for (StorageInfo info:storageInfoList){
                // TODO: 2017/2/15 0015
                MountPoint moutPoint = new MountPoint();
                moutPoint.mDescription = info.getmDescription();
                moutPoint.mIsMounted  = info.getmState().equals("mounted");
                moutPoint.mPath = info.getmPathName();
                moutPoint.mIsExternal  = info.ismRemovable();
                mMountPathList.add(moutPoint);
            }
        }

    }

    public static synchronized MoutManager getInstance(Context context){
        if (null==mInstance){
            mInstance = new MoutManager(context);
        }
        return mInstance;
    }

    public static void updateMountPointSpaceInfo() {
        for (MountPoint mountPoint:mMountPathList){
            if (mountPoint.mIsMounted){
                File file = new File(mountPoint.mPath);
                mountPoint.mFreeSpace = file.getFreeSpace();
                mountPoint.mTotalSpace = file.getTotalSpace();
            }

            Log.d(TAG,"free space is "+mountPoint.mFreeSpace + " total spce is "+mountPoint.mTotalSpace);
        }
    }


    public static boolean isRootPath(String mPath) {
        return ROOT_PATH.equals(mPath);
    }

    private static class MountPoint {
        String mDescription;
        String mPath;
        boolean mIsExternal;
        boolean mIsMounted;
        long mMaxFileSize;
        long mFreeSpace;
        long mTotalSpace;
    }
}

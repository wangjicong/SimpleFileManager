package sunvov.filesmanager;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class FileManagerService extends Service {
    private static final String TAG = FileManagerService.class.getName();
    private ServiceBinder mBinder;
    private FileUploadListener mListener;
    private FilesListManager mFileListManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void loadFileList(String current_path) {
        LoadFilesListTask task = new LoadFilesListTask(getApplicationContext(),mListener,current_path);
        task.execute();
    }

    public class ServiceBinder extends Binder{
        public FileManagerService getFileManagerServiceInstance(){
            return FileManagerService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new ServiceBinder();
        mFileListManager = new FilesListManager();
    }

    public interface FileUploadListener{
        public void onTaskUploadlistFinished();
    }

    public void setUploadListLisenter(FileUploadListener listLisenter){
        if (null!=listLisenter){
            mListener = listLisenter;
        }
    }

    public FilesListManager getmFileListManager() {
        return mFileListManager;
    }

    public void setmFileListManager(FilesListManager mFileListManager) {
        this.mFileListManager = mFileListManager;
    }
}

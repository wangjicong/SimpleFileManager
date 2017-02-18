package sunvov.filesmanager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class LoadFilesListTask extends AsyncTask<Void,Void,Integer> {
    private static final String TAG = LoadFilesListTask.class.getName();
    private String mPath;
    private FileManagerService.FileUploadListener mListener;
    private Context mContext;

    public LoadFilesListTask(Context context, FileManagerService.FileUploadListener mListener, String current_path) {
        this.mPath = current_path;
        this.mListener = mListener;
        this.mContext = context;
        MoutManager.getInstance(mContext);
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        if (MoutManager.isRootPath(mPath)){
            Log.d(TAG,"wangjicong xxxxx");
            MoutManager.getInstance(mContext).updateMountPointSpaceInfo();
            return 1;
        }
        return 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (null!=mListener){
            mListener.onTaskUploadlistFinished();
        }
    }
}

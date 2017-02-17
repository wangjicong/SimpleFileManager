package sunvov.filesmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class FilesListManager {
    private static final String TAG = FilesListManager.class.getName();

    private List<FileInfo> mSaveFilesList = new ArrayList<>();


    public List<FileInfo> getmSaveFilesList() {
        return mSaveFilesList;
    }

    public void setmSaveFilesList(List<FileInfo> mSaveFilesList) {
        this.mSaveFilesList = mSaveFilesList;
    }
}

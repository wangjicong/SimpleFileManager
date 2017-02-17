package sunvov.filesmanager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class FileListAdapter extends BaseAdapter {
    private static final String TAG = FileListAdapter.class.getName();
    private FileManagerService mService;
    public FileListAdapter(String name, FileManagerService mService, String current_path) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}

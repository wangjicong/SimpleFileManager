package sunvov.filesmanager;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class FileListAdapter extends BaseAdapter {
    private static final String TAG = FileListAdapter.class.getName();
    private FilesListManager mFileListManager;
    private List<FileInfo> mShowList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public FileListAdapter(MainActivity name, FilesListManager listManager, String current_path) {
        this.mFileListManager = listManager;
        this.mShowList = listManager.getmSaveFilesList();
        this.mContext = name.getApplicationContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void notifyDataSetChanged() {
        mShowList.clear();
        mShowList = mFileListManager.getmSaveFilesList();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFileListManager.getmSaveFilesList().size();
    }

    @Override
    public Object getItem(int i) {
        return mShowList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view==null){
            viewHolder = new ViewHolder();

            View v = mLayoutInflater.inflate(R.layout.filelist,viewGroup,false);
            viewHolder.icon = (ImageView)v.findViewById(R.id.icon);
            viewHolder.name = (TextView)v.findViewById(R.id.name);
            viewHolder.size = (TextView)v.findViewById(R.id.size);

            viewHolder.update();
            view = v;
            view.setTag(viewHolder);

        }else {

            viewHolder = (ViewHolder) view.getTag();
            viewHolder.update();
        }
        return view;
    }


    private class ViewHolder{
        ImageView icon;
        TextView name;
        TextView size;

        public void setIcon() {
            this.icon.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
        }


        public void setName() {
            this.name = name;
        }


        public void setSize() {
            this.size = size;
        }

        public void update() {
            setIcon();
            setName();
            setSize();
        }
    }
}

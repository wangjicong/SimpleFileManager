package sunvov.filesmanager;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import wjc.storage.tools.FileUtils;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class FileListAdapter extends BaseAdapter {
    private static final String TAG = FileListAdapter.class.getName();
    private FilesListManager mFileListManager;
    private List<FileInfo> mShowList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String mPath;

    public FileListAdapter(MainActivity name, FilesListManager listManager, String current_path) {
        this.mShowList = listManager.getmSaveFilesList();
        this.mContext = name.getApplicationContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mPath = current_path;
        this.mFileListManager = listManager;
    }


    public void dataChanged() {
        mShowList = mFileListManager.getmSaveFilesList();
        Log.d(TAG,"wangjicong mShowList.size is "+mShowList.size());
    }

    @Override
    public void notifyDataSetChanged() {
        dataChanged();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mShowList.size();
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

            viewHolder.update(i);
            view = v;
            view.setTag(viewHolder);

        }else {

            viewHolder = (ViewHolder) view.getTag();
            viewHolder.update(i);
        }
        return view;
    }


    private class ViewHolder{
        ImageView icon;
        TextView name;
        TextView size;

        public void setIcon(int position) {
            FileInfo fileInfo = mShowList.get(position);
            Log.d(TAG,"wangjicong mpath is "+mPath);
            if (MoutManager.isMoutPoint(fileInfo.getPath())){
                if (fileInfo.ismIsExternal()){
                    this.icon.setBackground(mContext.getResources().getDrawable(R.drawable.sdcard));
                } else {
                    this.icon.setBackground(mContext.getResources().getDrawable(R.drawable.phone_storage));
                }
            } else {
                if (fileInfo.getIsDir()){
                    this.icon.setBackground(mContext.getResources().getDrawable(R.drawable.fm_folder));
                } else {
                    this.icon.setBackground(mContext.getResources().getDrawable(R.drawable.fm_txt));
                }
            }
        }


        public void setName(int position) {
            FileInfo fileInfo = mShowList.get(position);
            if (MoutManager.isMoutPoint(fileInfo.getPath())){
                if (fileInfo.ismIsExternal()){
                    this.name.setText(mContext.getString(R.string.external));
                }else {
                    this.name.setText(mContext.getString(R.string.internal));
                }
            }else{
                this.name.setText(fileInfo.getFilename());
            }
        }


        public void setSize(int position) {
            FileInfo fileInfo = mShowList.get(position);
            if (MoutManager.isMoutPoint(fileInfo.getPath())){
                StringBuffer stringBuffer = new StringBuffer();
                File file = new File(fileInfo.getPath());
                String total_size = FileUtils.sizeToString(file.getTotalSpace());
                String free_size  = FileUtils.sizeToString(new File(fileInfo.getPath()).getFreeSpace());
                String str = String.format(mContext.getResources().getString(R.string.total_size_hint),total_size);
                stringBuffer.append(str +"\n");
                str = String.format(mContext.getResources().getString(R.string.free_size_hint),free_size);
                stringBuffer.append(str);
                this.size.setText(stringBuffer.toString());
            }else{
                this.name.setText(fileInfo.getFilename());
            }
        }

        public void update(int position) {
            setIcon(position);
            setName(position);
            setSize(position);
        }
    }
}

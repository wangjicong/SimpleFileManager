package sunvov.filesmanager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String tag = MainActivity.class.getName();
    private ListView mDeviceList;
    private TextView mEmptyView;
    private FileManagerService mService;

    private ServiceConnection mServiceConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ((FileManagerService.ServiceBinder) iBinder).getFileManagerServiceInstance();
            mService.setUploadListLisenter(new FileManagerService.FileUploadListener() {
                @Override
                public void onTaskUploadlistFinished() {
                    FileListAdapter adapter = (FileListAdapter) mDeviceList.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            });

            serviceConnect();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDeviceList = (ListView) findViewById(R.id.device_list);
        mEmptyView = (TextView) findViewById(R.id.list_empty);
        initActionBar();
        initDeviceList();

        bindFileService();

        Log.d(tag,"Android version info "+ Build.VERSION.SDK_INT);
    }

    private void bindFileService() {
        Intent intent = new Intent(MainActivity.this, FileManagerService.class);
        bindService(intent, mServiceConnect, Context.BIND_AUTO_CREATE);

    }

    private void serviceConnect() {
        String current_path  = getCurrentPath();
        FileListAdapter adapter = new FileListAdapter(MainActivity.class.getName(),mService,current_path);
        mDeviceList.setAdapter(adapter);
        mService.loadFileList(current_path);
    }

    private void initDeviceList() {
        if (null != mDeviceList) {
            mDeviceList.setEmptyView(mEmptyView);
        }
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();

        if (null != actionBar) {
            Log.d(tag, "action is not null");
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customActionBar = layoutInflater.inflate(R.layout.actionbar, null);

            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
                    ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME
                            | ActionBar.DISPLAY_SHOW_TITLE);

            actionBar.setCustomView(customActionBar);
        }
    }

    private String getCurrentPath(){

        return MoutManager.ROOT_PATH;
    }
}

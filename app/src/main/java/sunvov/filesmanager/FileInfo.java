package sunvov.filesmanager;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class FileInfo {
    private static final String TAG = FileInfo.class.getName();

    private String filename;
    private String path;
    private String absolutePath;
    private boolean isDir;
    private String type;
    private long last_modify_time;
    private long size;
    private boolean mIsExternal;
    private boolean mIsMounted;
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public boolean getIsDir() {
        return isDir;
    }

    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(long last_modify_time) {
        this.last_modify_time = last_modify_time;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean ismIsExternal() {
        return mIsExternal;
    }

    public void setmIsExternal(boolean mIsExternal) {
        this.mIsExternal = mIsExternal;
    }

    public boolean ismIsMounted() {
        return mIsMounted;
    }

    public void setmIsMounted(boolean mIsMounted) {
        this.mIsMounted = mIsMounted;
    }
}

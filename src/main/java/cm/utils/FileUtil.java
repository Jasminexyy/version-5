package cm.utils;

import java.io.File;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/23
 */
public class FileUtil {
    /**
     * @param dirFile
     * @return boolean
     */
    public static boolean deleteFile(File dirFile) {
        if (!dirFile.exists()) { return false; }
        if (dirFile.isFile()) { return dirFile.delete();
        } else {
            for (File file : dirFile.listFiles()) {
                deleteFile(file);
            }
        }
        return dirFile.delete();
    }
}

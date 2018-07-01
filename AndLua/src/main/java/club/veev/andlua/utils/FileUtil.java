package club.veev.andlua.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import club.veev.andlua.AndLua;

/**
 * Created by Veev on 2018/7/1.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    FileUtil
 */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static String getLuaFilesPath() {
        String f = getFilesPath() + "/lua/files";
        checkFolder(f);
        return f;
    }

    public static String getTestFolder() {
        String f = Environment.getExternalStorageDirectory() + "/AndLua";
        checkFolder(f);
        return f;
    }

    /**
     * 获取APP files路径
     */
    private static String getFilesPath() {
        return AndLua.getContext().getFilesDir().getPath();
    }

    private static boolean checkFolder(String folder) {
        File file = new File(folder);
        return file.exists() || file.mkdirs();
    }

    /**
     * 新建文件
     *
     * @param fileName String 包含路径的文件名 如:E:\phsftp\src\123.txt
     * @param content  String 文件内容
     */
    public static void createNewFile(String fileName, String content) {
        createNewFile(fileName, content, "\n");
    }

    /**
     * 新建文件
     *
     * @param fileName String 包含路径的文件名 如:E:\phsftp\src\123.txt
     * @param content  String 文件内容
     * @param endWith  String 结束符
     */
    public static void createNewFile(String fileName, String content, String endWith) {
        try {
            File filePath = new File(fileName);
            if (!filePath.exists()) {
                boolean b = filePath.createNewFile();
            }
            FileWriter fw = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(content);
            if (!TextUtils.isEmpty(endWith)) {
                if (TextUtils.equals(endWith, "\n")) {
                    pw.println();
                } else {
                    pw.print(endWith);
                }
            }
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFileToString(String filePath) {
        return readFileToString(filePath, "utf-8");
    }

    /**
     * 读文件并转换为String
     * 因是一次性读取，建议只用作读取小文件，否则有性能问题
     *
     * @param filePath
     * @param charset
     * @return
     */
    public static String readFileToString(String filePath, String charset) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                int size = (int) file.length();
                byte[] data = new byte[size];
                inputStream.read(data, 0, size);
                String string = new String(data, charset);
                return string;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}

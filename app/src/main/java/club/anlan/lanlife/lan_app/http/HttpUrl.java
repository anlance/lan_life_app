package club.anlan.lanlife.lan_app.http;

/**
 * http url 请求
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 15:08
 */
public class HttpUrl {

    public static final int PAGE_SIZE = 10;
    public static final String BASE_URl = "http://182.254.215.204:8080";

    // ----------------------------- 文件 -----------------------------------//
    // 获取文件
    public static final String GET_FILE = "/basic/file/getFiles";
    // 上传文件
    public static final String UPLOAD_FILE = "/basic/file/uploadFile";
    // 下载文件
    public static final String DOWNLOAD_FILE = "/basic/file/downloadFile";
    // 删除文件 （进入回收站）
    public static final String DELETE_FILE = "/basic/file/deleteFile";
    // 复原文件 （从回收站移处）
    public static final String RECOVERY_FILE = "/basic/file/recoveryFile";
    // 彻底删除文件
    public static final String DELETE_FILE_REL = "/basic/file/deleteFileReal/";
}

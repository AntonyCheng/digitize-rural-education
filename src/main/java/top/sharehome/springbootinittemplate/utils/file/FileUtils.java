package top.sharehome.springbootinittemplate.utils.file;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.sharehome.springbootinittemplate.common.base.ReturnCode;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeReturnException;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具类
 *
 * @author AntonyCheng
 */
public class FileUtils {

    private static final Map<String, Long> FILE_EXTENSION_MAP = new HashMap<String, Long>() {
        {
            // mp4 视频大小限制600MB
            put("mp4", (long) (600 * 1024 * 1024));
            // zip 压缩包大小限制600MB
            put("zip", (long) (600 * 1024 * 1024));
            // rar 压缩包大小限制600MB
            put("rar", (long) (600 * 1024 * 1024));
            // 7z 压缩包大小限制600MB
            put("7z", (long) (600 * 1024 * 1024));
            // mp3 音频大小限制100MB
            put("mp3", (long) (100 * 1024 * 1024));
            // 常见文件大小限制30MB
            put("xlsx", (long) (30 * 1024 * 1024));
            put("xls", (long) (30 * 1024 * 1024));
            put("doc", (long) (30 * 1024 * 1024));
            put("docx", (long) (30 * 1024 * 1024));
            put("ppt", (long) (30 * 1024 * 1024));
            put("pptx", (long) (30 * 1024 * 1024));
            put("pdf", (long) (30 * 1024 * 1024));
            // 图片文件大小限制10MB
            put("png", (long) (10 * 1024 * 1024));
            put("jpg", (long) (10 * 1024 * 1024));
            put("jpeg", (long) (10 * 1024 * 1024));
            put("gif", (long) (10 * 1024 * 1024));
            // 小型文件大小限制1MB
            put("java", (long) (1024 * 1024));
            put("py", (long) (1024 * 1024));
            put("c", (long) (1024 * 1024));
            put("cpp", (long) (1024 * 1024));
            put("go", (long) (1024 * 1024));
            put("html", (long) (1024 * 1024));
            put("js", (long) (1024 * 1024));
            put("ts", (long) (1024 * 1024));
            put("sql", (long) (1024 * 1024));
            put("css", (long) (1024 * 1024));
            put("md", (long) (1024 * 1024));
        }
    };

    private static final Map<String, Long> AVATAR_EXTENSION_MAP = new HashMap<String, Long>() {
        {
            put("png", (long) (10 * 1024 * 1024));
            put("jpg", (long) (10 * 1024 * 1024));
            put("jpeg", (long) (10 * 1024 * 1024));
        }
    };

    /**
     * 对本项目普通文件进行校验
     *
     * @param multipartFile 文件
     */
    public static void validatedFile(MultipartFile multipartFile) {
        long size = multipartFile.getSize();
        if (ObjectUtils.isEmpty(multipartFile) && !ObjectUtils.notEqual(size, 0)) {
            throw new CustomizeReturnException(ReturnCode.FILE_UPLOAD_EXCEPTION);
        }
        String fileName = StringUtils.isNotBlank(multipartFile.getOriginalFilename()) ? multipartFile.getOriginalFilename() : multipartFile.getName();
        if (fileName.length() > 100) {
            throw new CustomizeReturnException(ReturnCode.USER_FILE_NAME_IS_TOO_LONG);
        }
        String suffix = FilenameUtils.getExtension(fileName).toLowerCase();
        if (suffix.length() > 10) {
            throw new CustomizeReturnException(ReturnCode.USER_FILE_SUFFIX_IS_TOO_LONG);
        }
        Long maxSize = FILE_EXTENSION_MAP.get(suffix);
        if (ObjectUtils.isEmpty(maxSize)) {
            throw new CustomizeReturnException(ReturnCode.USER_UPLOADED_FILE_TYPE_MISMATCH);
        }
        if (maxSize < size) {
            throw new CustomizeReturnException(ReturnCode.USER_UPLOADED_FILE_IS_TOO_LARGE);
        }
    }

    /**
     * 对本项目头像文件进行校验
     *
     * @param multipartFile 文件
     */
    public static void validatedAvatar(MultipartFile multipartFile) {
        long size = multipartFile.getSize();
        if (ObjectUtils.isEmpty(multipartFile) && !ObjectUtils.notEqual(size, 0)) {
            throw new CustomizeReturnException(ReturnCode.FILE_UPLOAD_EXCEPTION);
        }
        String fileName = StringUtils.isNotBlank(multipartFile.getOriginalFilename()) ? multipartFile.getOriginalFilename() : multipartFile.getName();
        if (fileName.length() > 100) {
            throw new CustomizeReturnException(ReturnCode.USER_FILE_NAME_IS_TOO_LONG);
        }
        String suffix = FilenameUtils.getExtension(fileName).toLowerCase();
        if (suffix.length() > 10) {
            throw new CustomizeReturnException(ReturnCode.USER_FILE_SUFFIX_IS_TOO_LONG);
        }
        Long maxSize = AVATAR_EXTENSION_MAP.get(suffix);
        if (ObjectUtils.isEmpty(maxSize)) {
            throw new CustomizeReturnException(ReturnCode.USER_UPLOADED_FILE_TYPE_MISMATCH);
        }
        if (maxSize < size) {
            throw new CustomizeReturnException(ReturnCode.USER_UPLOADED_FILE_IS_TOO_LARGE);
        }
    }

}
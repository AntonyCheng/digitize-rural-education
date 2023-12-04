package top.sharehome.springbootinittemplate.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务接口
 *
 * @author AntonyCheng
 * @since 2023/12/4 22:19:26
 */

public interface FileService {

    /**
     * 上传文件
     *
     * @param file     文件体
     * @param fileType 文件分类
     * @return 返回上传后的文件地址
     */
    public String uploadFile(MultipartFile file, String fileType);

}

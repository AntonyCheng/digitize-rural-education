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
     * @param multipartFile 文件体
     * @param fileType      文件分类
     * @param userId        文件所属用户ID
     */
    public void upload(MultipartFile multipartFile, String fileType, Long userId);

    /**
     * 根据文件ID进行删除
     *
     * @param fileId     文件ID
     * @param userId 文件所属用户ID
     */
    void deleteFileById(Long fileId, Long userId);
}

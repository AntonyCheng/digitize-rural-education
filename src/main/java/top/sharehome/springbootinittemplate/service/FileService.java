package top.sharehome.springbootinittemplate.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;
import top.sharehome.springbootinittemplate.model.dto.file.FilePageDto;
import top.sharehome.springbootinittemplate.model.entity.PageModel;
import top.sharehome.springbootinittemplate.model.vo.file.FilePageVo;

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
    Long uploadFile(MultipartFile multipartFile, String fileType, Long userId);

    /**
     * 上传头像
     *
     * @param multipartFile 文件体
     * @param userId        头像所属用户ID
     */
    Long uploadAvatar(MultipartFile multipartFile, Long userId);

    /**
     * 根据文件ID进行删除文件
     *
     * @param fileId 文件ID
     */
    void deleteById(Long fileId);

    /**
     * 文件分页查询
     *
     * @param filePageDto 文件查询条件类
     * @return 分页结果
     */
    Page<FilePageVo> pageFile(PageModel pageModel, FilePageDto filePageDto);
}
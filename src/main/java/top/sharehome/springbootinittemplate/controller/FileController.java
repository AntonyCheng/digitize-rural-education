package top.sharehome.springbootinittemplate.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.sharehome.springbootinittemplate.common.base.R;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;
import top.sharehome.springbootinittemplate.model.dto.file.FileUploadDto;
import top.sharehome.springbootinittemplate.service.FileService;
import top.sharehome.springbootinittemplate.utils.file.FileUtils;
import top.sharehome.springbootinittemplate.utils.satoken.LoginUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 文件控制器
 *
 * @author AntonyCheng
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 用户上传文件
     *
     * @param fileUploadDto 文件上传Dto类
     * @return 返回上传结果
     */
    @PostMapping("/upload")
    public R<String> upload(@Validated(PostGroup.class) FileUploadDto fileUploadDto) {
        FileUtils.validatedFile(fileUploadDto.getMultipartFile());
        Long userId = LoginUtils.getLoginUserId();
        fileService.upload(fileUploadDto.getMultipartFile(), fileUploadDto.getFileType(), userId);
        return R.ok("文件上传成功");
    }

    /**
     * 根据文件ID进行删除
     *
     * @param fileId 文件ID
     * @return 返回删除结果
     */
    @DeleteMapping("/delete/{id}")
    public R<String> deleteFileById(@PathVariable("id") @NotNull(message = "文件ID不能为空") Long fileId) {
        Long userId = LoginUtils.getLoginUserId();
        fileService.deleteFileById(fileId,userId);
        return R.ok("文件删除成功");
    }

}

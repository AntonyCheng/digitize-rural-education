package top.sharehome.springbootinittemplate.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.sharehome.springbootinittemplate.common.base.R;
import top.sharehome.springbootinittemplate.common.base.ReturnCode;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeReturnException;
import top.sharehome.springbootinittemplate.model.ModelConstant;
import top.sharehome.springbootinittemplate.model.dto.file.FileUploadDto;
import top.sharehome.springbootinittemplate.service.FileService;
import top.sharehome.springbootinittemplate.utils.file.FileUtils;
import top.sharehome.springbootinittemplate.utils.satoken.LoginUtils;

import javax.annotation.Resource;

/**
 * 用户端文件控制器
 *
 * @author AntonyCheng
 */
@RestController
@RequestMapping("/user_file")
public class UserFileController {

    @Resource
    private FileService fileService;

    /**
     * 用户上传文件
     *
     * @param fileUploadDto 文件上传Dto类
     * @return 返回文件ID
     */
    @PostMapping("/upload_file")
    public R<String> uploadFile(@Validated(PostGroup.class) FileUploadDto fileUploadDto) {
        FileUtils.validatedFile(fileUploadDto.getMultipartFile());
        if (!ModelConstant.FILE_TYPE.contains(fileUploadDto.getFileType())) {
            throw new CustomizeReturnException(ReturnCode.FILE_UPLOAD_EXCEPTION);
        }
        Long userId = LoginUtils.getLoginUserId();
        fileService.uploadFile(fileUploadDto.getMultipartFile(), fileUploadDto.getFileType(), userId);
        return R.ok("文件上传成功");
    }

    /**
     * 用户上传头像
     *
     * @param fileUploadDto 文件上传Dto类
     * @return 返回文件ID
     */
    @PostMapping("/upload_avatar")
    public R<String> uploadAvatar(@Validated(PostGroup.class) FileUploadDto fileUploadDto) {
        FileUtils.validatedAvatar(fileUploadDto.getMultipartFile());
        Long userId = LoginUtils.getLoginUserId();
        fileService.uploadAvatar(fileUploadDto.getMultipartFile(), userId);
        return R.ok("头像上传成功");
    }

}
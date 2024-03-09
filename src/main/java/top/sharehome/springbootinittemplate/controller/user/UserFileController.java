package top.sharehome.springbootinittemplate.controller.user;

import cn.dev33.satoken.annotation.SaCheckRole;
import org.aspectj.apache.bcel.classfile.ConstantModule;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.sharehome.springbootinittemplate.common.base.Constants;
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
@RequestMapping("/user/file")
@SaCheckRole(Constants.ROLE_USER)
public class UserFileController {

    @Resource
    private FileService fileService;

    /**
     * 用户上传共享文件
     *
     * @param fileUploadDto 文件上传Dto类
     * @return 返回文件ID
     */
    @PostMapping("/upload_file")
    public R<Long> uploadFile(@Validated(PostGroup.class) FileUploadDto fileUploadDto) {
        FileUtils.validatedFile(fileUploadDto.getMultipartFile());
        Long userId = LoginUtils.getLoginUserId();
        Long fileId = fileService.uploadFile(fileUploadDto.getMultipartFile(), ModelConstant.FILE_TYPE_SHARE, userId);
        return R.ok(fileId);
    }

    /**
     * 用户上传头像
     *
     * @param multipartFile 头像文件
     * @return 返回头像ID
     */
    @PostMapping("/upload_avatar")
    public R<Long> uploadAvatar(@RequestPart("file") MultipartFile multipartFile) {
        FileUtils.validatedAvatar(multipartFile);
        Long userId = LoginUtils.getLoginUserId();
        fileService.uploadAvatar(multipartFile, userId);
        return R.ok("用户上传头像成功");
    }

}
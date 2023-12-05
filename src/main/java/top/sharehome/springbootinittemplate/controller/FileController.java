package top.sharehome.springbootinittemplate.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import top.sharehome.springbootinittemplate.common.base.R;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;
import top.sharehome.springbootinittemplate.model.dto.file.FileUploadDto;
import top.sharehome.springbootinittemplate.service.FileService;
import top.sharehome.springbootinittemplate.utils.file.FileUtils;
import top.sharehome.springbootinittemplate.utils.satoken.LoginUtils;

import javax.annotation.Resource;

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

    @PostMapping("/upload")
    public R<String> upload(@RequestPart @Validated(PostGroup.class) FileUploadDto fileUploadDto) {
        FileUtils.validatedFile(fileUploadDto.getMultipartFile());
        Long userId = LoginUtils.getLoginUserId();
        fileService.uploadFile(fileUploadDto.getMultipartFile(), fileUploadDto.getFileType(), userId);
        return R.ok("文件上传成功");
    }

}

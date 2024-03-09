package top.sharehome.springbootinittemplate.controller.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sharehome.springbootinittemplate.common.base.Constants;
import top.sharehome.springbootinittemplate.common.base.R;
import top.sharehome.springbootinittemplate.common.validate.GetGroup;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;
import top.sharehome.springbootinittemplate.model.ModelConstant;
import top.sharehome.springbootinittemplate.model.dto.file.FilePageDto;
import top.sharehome.springbootinittemplate.model.dto.file.FileUploadDto;
import top.sharehome.springbootinittemplate.model.entity.PageModel;
import top.sharehome.springbootinittemplate.model.vo.file.FilePageVo;
import top.sharehome.springbootinittemplate.service.FileService;
import top.sharehome.springbootinittemplate.utils.file.FileUtils;
import top.sharehome.springbootinittemplate.utils.satoken.LoginUtils;

import javax.annotation.Resource;

/**
 * 管理端文件控制器
 *
 * @author AntonyCheng
 * @since 2023/12/6 23:16:31
 */
@RestController
@RequestMapping("/admin/file")
@SaCheckRole(Constants.ROLE_ADMIN)
public class AdminFileController {

    @Resource
    private FileService fileService;

    /**
     * 管理员上传4C文件
     *
     * @param fileUploadDto 文件上传Dto类
     * @return 返回文件ID
     */
    @PostMapping("/upload_fourc_file")
    public R<Long> uploadFourcFile(@Validated(PostGroup.class) FileUploadDto fileUploadDto) {
        FileUtils.validatedFile(fileUploadDto.getMultipartFile());
        Long userId = LoginUtils.getLoginUserId();
        Long fileId = fileService.uploadFile(fileUploadDto.getMultipartFile(), ModelConstant.FILE_TYPE_FOURC, userId);
        return R.ok(fileId);
    }

    /**
     * 管理员上传定制化文件
     *
     * @param fileUploadDto 文件上传Dto类
     * @return 返回文件ID
     */
    @PostMapping("/upload_customize_file")
    public R<Long> uploadCustomizeFile(@Validated(PostGroup.class) FileUploadDto fileUploadDto) {
        FileUtils.validatedFile(fileUploadDto.getMultipartFile());
        Long userId = LoginUtils.getLoginUserId();
        Long fileId = fileService.uploadFile(fileUploadDto.getMultipartFile(), ModelConstant.FILE_TYPE_CUSTOMIZE, userId);
        return R.ok(fileId);
    }

    /**
     * 分页获取用户文件信息
     *
     * @param filePageDto 文件分页Dto类
     * @return 返回分页数据
     */
    @GetMapping("/page")
    public R<Page<FilePageVo>> pageFile(@Validated(GetGroup.class) FilePageDto filePageDto) {
        Page<FilePageVo> res = fileService.pageFile(filePageDto);
        return R.ok(res);
    }

}
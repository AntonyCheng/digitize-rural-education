package top.sharehome.springbootinittemplate.controller.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sharehome.springbootinittemplate.common.base.Constants;
import top.sharehome.springbootinittemplate.common.base.R;
import top.sharehome.springbootinittemplate.common.validate.GetGroup;
import top.sharehome.springbootinittemplate.model.dto.file.FilePageDto;
import top.sharehome.springbootinittemplate.model.entity.PageModel;
import top.sharehome.springbootinittemplate.model.vo.file.FilePageVo;
import top.sharehome.springbootinittemplate.service.FileService;

import javax.annotation.Resource;

/**
 * 管理端文件控制器
 *
 * @author AntonyCheng
 * @since 2023/12/6 23:16:31
 */
@RestController
@RequestMapping("/admin/file")
@SaCheckRole(Constants.USER_ROLE_ADMIN)
public class AdminFileController {

    @Resource
    private FileService fileService;

    /**
     * 分页获取用户文件信息
     *
     * @param pageModel   分页实体类
     * @param filePageDto 文件分页Dto类
     * @return 返回分页数据
     */
    @GetMapping("/page")
    public R<Page<FilePageVo>> pageFile(@Validated(GetGroup.class) PageModel pageModel, FilePageDto filePageDto) {
        Page<FilePageVo> res = fileService.pageFile(pageModel, filePageDto);
        return R.ok(res);
    }

}
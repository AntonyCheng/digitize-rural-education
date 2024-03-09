package top.sharehome.springbootinittemplate.controller.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sharehome.springbootinittemplate.common.base.Constants;
import top.sharehome.springbootinittemplate.common.base.R;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;
import top.sharehome.springbootinittemplate.model.ModelConstant;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAddDto;
import top.sharehome.springbootinittemplate.service.ResourceService;
import top.sharehome.springbootinittemplate.utils.satoken.LoginUtils;

import javax.annotation.Resource;

/**
 * 管理端资源（fourC）控制器
 *
 * @author AntonyCheng
 */
@RequestMapping("/admin/resource")
@SaCheckRole(Constants.ROLE_ADMIN)
@RestController
public class AdminResourceController {

    @Resource
    private ResourceService resourceService;

    /**
     * 管理员添加4C资源
     */
    @PostMapping("/add_fourc")
    public R<String> addFourc(@Validated(PostGroup.class) @RequestBody ResourceAddDto resourceAddDto) {
        Long userId = LoginUtils.getLoginUserId();
        resourceService.addResource(userId, resourceAddDto, ModelConstant.FILE_TYPE_FOURC);
        return R.ok("共享资源添加成功");
    }

    /**
     * 管理员添加定制化资源
     */
    @PostMapping("/add_customize")
    public R<String> addCustomize(@Validated(PostGroup.class) @RequestBody ResourceAddDto resourceAddDto) {
        Long userId = LoginUtils.getLoginUserId();
        resourceService.addResource(userId, resourceAddDto, ModelConstant.FILE_TYPE_CUSTOMIZE);
        return R.ok("定制化资源添加成功");
    }

}

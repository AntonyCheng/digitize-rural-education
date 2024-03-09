package top.sharehome.springbootinittemplate.controller.user;

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
 * 用户端资源（share）控制器
 *
 * @author AntonyCheng
 */
@RequestMapping("/user/resource")
@SaCheckRole(Constants.ROLE_USER)
@RestController
public class UserResourceController {

    @Resource
    private ResourceService resourceService;

    /**
     * 用户添加共享资源
     */
    @PostMapping("/add")
    public R<String> add(@Validated(PostGroup.class) @RequestBody ResourceAddDto resourceAddDto) {
        Long userId = LoginUtils.getLoginUserId();
        resourceService.addResource(userId, resourceAddDto, ModelConstant.FILE_TYPE_SHARE);
        return R.ok("资源添加成功");
    }

}

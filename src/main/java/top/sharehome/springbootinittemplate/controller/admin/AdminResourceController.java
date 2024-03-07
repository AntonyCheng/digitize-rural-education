package top.sharehome.springbootinittemplate.controller.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sharehome.springbootinittemplate.common.base.Constants;
import top.sharehome.springbootinittemplate.service.ResourceService;

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

}

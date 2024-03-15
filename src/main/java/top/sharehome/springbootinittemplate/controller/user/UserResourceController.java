package top.sharehome.springbootinittemplate.controller.user;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.sharehome.springbootinittemplate.common.base.Constants;
import top.sharehome.springbootinittemplate.common.base.R;
import top.sharehome.springbootinittemplate.common.validate.GetGroup;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;
import top.sharehome.springbootinittemplate.model.ModelConstant;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAddDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAdminPageDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceUserPageDto;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceAdminPageVo;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceDetailVo;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceUserPageVo;
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

    /**
     * 用户分页查询共享资源
     */
    @GetMapping("/page_share")
    public R<Page<ResourceUserPageVo>> pageShare(@Validated(GetGroup.class) ResourceUserPageDto resourceUserPageDto){
        Page<ResourceUserPageVo> page = resourceService.pageUserResource(resourceUserPageDto, ModelConstant.FILE_TYPE_SHARE);
        return R.ok(page);
    }

    /**
     * 用户分页查询4C资源
     */
    @GetMapping("/page_fourc")
    public R<Page<ResourceUserPageVo>> pageFourc(@Validated(GetGroup.class) ResourceUserPageDto resourceUserPageDto){
        Page<ResourceUserPageVo> page = resourceService.pageUserResource(resourceUserPageDto, ModelConstant.FILE_TYPE_FOURC);
        return R.ok(page);
    }

    /**
     * 根据资源ID获取资源细节
     */
    @GetMapping("/detail/{id}")
    public R<ResourceDetailVo> detail(@PathVariable("id") Long id){
       ResourceDetailVo resourceDetailVo =  resourceService.detailById(id);
       return R.ok(resourceDetailVo);
    }

}

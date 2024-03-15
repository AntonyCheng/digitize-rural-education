package top.sharehome.springbootinittemplate.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAddDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAdminPageDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceUserPageDto;
import top.sharehome.springbootinittemplate.model.entity.Resource;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceAdminPageVo;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceDetailVo;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceUserPageVo;

/**
 * 文件服务接口
 *
 * @author AntonyCheng
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 添加资源
     */
    void addResource(Long userId, ResourceAddDto resourceAddDto, String fileTypeShare);

    /**
     * 管理员分页查询资源
     */
    Page<ResourceAdminPageVo> pageAdminResource(ResourceAdminPageDto resourceAdminPageDto, String fileType);

    /**
     * 用户分页查询资源
     */
    Page<ResourceUserPageVo> pageUserResource(ResourceUserPageDto resourceUserPageDto, String fileType);

    /**
     * 根据资源ID获取资源详情
     */
    ResourceDetailVo detailById(Long id);
}
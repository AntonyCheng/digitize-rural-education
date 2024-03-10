package top.sharehome.springbootinittemplate.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAddDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourcePageDto;
import top.sharehome.springbootinittemplate.model.entity.Resource;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourcePageVo;

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
     * 分页查询资源
     */
    Page<ResourcePageVo> pageResource(ResourcePageDto resourcePageDto, String fileTypeShare);
}
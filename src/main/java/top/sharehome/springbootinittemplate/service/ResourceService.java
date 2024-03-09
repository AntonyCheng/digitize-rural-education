package top.sharehome.springbootinittemplate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAddDto;
import top.sharehome.springbootinittemplate.model.entity.Resource;

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
}
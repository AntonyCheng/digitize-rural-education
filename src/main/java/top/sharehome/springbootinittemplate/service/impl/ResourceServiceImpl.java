package top.sharehome.springbootinittemplate.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.sharehome.springbootinittemplate.common.base.ReturnCode;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeReturnException;
import top.sharehome.springbootinittemplate.mapper.ResourceMapper;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAddDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourcePageDto;
import top.sharehome.springbootinittemplate.model.entity.Resource;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourcePageVo;
import top.sharehome.springbootinittemplate.service.ResourceService;

/**
 * 文件服务实现类
 *
 * @author AntonyCheng
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    @Override
    public void addResource(Long userId, ResourceAddDto resourceAddDto, String fileTypeShare) {
        Resource resource = new Resource()
                .setTitle(resourceAddDto.getTitle())
                .setInfo(resourceAddDto.getInfo())
                .setFileId(resourceAddDto.getFileId())
                .setUserId(userId)
                .setStatus(0)
                .setFileType(fileTypeShare);
        int insertResult = resourceMapper.insert(resource);
        if (insertResult == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
    }

    @Override
    public Page<ResourcePageVo> pageResource(ResourcePageDto resourcePageDto, String fileTypeShare) {
        return null;
    }

}

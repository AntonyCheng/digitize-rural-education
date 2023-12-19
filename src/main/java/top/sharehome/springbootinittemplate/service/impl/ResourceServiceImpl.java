package top.sharehome.springbootinittemplate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.sharehome.springbootinittemplate.mapper.ResourceMapper;
import top.sharehome.springbootinittemplate.model.entity.Resource;
import top.sharehome.springbootinittemplate.service.ResourceService;

/**
 * 文件服务实现类
 *
 * @author AntonyCheng
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}

package top.sharehome.springbootinittemplate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.sharehome.springbootinittemplate.model.entity.Resource;

/**
 * 资源Mapper类
 *
 * @author AntonyCheng
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

}
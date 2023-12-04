package top.sharehome.springbootinittemplate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.sharehome.springbootinittemplate.model.entity.File;

/**
 * 文件Mapper类
 *
 * @author AntonyCheng
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {
}
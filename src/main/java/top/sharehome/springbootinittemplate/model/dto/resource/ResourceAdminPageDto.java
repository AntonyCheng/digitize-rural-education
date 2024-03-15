package top.sharehome.springbootinittemplate.model.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.sharehome.springbootinittemplate.model.entity.PageModel;

import java.io.Serializable;

/**
 * 资源分页Dto类
 *
 * @author AntonyCheng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResourceAdminPageDto extends PageModel implements Serializable {

    private static final long serialVersionUID = 2303570952494309377L;

    /**
     * 资源标题
     */
    private String title;

    /**
     * 资源简介
     */
    private String info;

    /**
     * 资源所属用户的账号
     */
    private String userAccount;

    /**
     * 资源所对应文件类型
     */
    private String fileType;

}

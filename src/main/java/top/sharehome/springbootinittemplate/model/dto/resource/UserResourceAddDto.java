package top.sharehome.springbootinittemplate.model.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户添加共享资源Dto类
 *
 * @author AntonyCheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResourceAddDto implements Serializable {

    private static final long serialVersionUID = 1880411619453254823L;

    /**
     * 资源标题
     */
    @NotEmpty(message = "资源标题不能为空", groups = {PostGroup.class})
    private String title;

    /**
     * 资源简介
     */
    @NotEmpty(message = "资源简介不能为空", groups = {PostGroup.class})
    private String info;

    /**
     * 资源对应文件ID
     */
    @NotNull(message = "资源对应文件ID不能为空", groups = {PostGroup.class})
    private Long fileId;

}

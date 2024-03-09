package top.sharehome.springbootinittemplate.model.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 添加资源Dto类
 *
 * @author AntonyCheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResourceAddDto implements Serializable {

    private static final long serialVersionUID = 96731681039824443L;
    /**
     * 资源标题
     */
    @NotEmpty(message = "资源标题不能为空", groups = {PostGroup.class})
    @Size(min = 1, max = 50, message = "标题长度应为1-50字", groups = {PostGroup.class})
    private String title;

    /**
     * 资源简介
     */
    @NotEmpty(message = "资源简介不能为空", groups = {PostGroup.class})
    @Size(min = 1, max = 1000, message = "资源简介长度应为1-1000字", groups = {PostGroup.class})
    private String info;

    /**
     * 资源对应文件ID
     */
    @NotNull(message = "资源对应文件ID不能为空", groups = {PostGroup.class})
    private Long fileId;

}

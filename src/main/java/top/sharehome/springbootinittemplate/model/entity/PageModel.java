package top.sharehome.springbootinittemplate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.sharehome.springbootinittemplate.common.validate.GetGroup;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页实体类
 *
 * @author AntonyCheng
 * @since 2023/12/10 19:31:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageModel implements Serializable {

    private static final long serialVersionUID = -1649075566134281161L;

    @NotNull
    @Min(value = 1, groups = {GetGroup.class}, message = "分页参数错误")
    private Long page;

    @NotNull
    @Min(value = 1, groups = {GetGroup.class}, message = "分页参数错误")
    @Max(value = 100, groups = {GetGroup.class}, message = "分页参数错误")
    private Long size;

}

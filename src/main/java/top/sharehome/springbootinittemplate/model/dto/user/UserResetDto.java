package top.sharehome.springbootinittemplate.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;
import top.sharehome.springbootinittemplate.common.validate.PutGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户重置密码Dto类
 *
 * @author AntonyCheng
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResetDto implements Serializable {

    private static final long serialVersionUID = 7135266772387084504L;
    /**
     * 用户id
     */
    @NotNull(message = "用户ID不能为空", groups = {PutGroup.class})
    private Long id;

    /**
     * 重置后的新密码
     */
    @Size(min = 5, max = 16, message = "密码长度介于5-16位之间", groups = {PutGroup.class})
    @NotEmpty(message = "重置后的新密码不能为空", groups = {PutGroup.class})
    private String newPassword;

}

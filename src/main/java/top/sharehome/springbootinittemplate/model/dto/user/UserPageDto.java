package top.sharehome.springbootinittemplate.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户分页Dto类
 *
 * @author AntonyCheng
 * @since 2023/12/6 22:13:58
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDto implements Serializable {

    private static final long serialVersionUID = -3795426071198286722L;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户角色（admin/user）
     */
    private String role;

    /**
     * 用户学校
     */
    private String school;

}
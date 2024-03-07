package top.sharehome.springbootinittemplate.model.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户分页Vo类
 *
 * @author AntonyCheng
 * @since 2023/12/6 22:12:20
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserPageVo implements Serializable {

    private static final long serialVersionUID = -8409279135828575371L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户角色（admin/user）
     */
    private String role;

    /**
     * 用户学校
     */
    private String school;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
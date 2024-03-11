package top.sharehome.springbootinittemplate.model.vo.recource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 资源分页Vo类
 *
 * @author AntonyCheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResourcePageVo implements Serializable {

    private static final long serialVersionUID = 6549735237763810446L;

    /**
     * 资源ID
     */
    private Long id;

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
     * 资源对应文件ID
     */
    private Long fileId;

    /**
     * 资源对应文件名称
     */
    private String fileName;

    /**
     * 资源对应文件URL
     */
    private String fileUrl;

}

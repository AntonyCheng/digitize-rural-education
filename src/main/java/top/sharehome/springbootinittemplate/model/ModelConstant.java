package top.sharehome.springbootinittemplate.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 项目中一些固定的常量值
 *
 * @author AntonyCheng
 * @since 2023/12/6 23:00
 */

public interface ModelConstant {

    /**
     * 文件模块类型
     */
    List<String> FILE_TYPE = new ArrayList<String>() {
        {
            add("share");
            add("fourC");
            add("customize");
        }
    };

    /**
     * 启用/禁用状态
     */
    List<Integer> STATUS = new ArrayList<Integer>() {
        {
            add(0);
            add(1);
        }
    };

}

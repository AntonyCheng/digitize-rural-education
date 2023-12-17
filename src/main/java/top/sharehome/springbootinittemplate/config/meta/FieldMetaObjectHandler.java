package top.sharehome.springbootinittemplate.config.meta;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * 自动字段填充器
 *
 * @author AntonyCheng
 */
@Component
@Slf4j
public class FieldMetaObjectHandler implements MetaObjectHandler {

    /**
     * 需要处理的字段名——updateTime
     */
    public static final String UPDATE_TIME = "updateTime";

    /**
     * 需要处理的字段名——createTime
     */
    public static final String CREATE_TIME = "createTime";

    /**
     * 需要处理的字段名——isDeleted
     */
    public static final String IS_DELETED = "isDeleted";

    /**
     * 需要处理的字段名——role
     */
    public static final String ROLE = "role";

    /**
     * 需要处理的字段名——messageRead
     */
    public static final String MESSAGE_READ = "messageRead";

    /**
     * 需要处理的字段名——messageTotal
     */
    public static final String MESSAGE_TOTAL = "messageTotal";

    /**
     * 插入时自动填充的字段
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(UPDATE_TIME)) {
            metaObject.setValue(UPDATE_TIME, LocalDateTime.now());
        }

        if (metaObject.hasSetter(CREATE_TIME)) {
            metaObject.setValue(CREATE_TIME, LocalDateTime.now());
        }

        if (metaObject.hasSetter(IS_DELETED)) {
            metaObject.setValue(IS_DELETED, 0);
        }

        if (metaObject.hasSetter(ROLE)) {
            metaObject.setValue(ROLE, "user");
        }

        if (metaObject.hasSetter(MESSAGE_READ)) {
            metaObject.setValue(MESSAGE_READ, 0L);
        }

        if (metaObject.hasSetter(MESSAGE_TOTAL)) {
            metaObject.setValue(MESSAGE_TOTAL, 0L);
        }
    }

    /**
     * 更新时自动填充的字段
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(UPDATE_TIME)) {
            metaObject.setValue(UPDATE_TIME, LocalDateTime.now());
        }
    }

    /**
     * 依赖注入日志输出
     */
    @PostConstruct
    private void initDi() {
        log.info("############ meta object config DI.");
    }

}
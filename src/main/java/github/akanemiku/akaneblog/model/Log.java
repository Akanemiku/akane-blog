package github.akanemiku.akaneblog.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 日志表
 */
@Data
@Entity
@Table(name = "t_log")
public class Log implements Serializable {
    /**
     * 日志主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产生的动作
     */
    private String action;

    /**
     * 产生的数据
     */
    private String data;

    /**
     * 发生人id
     */
    private Integer authorId;

    /**
     * 日志产生的IP
     */
    private String ip;

    /**
     * 日志创建时间
     */
    private Integer created;
}

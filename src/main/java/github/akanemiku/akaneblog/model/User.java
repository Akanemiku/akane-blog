package github.akanemiku.akaneblog.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户表
 */
@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = -4363734549394202180L;
    /** 主键编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** email */
    private String email;
    /** 主页地址 */
    private String homeUrl;
    /**  用户显示的名称 */
    private String screenName;
    /** 用户注册时的GMT unix时间戳 */
    private Integer created;
    /** 最后活动时间 */
    private Integer activated;
    /** 上次登录最后活跃时间 */
    private Integer logged;
    /** 用户组 */
    private String groupName;
}

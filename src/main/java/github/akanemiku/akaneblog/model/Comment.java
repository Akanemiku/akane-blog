package github.akanemiku.akaneblog.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 评论表
 */
@Data
@Entity
@Table(name = "t_comment")
public class Comment implements Serializable {
    /**
     * comment表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coid;
    /**
     * contents表主键,关联字段
     */
    private Integer cid;
    /**
     * 评论生成时的GMT unix时间戳
     */
    private Integer created;
    /**
     * 评论作者
     */
    private String author;
    /**
     * 评论所属用户id
     */
    private String authorId;
    /**
     * 评论所属内容作者id
     */
    private Integer ownerId;
    /**
     * 评论者邮件
     */
    private String email;
    /**
     * 评论者网址
     */
    private String url;
    /**
     * 评论者ip地址
     *
     */
    private String ip;
    /**
     * 评论者客户端
     */
    private String agent;
    /**
     * 评论类型
     */
    private String type;
    /**
     * 评论状态
     */
    private String status;
    /**
     * 父级评论
     */
    private Integer parent;
    /**
     * 评论内容
     */
    private String content;
}

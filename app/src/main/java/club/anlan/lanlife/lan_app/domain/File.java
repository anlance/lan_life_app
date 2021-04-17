package club.anlan.lanlife.lan_app.domain;

import java.util.Date;


/**
 * 类
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 18:02
 */
public class File {

    /**
     * id
     */
    private String id;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件类型，文件后缀生成
     */
    private String type;

    /**
     * 存储地址
     */
    private String url;

    /**
     * 是否删除，0-被删除
     */
    private Integer deleteFlag;

    /**
     * 创建用户
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    public File() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

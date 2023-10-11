package com.potech.helpform.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 求助帖子回复
 * </p>
 *
 * @author Polaris111
 * @since 2022-03-05
 */
public class HelperPostBarMessage implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Long helpPostBarId;

    private LocalDateTime sendTime;

    private String messageUsername;

    private String message;

    private String sendLocation;

    private String messageUsernameUuid;

    private Integer deleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHelpPostBarId() {
        return helpPostBarId;
    }

    public void setHelpPostBarId(Long helpPostBarId) {
        this.helpPostBarId = helpPostBarId;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessageUsername() {
        return messageUsername;
    }

    public void setMessageUsername(String messageUsername) {
        this.messageUsername = messageUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendLocation() {
        return sendLocation;
    }

    public void setSendLocation(String sendLocation) {
        this.sendLocation = sendLocation;
    }

    public String getMessageUsernameUuid() {
        return messageUsernameUuid;
    }

    public void setMessageUsernameUuid(String messageUsernameUuid) {
        this.messageUsernameUuid = messageUsernameUuid;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "HelperPostBarMessage{" +
        "id=" + id +
        ", helpPostBarId=" + helpPostBarId +
        ", sendTime=" + sendTime +
        ", messageUsername=" + messageUsername +
        ", message=" + message +
        ", sendLocation=" + sendLocation +
        ", messageUsernameUuid=" + messageUsernameUuid +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

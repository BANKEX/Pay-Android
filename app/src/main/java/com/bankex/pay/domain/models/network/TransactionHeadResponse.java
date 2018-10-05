package com.bankex.pay.domain.models.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Gevork Safaryan on 21.06.2018
 */
public class TransactionHeadResponse {


    @SerializedName("totalEntities")
    @Expose
    private Integer totalEntities;
    @SerializedName("pageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("listId")
    @Expose
    private String listId;
    @SerializedName("moduleId")
    @Expose
    private String moduleId;
    @SerializedName("entityId")
    @Expose
    private String entityId;
    @SerializedName("updateTime")
    @Expose
    private String updateTime;

    /**
     * No args constructor for use in serialization
     */
    public TransactionHeadResponse() {
    }

    /**
     * @param totalEntities
     * @param moduleId
     * @param updateTime
     * @param listId
     * @param entityId
     * @param pageSize
     * @param pageNumber
     */
    public TransactionHeadResponse(Integer totalEntities, Integer pageNumber, Integer pageSize, String listId, String moduleId, String entityId, String updateTime) {
        super();
        this.totalEntities = totalEntities;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.listId = listId;
        this.moduleId = moduleId;
        this.entityId = entityId;
        this.updateTime = updateTime;
    }

    public Integer getTotalEntities() {
        return totalEntities;
    }

    public void setTotalEntities(Integer totalEntities) {
        this.totalEntities = totalEntities;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionHeadResponse that = (TransactionHeadResponse) o;
        return Objects.equal(totalEntities, that.totalEntities) &&
                Objects.equal(pageNumber, that.pageNumber) &&
                Objects.equal(pageSize, that.pageSize) &&
                Objects.equal(listId, that.listId) &&
                Objects.equal(moduleId, that.moduleId) &&
                Objects.equal(entityId, that.entityId) &&
                Objects.equal(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(totalEntities, pageNumber, pageSize, listId, moduleId, entityId, updateTime);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("totalEntities", totalEntities)
                .add("pageNumber", pageNumber)
                .add("pageSize", pageSize)
                .add("listId", listId)
                .add("moduleId", moduleId)
                .add("entityId", entityId)
                .add("updateTime", updateTime)
                .toString();
    }
}

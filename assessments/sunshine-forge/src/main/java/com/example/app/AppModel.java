package com.example.app;

import com.example.space.SpaceModel;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by localadmin on 7/20/17.
 */

@Entity
@Table (name = "apps")
final class AppModel {

    public Long appId;
    public String appName;
    public Integer diskAllocation;
    public Integer memoryAllocation;

    private SpaceModel spaceModel;

    public Long getAppId() {
        return appId;
    }


    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getDiskAllocation() {
        return diskAllocation;
    }

    public void setDiskAllocation(Integer diskAllocation) {
        this.diskAllocation = diskAllocation;
    }

    public Integer getMemoryAllocation() {
        return memoryAllocation;
    }

    public void setMemoryAllocation(Integer memoryAllocation) {
        this.memoryAllocation = memoryAllocation;
    }


    @ManyToOne
    @JoinColumn(name = "space_id")
    public SpaceModel getSpaceModel() {
        return spaceModel;
    }
}

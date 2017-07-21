package com.example.app;

import com.example.space.SpaceModel;

import javax.persistence.*;

/**
 * Created by localadmin on 7/20/17.
 */

@Entity
@Table (name = "apps")
final class AppModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appId;
    private String appName;
    private Integer diskAllocation;
    private Integer memoryAllocation;



    @ManyToOne
    @JoinColumn(name = "space_id")
    private SpaceModel spaceModel;

    public SpaceModel getSpaceModel() {
        return spaceModel;
    }

    public void setSpaceModel(SpaceModel spaceModel) {
        this.spaceModel = spaceModel;
    }



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


//    @ManyToOne
//    @JoinColumn(name = "space_id")
//    public SpaceModel getSpaceModel() {
//        return spaceModel;
//    }
}

package me.gerry.sdkpackage.domain;

import java.util.ArrayList;
import java.util.List;

public class SdkResourceEntity {

    /**
     * 未知类型
     */
    public static final String RESOURCE_TYPE_DK           = "Unknown Resource Type";
    /**
     * SDK Platform
     */
    public static final String RESOURCE_TYPE_SDKPLATFORM  = "SDK Platform";
    /**
     * Samples for SDK
     */
    public static final String RESOURCE_TYPE_SDKSAMPLE    = "Samples for SDK";
    /**
     * Android SDK Build-tools
     */
    public static final String RESOURCE_TYPE_BUILDTOOL    = "Android SDK Build-tools";
    /**
     * Android SDK Platform-tools
     */
    public static final String RESOURCE_TYPE_PLATFORMTOOL = "Android SDK Platform-tools";
    /**
     * Android SDK Tools
     */
    public static final String RESOURCE_TYPE_TOOL         = "Android SDK Tools";
    /**
     * Documentation for Android SDK
     */
    public static final String RESOURCE_TYPE_DOC          = "Documentation for Android SDK";
    /**
     * Sources for Android SDK
     */
    public static final String RESOURCE_TYPE_SDKSOURCE    = "Sources for Android SDK";

    private String             mRevision;
    private List<Archive>      mArchives;
    private boolean            mIsObsolete;
    private String             mResourceType;

    public SdkResourceEntity() {
        this.mRevision = "0";
        this.mArchives = new ArrayList<Archive>();
        this.mIsObsolete = false;
        this.mResourceType = SdkResourceEntity.RESOURCE_TYPE_DK;
    }

    /**
     * 获取资源类型
     * 
     * @return 资源类型
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_DK
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_SDKPLATFORM
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_SDKSAMPLE
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_BUILDTOOL
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_PLATFORMTOOL
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_TOOL
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_DOC
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_SDKSOURCE
     */
    public String getResourceType() {
        return this.mResourceType;
    }

    /**
     * 设置资源类型
     * 
     * @param resourceType
     *            资源类型
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_DK
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_SDKPLATFORM
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_SDKSAMPLE
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_BUILDTOOL
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_PLATFORMTOOL
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_TOOL
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_DOC
     * @see me.gerry.sdkpackage.domain.SdkResourceEntity#RESOURCE_TYPE_SDKSOURCE
     */
    public void setResourceType(String resourceType) {
        this.mResourceType = resourceType;
    }

    /**
     * 获取该资源的版本
     * 
     * @return 表示该资源版本的字符串，如03
     */
    public String getRevision() {
        return this.mRevision;
    }

    /**
     * 设置该资源的版本
     * 
     * @param revision
     *            表示该资源版本的字符串，如03
     */
    public void setRevision(String revision) {
        this.mRevision = revision;
    }

    /**
     * 判断该资源是否已经过时。
     * 
     * @return true 已经过时；false 没有过时。
     */
    public boolean isObsolete() {
        return this.mIsObsolete;
    }

    /**
     * 设置该资源的过时信息。
     * 
     * @param obsolete
     *            true 已经过时；false 没有过时。
     */
    public void setObsolete(boolean obsolete) {
        this.mIsObsolete = obsolete;
    }

    /**
     * 添加一个该资源的存储信息。
     * 
     * @param arc
     *            包含了一些存储位置等相关信息。
     */
    public void addArchive(Archive arc) {
        this.mArchives.add(arc);
    }

    /**
     * 获取该资源存储信息的集合。
     * 
     * @return 该资源存储信息的集合。
     */
    public List<Archive> archiveList() {
        return this.mArchives;
    }

    public void printArchives() {
        for (Archive archive : mArchives) {
            System.out.println(archive.toString());
        }
    }
}
package me.gerry.sdkpackage.domain;

import java.util.List;

import me.gerry.sdkpackage.util.LogPrinter;

public interface SdkResource {

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
    public String getResourceType();

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
    public void setResourceType(String resourceType);

    /**
     * 获取该资源的版本
     * 
     * @return 表示该资源版本的字符串，如03
     */
    public String getRevision();

    /**
     * 设置该资源的版本
     * 
     * @param revision
     *            表示该资源版本的字符串，如03
     */
    public void setRevision(String revision);

    /**
     * 判断该资源是否已经过时。
     * 
     * @return true 已经过时；false 没有过时。
     */
    public boolean isObsolete();

    /**
     * 设置该资源的过时信息。
     * 
     * @param obsolete
     *            true 已经过时；false 没有过时。
     */
    public void setObsolete(boolean obsolete);

    /**
     * 添加一个该资源的存储信息。
     * 
     * @param arc
     *            包含了一些存储位置等相关信息。
     */
    public void addArchive(Archive arc);

    /**
     * 获取该资源存储信息的集合。
     * 
     * @return 该资源存储信息的集合。
     */
    public List<Archive> getArchives();

    public void printArchives(LogPrinter printer);

}
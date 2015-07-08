package me.gerry.sdkpackage.domain;

import java.util.ArrayList;
import java.util.List;

public class SdkResourceEntity {

    /**
     * δ֪����
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
     * ��ȡ��Դ����
     * 
     * @return ��Դ����
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
     * ������Դ����
     * 
     * @param resourceType
     *            ��Դ����
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
     * ��ȡ����Դ�İ汾
     * 
     * @return ��ʾ����Դ�汾���ַ�������03
     */
    public String getRevision() {
        return this.mRevision;
    }

    /**
     * ���ø���Դ�İ汾
     * 
     * @param revision
     *            ��ʾ����Դ�汾���ַ�������03
     */
    public void setRevision(String revision) {
        this.mRevision = revision;
    }

    /**
     * �жϸ���Դ�Ƿ��Ѿ���ʱ��
     * 
     * @return true �Ѿ���ʱ��false û�й�ʱ��
     */
    public boolean isObsolete() {
        return this.mIsObsolete;
    }

    /**
     * ���ø���Դ�Ĺ�ʱ��Ϣ��
     * 
     * @param obsolete
     *            true �Ѿ���ʱ��false û�й�ʱ��
     */
    public void setObsolete(boolean obsolete) {
        this.mIsObsolete = obsolete;
    }

    /**
     * ���һ������Դ�Ĵ洢��Ϣ��
     * 
     * @param arc
     *            ������һЩ�洢λ�õ������Ϣ��
     */
    public void addArchive(Archive arc) {
        this.mArchives.add(arc);
    }

    /**
     * ��ȡ����Դ�洢��Ϣ�ļ��ϡ�
     * 
     * @return ����Դ�洢��Ϣ�ļ��ϡ�
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
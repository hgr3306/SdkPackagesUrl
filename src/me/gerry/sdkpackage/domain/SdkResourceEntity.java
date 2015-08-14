package me.gerry.sdkpackage.domain;

import java.util.ArrayList;
import java.util.List;

import me.gerry.sdkpackage.util.LogPrinter;

/**
 * 所有SDK资源的公共父类，提供资源的类型，版本，资源包信息，是否已经过时等信息。
 * 
 * @author Gerry
 *
 */
public class SdkResourceEntity implements SdkResource {

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
    
    /**
     * Sources for Android SDK
     */
    public static final String RESOURCE_TYPE_SYSTEMIMAGE    = "System Image";

    /**
     * SDK资源的版本号。
     */
    private String             mRevision;
    /**
     * SDK资源的资源包的信息。
     */
    private List<Archive>      mArchives;
    /**
     * SDK资源是否已经过时。
     */
    private boolean            mIsObsolete;
    /**
     * SDK资源的类型。
     */
    private String             mResourceType;

    public SdkResourceEntity() {
        this.mRevision = "0";
        this.mArchives = new ArrayList<Archive>();
        this.mIsObsolete = false;
        this.mResourceType = SdkResourceEntity.RESOURCE_TYPE_DK;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#getResourceType()
     */
    @Override
    public String getResourceType() {
        return this.mResourceType;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#setResourceType(java.lang.String)
     */
    @Override
    public void setResourceType(String resourceType) {
        this.mResourceType = resourceType;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#getRevision()
     */
    @Override
    public String getRevision() {
        return this.mRevision;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#setRevision(java.lang.String)
     */
    @Override
    public void setRevision(String revision) {
        this.mRevision = revision;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#isObsolete()
     */
    @Override
    public boolean isObsolete() {
        return this.mIsObsolete;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#setObsolete(boolean)
     */
    @Override
    public void setObsolete(boolean obsolete) {
        this.mIsObsolete = obsolete;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#addArchive(me.gerry.sdkpackage.domain.Archive)
     */
    @Override
    public void addArchive(Archive arc) {
        this.mArchives.add(arc);
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#getArchives()
     */
    @Override
    public List<Archive> getArchives() {
        return this.mArchives;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.SdkResource#printArchives()
     */
    @Override
    public void printArchives(LogPrinter printer) {
        for (Archive archive : mArchives) {
            printer.println(archive.toString());
        }
    }
}
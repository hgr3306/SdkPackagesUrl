package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.AndroidPlatformResourceEntity;

/**
 * Android x.x.x (APIxx) 下的 SDK Platform 
 * 
 * @author Guorui
 *
 */
public class SdkPlatform extends AndroidPlatformResourceEntity {
    private String mVersion;
    private String mDescription;

    public SdkPlatform() {
        super();
        this.mVersion = "0";
        this.mDescription = "There is no description.";
        super.setResourceType(RESOURCE_TYPE_SDKPLATFORM);
    }

    /**
     * 获取该资源适用Android平台的版本号
     * 
     * @return 表示Android平台版本号的字符串，如2.3,4.0
     */
    public String getVersion() {
        return this.mVersion;
    }

    /**
     * 设置该资源适用Android平台的版本号
     * 
     * @param version
     *            表示Android平台版本号的字符串，如2.3,4.0
     */
    public void setVersion(String version) {
        this.mVersion = version;
    }

    /**
     * 获取对该资源的描述
     * 
     * @return 描述信息
     */
    public String getDescription() {
        return this.mDescription;
    }

    /**
     * 对该资源进行描述
     * 
     * @param description
     *            描述信息
     */
    public void setDescription(String description) {
        this.mDescription = description;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(getResourceType());
        sb.append(": ");

        sb.append("Android ");
        sb.append(getVersion());
        sb.append(", ");

        if (!("".equals(getCodeName()))) {
            sb.append(getCodeName());
            sb.append(", ");
        }

        sb.append("API ");
        sb.append(getApiLevel());
        sb.append(", ");

        sb.append("revision ");
        sb.append(getRevision());

        sb.append(isObsolete() ? " (Obsolete)" : "");

        return sb.toString();
    }
}

package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.AndroidPlatformResourceEntity;
/**
 * Android x.x.x (APIxx) 下的 Samples for SDK
 * @author Gerry
 *
 */
public class SdkSample extends AndroidPlatformResourceEntity {

    private String mDescription;

    public SdkSample() {
        super();
        this.mDescription = "There is no description.";
        super.setResourceType(RESOURCE_TYPE_SDKSAMPLE);
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

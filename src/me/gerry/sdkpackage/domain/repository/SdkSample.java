package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.AndroidPlatformResource;

public class SdkSample extends AndroidPlatformResource {

    private String mDescription;

    public SdkSample() {
        super();
        this.mDescription = "There is no description.";
        super.setResourceType(RESOURCE_TYPE_SDKSAMPLE);
    }

    /**
     * ��ȡ�Ը���Դ������
     * 
     * @return ������Ϣ
     */
    public String getDescription() {
        return this.mDescription;
    }

    /**
     * �Ը���Դ��������
     * 
     * @param description
     *            ������Ϣ
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

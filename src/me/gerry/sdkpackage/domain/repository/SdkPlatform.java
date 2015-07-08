package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.AndroidPlatformResource;

/**
 * SDK Platform ��Դ�������Ϣ
 * 
 * @author Guorui
 *
 */
public class SdkPlatform extends AndroidPlatformResource {
    private String mVersion;
    private String mDescription;

    public SdkPlatform() {
        super();
        this.mVersion = "0";
        this.mDescription = "There is no description.";
        super.setResourceType(RESOURCE_TYPE_SDKPLATFORM);
    }

    /**
     * ��ȡ����Դ����Androidƽ̨�İ汾��
     * 
     * @return ��ʾAndroidƽ̨�汾�ŵ��ַ�������2.3,4.0
     */
    public String getVersion() {
        return this.mVersion;
    }

    /**
     * ���ø���Դ����Androidƽ̨�İ汾��
     * 
     * @param version
     *            ��ʾAndroidƽ̨�汾�ŵ��ַ�������2.3,4.0
     */
    public void setVersion(String version) {
        this.mVersion = version;
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

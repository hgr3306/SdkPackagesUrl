package me.gerry.sdkpackage.domain;

public class AndroidPlatformResource extends SdkResourceEntity {

    private String mApiLevel;
    private String mCodeName;

    public AndroidPlatformResource() {
        super();
        this.mApiLevel = "0";
        this.mCodeName = "";
    }

    /**
     * ��ȡ����Դ����API Level
     * 
     * @return ��ʾAPI Level���ַ�������10,22
     */
    public String getApiLevel() {
        return this.mApiLevel;
    }

    /**
     * ���ø���Դ����API Level
     * 
     * @param apiLevel
     *            ��ʾAPI Level���ַ�������10,22
     */
    public void setApiLevel(String apiLevel) {
        this.mApiLevel = apiLevel;
    }

    /**
     * ��ȡԤ���汾����
     * 
     * @return Ԥ���汾����
     */
    public String getCodeName() {
        return this.mCodeName;
    }

    /**
     * ����Ԥ���汾����
     * 
     * @param codeName
     *            Ԥ���汾����
     */
    public void setCodeName(String codeName) {
        this.mCodeName = codeName;
    }

}
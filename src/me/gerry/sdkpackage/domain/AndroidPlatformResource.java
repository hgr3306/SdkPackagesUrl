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
     * 获取该资源适用API Level
     * 
     * @return 表示API Level的字符串，如10,22
     */
    public String getApiLevel() {
        return this.mApiLevel;
    }

    /**
     * 设置该资源适用API Level
     * 
     * @param apiLevel
     *            表示API Level的字符串，如10,22
     */
    public void setApiLevel(String apiLevel) {
        this.mApiLevel = apiLevel;
    }

    /**
     * 获取预览版本名称
     * 
     * @return 预览版本名称
     */
    public String getCodeName() {
        return this.mCodeName;
    }

    /**
     * 设置预览版本名称
     * 
     * @param codeName
     *            预览版本名称
     */
    public void setCodeName(String codeName) {
        this.mCodeName = codeName;
    }

}
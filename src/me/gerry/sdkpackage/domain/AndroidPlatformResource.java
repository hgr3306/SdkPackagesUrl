package me.gerry.sdkpackage.domain;

public interface AndroidPlatformResource extends SdkResource {

    /**
     * 获取该资源适用API Level
     * 
     * @return 表示API Level的字符串，如10,22
     */
    public String getApiLevel();

    /**
     * 设置该资源适用API Level
     * 
     * @param apiLevel
     *            表示API Level的字符串，如10,22
     */
    public void setApiLevel(String apiLevel);

    /**
     * 获取预览版本名称
     * 
     * @return 预览版本名称
     */
    public String getCodeName();

    /**
     * 设置预览版本名称
     * 
     * @param codeName
     *            预览版本名称
     */
    public void setCodeName(String codeName);

}
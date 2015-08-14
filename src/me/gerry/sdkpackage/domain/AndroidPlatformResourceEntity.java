package me.gerry.sdkpackage.domain;

/**
 * android平台资源。该类及其子类的实例所表示的资源与特定的android平台版本相关，适用于特定的API LEVEL。
 * 
 * @author Gerry
 *
 */
public class AndroidPlatformResourceEntity extends SdkResourceEntity implements AndroidPlatformResource {

    /**
     * 资源适用的API LEVEL。
     */
    private String mApiLevel;
    /**
     * 预览版本的代号。
     */
    private String mCodeName;

    public AndroidPlatformResourceEntity() {
        super();
        this.mApiLevel = "0";
        this.mCodeName = "";
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.AndroidPlatformResource#getApiLevel()
     */
    @Override
    public String getApiLevel() {
        return this.mApiLevel;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.AndroidPlatformResource#setApiLevel(java.lang.String)
     */
    @Override
    public void setApiLevel(String apiLevel) {
        this.mApiLevel = apiLevel;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.AndroidPlatformResource#getCodeName()
     */
    @Override
    public String getCodeName() {
        return this.mCodeName;
    }

    /* (non-Javadoc)
     * @see me.gerry.sdkpackage.domain.AndroidPlatformResource#setCodeName(java.lang.String)
     */
    @Override
    public void setCodeName(String codeName) {
        this.mCodeName = codeName;
    }

}
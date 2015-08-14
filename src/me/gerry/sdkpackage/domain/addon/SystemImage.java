package me.gerry.sdkpackage.domain.addon;

import me.gerry.sdkpackage.domain.AndroidPlatformResourceEntity;

public class SystemImage extends AndroidPlatformResourceEntity {
    private String mAbi;
    
    public SystemImage() {
        super();
        super.setResourceType(RESOURCE_TYPE_SYSTEMIMAGE);
        this.mAbi = "";
    }

    public String getAbi() {
        return this.mAbi;
    }

    public void setAbi(String abi) {
        this.mAbi = abi;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(getAbi());
        sb.append(": ");
        
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

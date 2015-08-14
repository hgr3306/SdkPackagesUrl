package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.AndroidPlatformResourceEntity;

public class SdkSource extends AndroidPlatformResourceEntity {

    public SdkSource() {
        super();
        super.setResourceType(RESOURCE_TYPE_SDKSOURCE);
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

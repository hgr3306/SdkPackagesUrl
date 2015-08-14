package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.SdkResourceEntity;

/**
 * Tools 下的 Android SDK platform-tools
 * 
 * @author Guorui
 *
 */
public class PlatformTool extends SdkResourceEntity {

    public PlatformTool() {
        super();
        super.setResourceType(RESOURCE_TYPE_PLATFORMTOOL);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(getResourceType());
        sb.append(": ");

        sb.append("revision ");
        sb.append(getRevision());

        sb.append(isObsolete() ? " (Obsolete)" : "");

        return sb.toString();
    }

}

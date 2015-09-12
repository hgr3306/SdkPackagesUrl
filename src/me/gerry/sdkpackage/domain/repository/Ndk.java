package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.SdkResourceEntity;

/**
 * Android NDK 开发工具
 * 
 * @author Guorui
 *
 */
public class Ndk extends SdkResourceEntity {

    public Ndk() {
        super();
        super.setResourceType(RESOURCE_TYPE_NDK);
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

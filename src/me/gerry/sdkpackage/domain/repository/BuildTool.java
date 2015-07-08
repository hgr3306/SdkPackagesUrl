package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.SdkResourceEntity;

public class BuildTool extends SdkResourceEntity {

    public BuildTool() {
        super();
        super.setResourceType(RESOURCE_TYPE_BUILDTOOL);
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

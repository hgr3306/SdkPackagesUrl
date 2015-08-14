package me.gerry.sdkpackage.domain.repository;

import me.gerry.sdkpackage.domain.AndroidPlatformResourceEntity;
/**
 * JAVA docs
 * @author Gerry
 *
 */
public class Doc extends AndroidPlatformResourceEntity {

    public Doc() {
        super();
        super.setResourceType(RESOURCE_TYPE_DOC);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(getResourceType());
        sb.append(": ");

        sb.append("API ");
        sb.append(getApiLevel());
        sb.append(", ");

        sb.append("revision ");
        sb.append(getRevision());

        sb.append(isObsolete() ? " (Obsolete)" : "");

        return sb.toString();
    }
}

package me.gerry.sdkpackage.domain;

/**
 * SDK资源压缩包的相关信息
 * 
 * @author Gerry
 *
 */
public class Archive {
    /**
     * 常量值，表明该资源适用于任何操作系统。
     */
    public static final String HOSTOS_ANYOS   = "anyOS";
    /**
     * 常量值，表明该资源适用于windows系统。
     */
    public static final String HOSTOS_WINDOWS = "windows";
    /**
     * 常量值，表明该资源适用于linux系统。
     */
    public static final String HOSTOS_LINUX   = "linux";
    /**
     * 常量值，表明该资源适用于macosx系统。
     */
    public static final String HOSTOS_MACOSX  = "macosx";

    /**
     * 资源大小。
     */
    private long               mSize;
    /**
     * 校验码对象。
     */
    private Checksum           mChecksum;
    /**
     * 资源下载地址。
     */
    private String             mUrl;
    /**
     * 该资源适用的操作系统类型。
     */
    private String             hostOS;
    /**
     * 该资源适用的多少位的操作系统。
     */
    private String             hostBits;

    public Archive() {
        this.mSize = 0;
        this.mChecksum = new Checksum();
        this.mUrl = "";
        this.hostOS = Archive.HOSTOS_ANYOS;
        this.hostBits = "any";
    }

    public long getSize() {
        return this.mSize;
    }

    public void setSize(long size) {
        this.mSize = size;
    }

    public String getChecksumType() {
        return this.mChecksum.getType();
    }

    public void setChecksumType(String checksumType) {
        this.mChecksum.setType(checksumType);
    }

    public String getChecksumValue() {
        return this.mChecksum.getValue();
    }

    public void setChecksumValue(String checksumValue) {
        this.mChecksum.setValue(checksumValue);
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getHostOS() {
        return this.hostOS;
    }

    public void setHostOS(String hostOS) {
        this.hostOS = hostOS;
    }

    public String getHostBits() {
        return this.hostBits;
    }

    public void setHostBits(String hostBits) {
        this.hostBits = hostBits;
    }

    @Override
    public String toString() {
        return "Archive:\n"
                + "\tURL: " + getUrl() + "\n"
                + "\tSize: " + ((getSize() * 1.0) / 1024 / 1024) + " MB\n"
                + "\t" + getChecksumType() + ": " + getChecksumValue() + "\n"
                + "\tOS: " + getHostOS()
                + " "+("any".equals(hostBits)?"":hostBits)+" bits" +"\n";
    }

    /**
     * 资源的校验和对象
     * 
     * @author Gerry
     *
     */
    class Checksum {
        /**
         * 校验类型是sha1
         */
        public static final String TYPE_SHA1 = "sha1";

        /**
         * 资源的校验类型。
         */
        private String             type;
        /**
         * 资源的校验值。
         */
        private String             value;

        public Checksum() {
            this.type = TYPE_SHA1;
            this.value = "";
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

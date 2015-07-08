package me.gerry.sdkpackage.domain;

public class Archive {
    public static final String HOSTOS_ANYOS   = "anyOS";
    public static final String HOSTOS_WINDOWS = "windows";
    public static final String HOSTOS_LINUX   = "linux";
    public static final String HOSTOS_MACOSX  = "macosx";

    private long               mSize;
    private Checksum           mChecksum;
    private String             mUrl;
    private String             hostOS;

    public Archive() {
        this.mSize = 0;
        this.mChecksum = new Checksum();
        this.mUrl = "";
        this.hostOS = Archive.HOSTOS_ANYOS;
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

    @Override
    public String toString() {
        return "Archive:\n"
                + "\tURL: " + getUrl() + "\n"
                + "\tSize: " + ((getSize() * 1.0) / 1024 / 1024) + "MB\n"
                + "\t" + getChecksumType() + ": " + getChecksumValue() + "\n"
                + "\tOS: " + getHostOS() + "\n";
    }

    class Checksum {
        public static final String TYPE_SHA1 = "sha1";

        private String             type;
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

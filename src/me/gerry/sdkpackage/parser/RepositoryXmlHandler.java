package me.gerry.sdkpackage.parser;

import java.util.ArrayList;
import java.util.List;

import me.gerry.sdkpackage.domain.AndroidPlatformResource;
import me.gerry.sdkpackage.domain.Archive;
import me.gerry.sdkpackage.domain.SdkResourceEntity;
import me.gerry.sdkpackage.domain.repository.BuildTool;
import me.gerry.sdkpackage.domain.repository.Doc;
import me.gerry.sdkpackage.domain.repository.PlatformTool;
import me.gerry.sdkpackage.domain.repository.RepositoryElement;
import me.gerry.sdkpackage.domain.repository.SdkPlatform;
import me.gerry.sdkpackage.domain.repository.SdkSample;
import me.gerry.sdkpackage.domain.repository.SdkSource;
import me.gerry.sdkpackage.domain.repository.Tool;
import me.gerry.sdkpackage.util.XmlElementPool;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class RepositoryXmlHandler extends DefaultHandler {

    private boolean                 mReceiveCharacter;
    private StringBuffer            mStrBuf;
    private String                  mCharacter;

    private List<SdkResourceEntity> mResources;
    private SdkResourceEntity       mResource;
    private XmlElementPool          mElements;
    private Archive                 mArchive;

    public RepositoryXmlHandler() {
        super();
        this.mReceiveCharacter = false;
        this.mStrBuf = new StringBuffer();
        this.mCharacter = "";
        this.mElements = new XmlElementPool();
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (this.mReceiveCharacter) {
            if (mStrBuf.length() < 100) {
                mStrBuf.append(ch, start, length);
            } else {
                mStrBuf.append("\nToo Long...\n");
                this.mReceiveCharacter = false;
            }
        }
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始解析XML文件----->");
        mResources = new ArrayList<SdkResourceEntity>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("<-------解析结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        if (!("".equals(uri))) {

            switch (localName) {

            case RepositoryElement.PLATFORM:
                System.out.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_SDKPLATFORM + " :");
                mResource = new SdkPlatform();
                break;

            case RepositoryElement.SAMPLE:
                System.out.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_SDKSAMPLE + " :");
                mResource = new SdkSample();
                break;

            case RepositoryElement.PLATFORM_TOOL:
                System.out.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_PLATFORMTOOL + " :");
                mResource = new PlatformTool();
                break;

            case RepositoryElement.BUILD_TOOL:
                System.out.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_BUILDTOOL + " :");
                mResource = new BuildTool();
                break;

            case RepositoryElement.DOC:
                System.out.println("找到 " + SdkResourceEntity.RESOURCE_TYPE_DOC
                        + " :");
                mResource = new Doc();
                break;

            case RepositoryElement.TOOL:
                System.out.println("找到 " + SdkResourceEntity.RESOURCE_TYPE_TOOL
                        + " :");
                mResource = new Tool();
                break;

            case RepositoryElement.SOURCE:
                System.out.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_SDKSOURCE + " :");
                mResource = new SdkSource();
                break;

            case RepositoryElement.VERSION:
                this.mReceiveCharacter = true;
                break;

            case RepositoryElement.CODENAME:
                this.mReceiveCharacter = true;
                break;

            case RepositoryElement.API_LEVEL:
                this.mReceiveCharacter = true;
                break;

            case RepositoryElement.REVISION:
                this.mReceiveCharacter = true;
                break;

            case RepositoryElement.MAJOR:
                mStrBuf.setLength(0);
                break;

            case RepositoryElement.MINOR:
                this.mReceiveCharacter = true;
                break;

            case RepositoryElement.MICRO:
                this.mReceiveCharacter = true;
                break;

            case RepositoryElement.DESCRIPTION:
                this.mReceiveCharacter = true;
                break;

            case RepositoryElement.OBSOLETE:
                mResource.setObsolete(true);
                break;

            case RepositoryElement.ARCHIVE:
                mArchive = new Archive();
                break;

            case RepositoryElement.SIZE:
                this.mReceiveCharacter = true;
                break;

            case RepositoryElement.CHECKSUM:
                if (RepositoryElement.ARCHIVE.equals(mElements.previousElement())) {
                    String type = attributes.getValue(attributes.getIndex("type"));
                    mArchive.setChecksumType(type);
                    this.mReceiveCharacter = true;
                }
                break;

            case RepositoryElement.URL:
                if (RepositoryElement.ARCHIVE.equals(mElements.previousElement())) {
                    this.mReceiveCharacter = true;
                }
                break;

            case RepositoryElement.HOST_OS:
                if (RepositoryElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    this.mReceiveCharacter = true;
                }
                break;

            }

            mElements.addElement(localName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (!("".equals(uri))) {
            mElements.removeElement();

            switch (localName) {

            case RepositoryElement.PLATFORM:
            case RepositoryElement.SAMPLE:
            case RepositoryElement.PLATFORM_TOOL:
            case RepositoryElement.BUILD_TOOL:
            case RepositoryElement.DOC:
            case RepositoryElement.TOOL:
            case RepositoryElement.SOURCE:
                System.out.println("    " + mResource.toString());
                mResource.printArchives();
                mResources.add(mResource);
                break;

            case RepositoryElement.VERSION:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (RepositoryElement.PLATFORM.equals(mElements
                        .previousElement())) {
                    if (SdkResourceEntity.RESOURCE_TYPE_SDKPLATFORM.equals(mResource
                            .getResourceType())) {
                        SdkPlatform sp = (SdkPlatform) mResource;
                        sp.setVersion(mCharacter);
                    }
                }
                break;

            case RepositoryElement.CODENAME:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (mResource instanceof AndroidPlatformResource) {
                    AndroidPlatformResource ap = (AndroidPlatformResource) mResource;
                    ap.setCodeName(mCharacter);
                }
                break;

            case RepositoryElement.API_LEVEL:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (mResource instanceof AndroidPlatformResource) {
                    AndroidPlatformResource ap = (AndroidPlatformResource) mResource;
                    ap.setApiLevel(mCharacter);
                }
                break;

            case RepositoryElement.REVISION:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (!("layoutlib".equals(mElements.previousElement()))) {
                    mResource.setRevision(mCharacter);
                }
                break;

            case RepositoryElement.MAJOR:
                if (RepositoryElement.REVISION.equals(mElements
                        .previousElement())) {
                    mStrBuf.append('.');
                }
                this.mReceiveCharacter = false;
                break;

            case RepositoryElement.MINOR:
                if (RepositoryElement.REVISION.equals(mElements
                        .previousElement())) {
                    mStrBuf.append('.');
                }
                this.mReceiveCharacter = false;
                break;

            case RepositoryElement.MICRO:
                this.mReceiveCharacter = false;
                break;

            case RepositoryElement.DESCRIPTION:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (RepositoryElement.PLATFORM.equals(mElements
                        .previousElement())) {
                    if (SdkResourceEntity.RESOURCE_TYPE_SDKPLATFORM
                            .equals(mResource.getResourceType())) {
                        SdkPlatform sp = (SdkPlatform) mResource;
                        sp.setDescription(mCharacter);
                    }
                }
                break;

            case RepositoryElement.ARCHIVE:
                mResource.addArchive(mArchive);
                break;

            case RepositoryElement.SIZE:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (RepositoryElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    mArchive.setSize(Long.parseLong(mCharacter));
                }
                break;

            case RepositoryElement.CHECKSUM:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (RepositoryElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    mArchive.setChecksumValue(mCharacter);
                }
                break;

            case RepositoryElement.URL:
                this.mReceiveCharacter = false;
                if (-1 == mStrBuf.indexOf("https://dl-ssl.google.com/android/repository/")) {
                    mStrBuf.insert(0,
                            "https://dl-ssl.google.com/android/repository/");
                }
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (RepositoryElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    mArchive.setUrl(mCharacter);
                }
                break;

            case RepositoryElement.HOST_OS:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (RepositoryElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    if (0 == "windows".compareToIgnoreCase(mCharacter)) {
                        mArchive.setHostOS(Archive.HOSTOS_WINDOWS);
                    }

                    if (0 == "macosx".compareToIgnoreCase(mCharacter)) {
                        mArchive.setHostOS(Archive.HOSTOS_MACOSX);
                    }

                    if (0 == "linux".compareToIgnoreCase(mCharacter)) {
                        mArchive.setHostOS(Archive.HOSTOS_LINUX);
                    }
                }
                break;

            }
        }
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
        super.error(e);
    }

}

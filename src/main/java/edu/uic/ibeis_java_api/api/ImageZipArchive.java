package edu.uic.ibeis_java_api.api;

import java.io.File;

public class ImageZipArchive {

    private String localPath;

    protected ImageZipArchive(String localPath) {
        this.localPath = localPath;
    }

    protected ImageZipArchive(File localPath) {
        this.localPath = localPath.toString();
    }

    public String getLocalPath() {
        return localPath;
    }
}
package com.example.nlp_project;

/**
 * Created by DongND on 10/18/2016.
 */
public class PdfFile {
    String path;
    String name;
    String absolutePath;

    public PdfFile(String path, String name, String absolutePath){
        this.name=name;
        this.path=path;
        this.absolutePath=absolutePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}

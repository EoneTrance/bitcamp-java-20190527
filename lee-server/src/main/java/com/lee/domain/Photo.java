package com.lee.domain;

public class Photo {
  String fileName;
  String filePath;
  
  @Override
  public String toString() {
    return "Photo [fileName=" + fileName + ", filePath=" + filePath + "]";
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  
  public String getFilePath() {
    return this.filePath;
  }
}

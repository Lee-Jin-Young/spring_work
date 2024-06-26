package com.young.spring04.file.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {
    private int num;
    private String writer;
    private String title;
    private String orgFileName;
    private String saveFileName;
    private long fileSize;
    private String regdate;
    private int startRowNum;
    private int endRowNum;
    private MultipartFile myFile;
    
    public FileDto() {
        
    }

    public FileDto(int num, String writer, String title, String orgFileName, String savaFileName, long fileSize, String regdate, int startRowNum, int endRowNum, MultipartFile myFile) {
        super();
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.orgFileName = orgFileName;
        this.saveFileName = savaFileName;
        this.fileSize = fileSize;
        this.regdate = regdate;
        this.startRowNum = startRowNum;
        this.endRowNum = endRowNum;
        this.myFile = myFile;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrgFileName() {
        return orgFileName;
    }

    public void setOrgFileName(String orgFileName) {
        this.orgFileName = orgFileName;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String savaFileName) {
        this.saveFileName = savaFileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    public MultipartFile getMyFile() {
        return myFile;
    }

    public void setMyFile(MultipartFile myFile) {
        this.myFile = myFile;
    }
}

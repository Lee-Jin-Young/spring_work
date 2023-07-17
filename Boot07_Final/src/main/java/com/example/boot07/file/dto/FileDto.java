package com.example.boot07.file.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot07.users.dto.UsersDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //setter, getter
@Builder //chain형태로 값을 넣을수 있게 함
@NoArgsConstructor // 디폴트 생성자
@AllArgsConstructor // 모든 값을 인자로 가지는 생성자
@Alias("fileDto")
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
}

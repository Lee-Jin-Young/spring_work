package com.young.spring04.file.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.young.spring04.file.dto.FileDto;

/*
 *  [ View 를 만들어서 동작 시키는 방법 ]
 *  
 *  - AbstractView 추상클래스를 상속받아서 클래스를 정의한다.
 *  - @Component("bean의 이름 지정") 어노테이션을 붙여서 이름이 있는 bean이 되게 한다.
 *  - servlet-context.xml 에 BeanNameViewResolver 설정을 추가한다. 
 */

@Component("fileDownView")
public class FileDownView extends AbstractView{

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         /*
          *   ModelAndView 객체에 addObject( )해서 담은 내용은 Map 객체에서 얻어낼수 있다. 
          */
         FileDto dto = (FileDto)model.get("dto");

        long fileSize = dto.getFileSize();
        String orgFileName = dto.getOrgFileName();
        String saveFileName = dto.getSaveFileName();

        //다운로드 시켜줄 파일의 실제 경로 구성하기 
        // File.separator 는 window 에서는 \ , linux 에서는 /  를 얻어오게 된다. 
        String path = request.getServletContext().getRealPath("/resources/upload") 
                + File.separator
                + saveFileName;
        FileInputStream fis = new FileInputStream(path);

        String encodedName = URLEncoder.encode(orgFileName, "utf-8");
        encodedName = encodedName.replaceAll("\\+"," ");

        response.setHeader("Content-Disposition","attachment;filename = " + encodedName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        response.setContentLengthLong(fileSize);

        // response.getOutputStream() 메소드는 클라이언트에게 출력할수 있는 OutputStream 객체를 반환한다.
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        //한번에 최대 1M byte 씩 읽어올수 있는 버퍼
        byte[] buffer = new byte[1024*1024];
        int readedByte = 0;
        
        while(true){
            readedByte = fis.read(buffer);
            if(readedByte == -1) break;
            bos.write(buffer, 0, readedByte);
            bos.flush();
        }
        fis.close();        
    }

}
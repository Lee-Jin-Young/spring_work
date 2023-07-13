package com.example.boot07.users.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.users.dao.UsersDao;
import com.example.boot07.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired UsersDao dao;

	// 회원가입
    @Override
    public void addUser(UsersDto dto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(dto.getPwd());
        dto.setPwd(encodedPwd);

        dao.insert(dto);
    }

    // 로그인
    @Override
    public void loginProcess(UsersDto dto, HttpSession session) {
        boolean isValid = false;
        UsersDto resultDto = dao.getData(dto.getId());

        if (resultDto != null) {
            /*
             * 비밀번호가 BCrypt에 의해 암호화 되었기 때문에
             * Bcrypt를 이용해 입력한 비밀번호와 암호화된 비밀번호를 비교해야 한다.
             * DB에 암호화 된 비밀번호와 암호화 되지 않은 비밀번호가 있을 경우 오류가 날 수 있다.
             *         모든 비밀번호를 암호화 하는 메서드를 추가함으로 해결 가능하다.
             */
            isValid = BCrypt.checkpw(dto.getPwd(), resultDto.getPwd());
        }

        if (isValid) {
            session.setAttribute("id", resultDto.getId());
        }
    }

    // 회원 정보 열람
    @Override
    public void getInfo(HttpSession session, UsersDto dto, Model model) {
        String id = (String)session.getAttribute("id");
        UsersDto resultDto = dao.getData(id);
        model.addAttribute("dto", resultDto);
    }

    // 비밀번호 변경
    @Override
    public void updateUserPwd(HttpSession session, UsersDto dto, Model model) {
        String id = (String)session.getAttribute("id");
        UsersDto resultdto = dao.getData(id);
        
        //입력된 기존 비밀번호와 실제 기존 비밀번호를 비교, 같을 때만 변경
        boolean isValid = BCrypt.checkpw(dto.getPwd(), resultdto.getPwd());
        if (isValid) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            
            String encodedNewPwd = encoder.encode(dto.getNewPwd());
            dto.setNewPwd(encodedNewPwd);
            dto.setId(id);
            
            dao.updatePwd(dto);
            
            session.removeAttribute(id); //로그아웃
        }
        
        model.addAttribute("isSuccess", isValid)
        	.addAttribute(id);
    }

    // 프로필 이미지 등록
    @Override
    public Map<String, Object> saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
        String orgFileName=mFile.getOriginalFilename();
        String saveFileName=System.currentTimeMillis()+orgFileName;
        
        String realPath=request.getServletContext().getRealPath("/resources/upload");

        // upload 폴더가 없을 경우
        File upload=new File(realPath);
        if(!upload.exists()) {
           upload.mkdir();
        }
        
        try {
           String savePath=realPath+File.separator+saveFileName;
           mFile.transferTo(new File(savePath));
        }catch(Exception e) {
           e.printStackTrace();
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("imagePath", "/resources/upload/"+saveFileName);
        
        return map;
    }

    // 회원정보 수정
    @Override
    public void updateUser(UsersDto dto, HttpSession session) {
        String id = (String)session.getAttribute("id");
        // update_form에서 id를 입력할 수 없기 때문에 dto의 id는 null인 상태
        // session의 아이디값을 넣어줌으로 null point exception 방지
        dto.setId(id);

        if(dto.getProfile().equals("empty")) {
            dto.setProfile(null);
        }

        dao.update(dto);
    }

    // 회원 탈퇴
    @Override
    public void deleteUser(HttpSession session, Model model) {
        String id = (String)session.getAttribute("id");
        
        dao.delete(id);
    }

}

// UsersServiceImpl.java
package com.young.spring04.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring04.users.dao.UsersDao;
import com.young.spring04.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao dao;

    @Override
    public Map<String, Object> isExistId(String inputId) {
        return null;
    }

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
			 * 		모든 비밀번호를 암호화 하는 메서드를 추가함으로 해결 가능하다.
			 */
            isValid = BCrypt.checkpw(dto.getPwd(), resultDto.getPwd());
        }

        if (isValid) {
            session.setAttribute("id", resultDto.getId());
        }
    }

    // 회원 정보 열람
    @Override
    public void getInfo(HttpSession session, ModelAndView mView) {

    }

    // 비밀번호 변경
    @Override
    public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView) {

    }

    // 프로필 이미지 등록
    @Override
    public Map<String, Object> saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
        return null;
    }

    // 회원정보 수정
    @Override
    public void updateUser(UsersDto dto, HttpSession session) {

    }

    // 회원 탈퇴
    @Override
    public void deleteUser(HttpSession session, ModelAndView mView) {

    }
}

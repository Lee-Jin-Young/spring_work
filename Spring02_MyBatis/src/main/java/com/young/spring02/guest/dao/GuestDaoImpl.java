package com.young.spring02.guest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.young.spring02.guest.dto.GuestDto;

@Repository
public class GuestDaoImpl implements GuestDao{
    @Autowired
    private SqlSession session;
    
    @Override
    public void insert(GuestDto dto) {
        session.insert("guest.insert", dto);
    }

    @Override
    public void update(GuestDto dto) {
        session.update("guest.update",dto);
    }

    @Override
    public void delete(GuestDto dto) {
        session.delete("guest.delete", dto);
        
    }

    @Override
    public GuestDto getData(int num) {
        GuestDto dto = session.selectOne("guest.getData");
        return dto;
    }

    @Override
    public List<GuestDto> getList() {
        List<GuestDto> list = session.selectList("guest.getList");
        return list;
    }
}

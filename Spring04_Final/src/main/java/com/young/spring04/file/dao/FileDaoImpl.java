package com.young.spring04.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.young.spring04.file.dto.FileDto;

public class FileDaoImpl implements FileDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(FileDto dto) {
		session.insert("file.insert",dto);
	}

	@Override
	public FileDto getData(int num) {
		return session.selectOne("file.getData", num);
	}

	@Override
	public void delete(int num) {
		session.delete("file.delete",num);
	}

	@Override
	public List<FileDto> getList(FileDto dto) {
		return session.selectList("file.getList",dto);
	}

	@Override
	public int getCount(FileDto dto) {
		return session.selectOne("file.getCount",dto);
	}

}
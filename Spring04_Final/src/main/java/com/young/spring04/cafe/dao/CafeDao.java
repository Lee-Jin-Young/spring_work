package com.young.spring04.cafe.dao;

import java.util.List;

import com.young.spring04.cafe.dto.CafeDto;

public interface CafeDao {
    public List<CafeDto> getList(CafeDto dto);
    public int getCount(CafeDto dto);
    public void insert(CafeDto dto);
    public CafeDto getData(int num);
    public CafeDto getData(CafeDto dto); //키워드에 해당하는 글들의 정보
    public void addViewCount(int num);
    public void delete(int num);
    public void update(CafeDto dto);
}

package com.example.boot07.gallery.dao;

import java.util.List;

import com.example.boot07.gallery.dto.GalleryDto;

public interface GalleryDao {
    public List<GalleryDto> getList(GalleryDto dto);
    public int getCount();
    public void insert(GalleryDto dto);
    public GalleryDto getData(int num);
}

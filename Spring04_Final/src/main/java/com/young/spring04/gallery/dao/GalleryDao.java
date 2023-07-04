package com.young.spring04.gallery.dao;

import java.util.List;

import com.young.spring04.gallery.dto.GalleryDto;

public interface GalleryDao {
	public List<GalleryDto> getList(GalleryDto dto);
	public int getCount();
	public void insert(GalleryDto dto);
	public GalleryDto getData(int num);
}

package com.master.application.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.master.application.bean.Ariticle;
import com.master.application.bean.AriticleType;
import com.master.application.bean.Mod;
import com.master.application.bean.Tag;
import com.master.application.dao.AriticleDao;
import com.master.application.dao.AriticleTagDao;
import com.master.application.dao.AriticleTypeDao;
import com.master.application.dao.ModDao;
import com.master.application.dao.TagDao;
import com.master.application.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	AriticleTypeDao ariticleTypeDao;

	@Autowired
	TagDao tagDao;

	@Autowired
	AriticleDao ariticleDao;

	@Autowired
	AriticleTagDao ariticleTagDao;

	@Autowired
	ModDao modDao;

	@Override
	public List<AriticleType> getAriticleTypeList() throws Exception {
		return ariticleTypeDao.getAriticleTypeList();
	}

	@Override
	public List<Tag> getTagList() throws Exception {
		return tagDao.getTagList();
	}

	@Transactional
	@Override
	public void saveAriticle(Ariticle a, String tag_ids) throws Exception {

		
		String s = a.getContent();
		//String s1=s.replaceAll("<[\\w|\\s|\\=|\\\"|\\'|-]*>|</[\\w|\\s|\\=|\\\"|\\'|-]*>|<[\\w|\\s|\\=|\\\"|\\'|-]*/>|&[\\w]+;|\\s*", "");
		String s1=s.replaceAll("<([^>]*)>|&[\\w]+;|\\s*", "");
		
		if(s1.length()>230){
			a.setDescription(s1.substring(0, 230));
		}else {
			a.setDescription(s1);
		}
		
		ariticleDao.addAriticle(a);
		if (!StringUtils.isBlank(tag_ids)) {

			String[] tagIds = tag_ids.split(",");
			ariticleTagDao.addAriticleTag(a.getId(), Arrays.asList(tagIds));
		}

	}

	@Override
	public PageInfo<Ariticle> getAriticleList(int pageIndex, int pageSize, String typeIds) throws Exception {

		List<String> typeIdsList = new ArrayList<String>();
		if (!StringUtils.isBlank(typeIds)) {
			String[] ids = typeIds.split(",");
			typeIdsList = Arrays.asList(ids);
		}

		PageHelper.startPage(pageIndex, pageSize);
		List<Ariticle> list = ariticleDao.getAriticleList(typeIdsList);
		PageInfo<Ariticle> pageInfo = new PageInfo<Ariticle>(list);

		return pageInfo;

	}

	@Transactional
	@Override
	public void deleteAritile(int id) throws Exception {
		ariticleDao.deleteAriticle(id);
		ariticleTagDao.deleteTagByAriticle(id);

	}

	@Override
	public PageInfo<Mod> getModList(int pageIndex, int pageSize) throws Exception {
		PageHelper.startPage(pageIndex, pageSize);
		List<Mod> list = modDao.getModList();
		;
		PageInfo<Mod> pageInfo = new PageInfo<Mod>(list);
		return pageInfo;
	}

	@Override
	public void addMod(Mod m) throws Exception {
		modDao.addMod(m);
	}

	@Override
	public void deleteMod(int id) throws Exception {
		modDao.deleteMod(id);
	}

}

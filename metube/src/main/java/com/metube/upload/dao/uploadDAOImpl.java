package com.metube.upload.dao;

import org.springframework.stereotype.Repository;

import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.upload.vo.uploadVO;

@Repository("UploadDAO")
public class uploadDAOImpl extends mysqlAbstractMapper implements uploadDAO{

	@Override
	public int saveDataURL(uploadVO uvo) throws Exception {
		return insert("UploadDAO.saveDataURL", uvo);
	}

}

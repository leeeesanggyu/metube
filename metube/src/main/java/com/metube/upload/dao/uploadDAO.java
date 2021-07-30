package com.metube.upload.dao;

import com.metube.upload.vo.uploadVO;

public interface uploadDAO {

	int saveDataURL(uploadVO uvo) throws Exception;

}

package com.mybatis.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mybatis.dao.UserMapper;
import com.ssh.baseaction.BaseAction;
import com.ssh.basevo.PageBean;

public class UserAction extends BaseAction {
	@Autowired
	private UserMapper userMapper;

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}


	public void getPageList() throws IOException {
		// ��ѯ����
		String name = this.getHttpServletRequest().getParameter("name");
		// ��ҳ
		int pageIndex = Integer.parseInt(this.getHttpServletRequest()
				.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(this.getHttpServletRequest()
				.getParameter("pageSize"));
		
		int start = pageIndex * pageSize, end = start + pageSize;
		// �ֶ�����
		String sortField = this.getHttpServletRequest().getParameter(
				"sortField");
		String sortOrder = this.getHttpServletRequest().getParameter(
				"sortOrder");

		// ������ѯ����
		Map<String, Object> countParam = new HashMap<String, Object>();
		countParam.put("pageIndex", pageIndex);
		countParam.put("pageSize", pageSize);
		countParam.put("sortField", sortField);
		countParam.put("sortOrder", sortOrder);
		countParam.put("name", name);
		
		Integer userCount = userMapper.countByExample(countParam);
		// ���ݲ�ѯ����
		Map<String, Object> listParam = new HashMap<String, Object>();
		listParam.put("pageIndex", pageIndex);
		listParam.put("pageSize", pageSize);
		listParam.put("sortField", sortField);
		listParam.put("sortOrder", sortOrder);
		listParam.put("start", start);
		listParam.put("end", end);
		listParam.put("name", name);
		
		List<Map<String, Object>> userMapList = userMapper
				.selectByExample(listParam);

		HashMap result = new HashMap();
		result.put("data", userMapList);
		result.put("total", userMapList.size());

		String json = com.util.JSON.Encode(result);
		this.setAjax(json);
	}
	public String getBaiduPageList() throws IOException {
		// ��ѯ����
		String name = this.getHttpServletRequest().getParameter("name");
		// ��ҳ
		Long currentPage = Long.parseLong(this.getHttpServletRequest().getParameter("currentPage"));
		Long pageLine = Long.parseLong(this.getHttpServletRequest().getParameter("pageLine"));
		
		// ������ѯ����
		Map<String, Object> countParam = new HashMap<String, Object>();
		countParam.put("name", name);
		
		Integer userCount = userMapper.countByExample(countParam);
		Long totalCount =Long.valueOf(userCount + "");
		PageBean pageBean = new PageBean(totalCount,pageLine,currentPage);
		// ���ݲ�ѯ����
		Map<String, Object> listParam = new HashMap<String, Object>();
		listParam.put("start", pageBean.getStart());
		listParam.put("end", pageBean.getEnd());
		listParam.put("name", name);
		
		List<Map<String, Object>> userMapList = userMapper
				.selectByExample(listParam);
		
		String pageInfo = pageBean.getPageInfo();
		this.getHttpServletRequest().setAttribute("userMapList", userMapList);
		this.getHttpServletRequest().setAttribute("pageInfo", pageInfo);
		return "page_user_list";
	}
}

package com.ssh.basevo;



public abstract class BaseCondition {
 
    /**
     * ��ѯ����������
     */
    private Class<? extends BaseBean> basebean;
    /**
     * ��ҳ����
     */
    private PageBean pageBean = new PageBean(0l,null,null);//��ʼ���޶����¼
    
    
    
    public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Class<? extends BaseBean> getBasebean() {
        return basebean;
    }
 
    public void setBasebean(Class<? extends BaseBean> baseBean) {
        this.basebean = baseBean;
    }
    
}
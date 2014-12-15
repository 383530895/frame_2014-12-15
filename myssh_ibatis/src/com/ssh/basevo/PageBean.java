package com.ssh.basevo;

import java.util.ArrayList;
import java.util.List;


public class PageBean {
	private Long totalCount;//�ܼ�¼��
	private Long pageLine;//ÿҳ��ѯ��¼��
	private Long currentPage;//��ǰҳ��
	private Long pageNum;//��ҳ��
	private Long start;//��ѯ��ʼ��
	private Long end;//��ѯ������
	
	private List list;//��ѯ�Ľ����װ����
	private List<Long> pageNumerList = new ArrayList<Long>();//ҳ�뼯��
	public PageBean(){
	}
	/**
	 * 
	 * @param totalCount --�ܼ�¼��
	 * @param pageLine --ÿҳ��¼��
	 * @param currentPage --��ǰҳ��
	 */
	public PageBean(Long totalCount,Long pageLine,Long currentPage){
		this.totalCount = totalCount;
		this.pageLine = ((null == pageLine) ? 10 : pageLine);//(Ĭ��10����¼)
		this.pageNum = this.countPageNum(this.getTotalCount(),this.getPageLine());
		this.currentPage = ((null == currentPage || currentPage < 1) ? 1 : ((currentPage > this.getPageNum()) ? this.getPageNum() : currentPage));//(Ĭ�ϵ�1ҳ),������������ҳ������ô��չʾĩҳ
		this.initPageNumerList(this.getPageNum(),this.getCurrentPage());
		this.start = this.countStart(this.getCurrentPage(),this.getPageLine());
		this.end = this.countEnd(this.getCurrentPage(),this.getPageLine());
		
	}
	/**
	 * ÿ�ε���set��ز���ʱ��ˢ�·�ҳ����
	 */
	private void refreshPageParameter(){
		this.totalCount = this.getTotalCount();
		this.pageLine = ((null == this.getPageLine()) ? 10 : this.getPageLine());//(Ĭ��10����¼)
		this.pageNum = this.countPageNum(this.getTotalCount(),this.getPageLine());
		this.currentPage = ((null == this.getCurrentPage() || this.getCurrentPage() < 1) ? 1 : ((this.getCurrentPage() > this.getPageNum()) ? this.getPageNum() : this.getCurrentPage()));//(Ĭ�ϵ�1ҳ),������������ҳ������ô��չʾĩҳ
		this.start = this.countStart(this.getCurrentPage(),this.getPageLine());
		this.end = this.countEnd(this.getCurrentPage(),this.getPageLine());
	}
	/**
	 * �����ҳ����
	 * @param totalCount --�ܼ�¼��
	 * @param pageLine --ÿҳ��¼��
	 * @return
	 */
	public Long countPageNum(Long totalCount,Long pageLine){
		long pageNum;
		if(totalCount%pageLine > 0){
			pageNum = totalCount/pageLine + 1;
		}else{
			pageNum = totalCount/pageLine;
		}
		return pageNum;
	}
	/**
	 * �Ե�ǰҳΪ���ĵ㣬��ʼ��ǰ��չʾ��ҳǩ����
	 * @param pageNum
	 */
	public void initPageNumerList(Long pageNum,Long currentPage){
		int number = 5;//Ĭ�ϳ�ʼ����ҳǩ����
		int mid = number/2;//ǰ��λ��
		
		long first =  (currentPage - mid);//��ʼҳ��
		long last =  (currentPage + mid);//����ҳ��
		if(first < 1){
			first = 1;
			last = last + (1 - first);
		}
		if(last > pageNum){
			last = pageNum;
		}
		for(long i = first;i<last;i++){
			if(i<pageNum){
				pageNumerList.add(i);
			}
		}
	}
	/**
	 * �����ѯ��ʼ��
	 * @param currentPage --��ǰҳ��
	 * @param pageLine --ÿҳ��¼��
	 * @return
	 */
	public Long countStart(Long currentPage,Long pageLine){
		long start = (currentPage - 1)*pageLine + 1;
		return start;
	}
	/**
	 * �����ѯ������
	 * @param currentPage --��ǰҳ��
	 * @param pageLine --ÿҳ��¼��
	 * @return
	 */
	public Long countEnd(Long currentPage,Long pageLine){
		//��ĩҳʱ�Ľ�����ѯ��
		long end = currentPage * pageLine;
		if(currentPage == this.getPageNum()){
			long remainder = this.getTotalCount() % pageLine;
			if(remainder > 0){
				//���һҳʣ��ļ�¼��������ֻ��1ҳ�������
				end = (currentPage - 1) * pageLine + remainder;
			}
		}
		return end;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getPageLine() {
		return pageLine;
	}
	public void setPageLine(Long pageLine) {
		this.pageLine = pageLine;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public Long getPageNum() {
		return pageNum;
	}
	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	@Override
	public String toString() {
		String rn = "\r\n";
		String sp = ":";
		String sp1 = " ";
		//��ӡ���
		String info = "��¼����"+sp+rn;
		if(null == list){
			this.setList(new ArrayList<Object>());
		}
		for(int x = 0;x<list.size();x++){
			info += "��¼"+x + sp + list.get(x).toString() + rn;
		}	
		info += "�ܼ�¼����"+this.getTotalCount()+ sp1;
		info += "��ҳ����"+this.getPageNum()+ rn;
		info += "��ǰҳ��"+this.getCurrentPage()+ sp1;
		info += "ÿҳ����"+this.getPageLine()+ rn;
		info += "start:"+this.getStart()+ sp1;
		info += "end:"+this.getEnd();
		return info;
	}
	public List<Long> getPageNumerList() {
		return pageNumerList;
	}
	public void setPageNumerList(List<Long> pageNumerList) {
		this.pageNumerList = pageNumerList;
	}
	
}

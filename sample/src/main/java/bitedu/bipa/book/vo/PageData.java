package bitedu.bipa.book.vo;

import java.util.ArrayList;

/*
 *  paging bar와 해당 content를 셋팅한다.
 *  paging bar는 전/후 페이지의 존재 여부를 셋팅하고 page는 출력할 컨넨츠의 목록을 지정한다.
 *  paging bar를 만들어 내기 위해서는 요청 페이지가 있는 그룹정보와 페이지 정보가 필요하다.
 *  previous가 존재할 경우는 현재 그룹의 앞 그룹의 첫번째 페이지 정보를 가지고 있고
 *  next가 존재할 경우는 현재 그룹의 뒤 그룹의 첫번째 페이지 정보를 가지고 있다.
 *  가장 처음 리스트를 호출할 경우는 첫번째 그룹의 첫번째 페이지 정보를 가지고 있다.
 * 
 */
public class PageData<T> {
	private ArrayList<T> list;
	private String navBar;
	private int currentPage;

	public PageData(ArrayList<T> list, String navBar, String page) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.navBar = navBar;
		page = page==null?"1":page;
		this.currentPage = Integer.parseInt(page);
	}
	public ArrayList<T> getList() {
		return list;
	}
	public void setList(ArrayList<T> list) {
		this.list = list;
	}
	
	public String getNavBar() {
		return navBar;
	}
	public void setNavBar(String navBar) {
		this.navBar = navBar;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "PageData [list=" + list + ", navBar=" + navBar + ", currentPage=" + currentPage + "]";
	}

}

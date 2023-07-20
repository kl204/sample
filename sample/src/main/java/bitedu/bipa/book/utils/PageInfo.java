
package bitedu.bipa.book.utils;

public class PageInfo {
	//한 화면에 보여줄 페이지 개수
	private int itemsPerPage;
	//페이지네이선 아래 바의 그룹 번호 개수	
	private int groupsPerPage;
	//총페이지 개수
	private int endPage;

	public PageInfo(int itemsPerPage, int groupsPerPage) {
		this.itemsPerPage = itemsPerPage;
		this.groupsPerPage = groupsPerPage;
	}
	
	public PageInfo(int itemsPerPage, int groupsPerPage, int totalCount) {
		this.itemsPerPage = itemsPerPage;
		this.groupsPerPage = groupsPerPage;
		this.endPage = (int)(Math.ceil(totalCount/(float)itemsPerPage));
	}
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public int getGroupsPerPage() {
		return groupsPerPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setCount(int count) {
		this.endPage = (int)(Math.ceil(count/(float)itemsPerPage));
	}
	
	//리스트 db가서 짤라올때 쓸 메서드, result는 리스트중에서 어디 부터 짤라올지를 정한다.
	//itemsPerPage로 몇개까지 가져올지를 정하면 된다.
	public int calcuOrderOfPage(String page) {
		int result = 0;
		page = page==null?"1":page;
		result = (Integer.parseInt(page)-1)*this.itemsPerPage;
		return result;
	}
	
	@Override
	public String toString() {
		return "{'itemsPerPage':'" + itemsPerPage + "', groupsPerPage':'" + groupsPerPage + "', endPae':'" + endPage
				+ "}";
	}
	
}

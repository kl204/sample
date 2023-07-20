package bitedu.bipa.book.utils;

public class PagingbarGenerator {
	public static String generatePagingInfo(int group,int page,PageInfo info) {
		String result = null;
		StringBuilder sb = new StringBuilder();
		String space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		
		System.out.println("endpage : "+info.getEndPage());
		System.out.println("Groupspage : "+info.getGroupsPerPage());
		
		int endGroup = (int)Math.ceil((float)info.getEndPage()/info.getGroupsPerPage());
		System.out.println("endGroup "+endGroup);
		
		
		int temp1 = info.getEndPage() %	info.getGroupsPerPage() == 0	?	5	:	info.getEndPage() % info.getGroupsPerPage();
		int limit = endGroup==group ? temp1 : (info.getGroupsPerPage());	
		System.out.println(limit+","+info.getEndPage()+","+endGroup);
		
		
		if(group>1) {
			String prev = "<a href='./list.do?group="+(group-1)+"&page="+((group-1)*info.getGroupsPerPage())+"'>Prev</a>\n";
			sb.append(prev);
			//sb.append(space);
		}
		//((itemCount-1)*info.getItemsPerPage())
		for(int i=1;i<=limit;i++) {
			String temp = "<a href='./list.do?&group="+group+"&page="+((group-1)*info.getGroupsPerPage()+i)+"'>"+((group-1)*info.getGroupsPerPage()+i)+"</a>\n";
			//sb.append(space);
			sb.append(temp);
		}
		if(group<endGroup) {
			//sb.append(space);
			String next = "<a href='./list.do?group="+(group+1)+"&page="+((group*info.getGroupsPerPage())+1)+"'>Next</a>\n";
			sb.append(next);
		}
		
		result = sb.toString();
		
		
		return result;
	}
}

	package bitedu.bipa.book.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.book.service.BlmService4;
import bitedu.bipa.book.utils.PageInfo;
import bitedu.bipa.book.utils.PagingbarGenerator;
import bitedu.bipa.book.vo.BookCopy;
import bitedu.bipa.book.vo.PageData;

@Controller("bookController4")
@RequestMapping("/mybatisdb")
public class BookController4 {
	
	@Autowired
	private BlmService4 blmService;
	
	
	
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) String group,@RequestParam(required = false) String page) {
		ModelAndView mav = new ModelAndView();
		PagingbarGenerator pg = new PagingbarGenerator();
		PageInfo pi = new PageInfo(2,5, blmService.searchBookAll().size());
		
		//group = 현재 페이지 그룹 위치 예) 2개씩 그룹 묶는다? -> 12 (그룹 위치 1) / 34(그룹위치2) ...
		//page = 현재 그룹에서의 위치 포인트 예) 그룹위치3에서 prev로 내려오면 그룹위치2의 4가 됨

			System.out.println("pageGroup : " + group);
			System.out.println("pagePage : " + page);

		int pageGroup = 1;
		if(group!=null) {
			pageGroup = Integer.parseInt(group);
		}

		//nav bar 만드는 문자열
		String result = pg.generatePagingInfo(pageGroup, 0, pi);
		
		//각 페이지 별 긁어올 데이터 시작 객체, 이로부터 페이지당 몇개씩 긁어오면 되는지 알면 되니까 ItemsPerPage로
		int start = pi.calcuOrderOfPage(page);
		int end = pi.getItemsPerPage();
		
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		
		PageData<BookCopy> pd = new PageData<BookCopy>(blmService.searchPagingBook(start, end), result, page);		
		
		System.out.println(pd.getList());
		
		mav.addObject("pd",pd);
		mav.setViewName("./manager/book_list");
		return mav;
	}
	
	@RequestMapping(value="/view_regist.do", method=RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("./manager/book_regist");
		return mav;
	}
	
	@RequestMapping(value="/view_detail.do", method=RequestMethod.GET)
	public ModelAndView viewDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		BookCopy copy = blmService.findBook(bookSeq);
		mav.addObject("copy",copy);
		mav.setViewName("./manager/book_detail");
		return mav;
	}
	
	@RequestMapping(value="/view_update.do", method=RequestMethod.POST)
	public ModelAndView viewUpdate(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		BookCopy copy = blmService.findBook(bookSeq);
		mav.addObject("copy",copy);
		mav.setViewName("./manager/book_update");
		return mav;
	}
	
	@RequestMapping(value="/remove.do", method=RequestMethod.GET)
	public ModelAndView remove(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		boolean flag = blmService.removeBook(bookSeq);
		
		mav.setViewName("redirect:list.do");
		return mav;
	}
//	
//	@RequestMapping(value="/update.do", method=RequestMethod.POST)
//	public ModelAndView update(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		BookCopy copy = new BookCopy();
//		copy.setBookSeq(Integer.parseInt(request.getParameter("book_seq")));
//		copy.setIsbn(request.getParameter("isbn"));
//		copy.setTitle(request.getParameter("book_title"));
//		copy.setAuthor(request.getParameter("author"));
//		String date = request.getParameter("publish_date");
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			Date now = df.parse(date);
//			copy.setPublishDate(new Timestamp(now.getTime()));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		boolean flag = blmService.modifyBook(copy);
//		System.out.println("regist");
//		mav.setViewName("redirect:list.do");
//		return mav;
//	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("update"); 
		String path = "d:\\dev\\upload_files";

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024*1024*10);
		ServletFileUpload update = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = update.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookCopy copy = null;
		try {
			copy = blmService.upload(items);
			System.out.println("(controller)change data : "+copy);
			blmService.modifyBook(copy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public ModelAndView regist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BookCopy copy = new BookCopy();
		copy.setIsbn(request.getParameter("isbn"));
		copy.setTitle(request.getParameter("book_title"));
		copy.setAuthor(request.getParameter("author"));
		copy.setPublisher(request.getParameter("publisher"));
		String date = request.getParameter("publish_date");
		System.out.println("date "+date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(date);
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blmService.registBook(copy);
		System.out.println("regist");
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
	@RequestMapping(value="/upload.do", method=RequestMethod.POST)
	public ModelAndView upload(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("upload");
		//파일이 업로드 되기 전 임시 저장소 경로, 업로드된 파일이 이하일 경우에는 메모리에 저장되고, 
		//크기가 일정 값 이상일 경우에는 path 경로에 지정된 디렉토리에 임시 파일로 저장 
		String path = "d:\\dev\\upload_files";
		//DiskFileItemFactory 객체의 setRepository() 메서드를 사용하여 이 경로를 설정
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//factory 객체에 저장소(repository)를 설정, new File(path)를 통해 업로드된 파일이 저장될 디렉토리를 설정
		factory.setRepository(new File(path));
		//업로드 파일의 크기를 지정합니다. 이 코드에서는 10MB를 기준으로 설정,
		//업로드된 파일의 크기가 이 값보다 작으면 메모리에 저장되고, 이보다 크면 임시 디렉토리(위에서 설정한 저장소)에 저장
		factory.setSizeThreshold(1024*1024*10);
		//클래스의 객체 upload를 생성, ServletFileUpload은 실제 파일 업로드를 처리하는 데 사용되는 클래스로, 
		//factory 객체를 통해 업로드된 파일의 저장 방식과 크기 제한 등을 설정
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookCopy copy = null;
		try {
			copy = blmService.upload(items);
			System.out.println(copy);
			blmService.registBook(copy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	@RequestMapping(value="/download.do",method = RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName,HttpServletResponse resp) {
		
		//다운로드 할 이미지 파일 경로 + 이미지 파일 이름
		File downloadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
		
		try {
			//fileName.getBytes("UTF-8") = 파일 이름을 UTF-8 인코딩으로 변환한 후 바이트 배열로 가져옴
			//new String은 UTF-8로 인코딩된 파일 이름을 ISO-8859-1 인코딩으로 변환, String은 바이트 배열을 인수로 받음
			fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// ISO-8859-1 형식이 아니면 exception
			e.printStackTrace();
		}
		//http응답 헤더의content-type, 응답으로 보내지는 내용이 "text/html"임을 나타내며, 문자 인코딩은 UTF-8로 설정됨
		resp.setContentType("text/html; charset=UTF-8");
		//캐시를 사용하지 않도록 응답 헤더에 Cache-Control 속성을 설정, 브라우저가 응답을 캐시하지 않도록 함
		resp.setHeader("Cache-Control", "no-cache");
		//Content-Disposition이 다운로드 유도임, attatchment는 자동으로 다운로드 창에 이름 넣어주는것
		resp.addHeader("Content-Disposition", "attatchment;filename="+fileName);
		
		try {
			//지정된 경로(downloadFile)의 파일을 읽기 위한 FileInputStream 객체 생성
			FileInputStream fis = new FileInputStream(downloadFile);
			//getOutputStream메서드를 통해 클라이언트로 응답 데이터를 보낼 수 있는 출력 스트림 가져옴
			OutputStream os = resp.getOutputStream();
			//파일 데이터 읽어올 버퍼 생성(256byte)
			byte[] buffer = new byte[256];
			int length = 0;
			//파일 읽어오는 과정, fis.read()는 return readBytes(b, 0, b.length); 의 리턴 값을 가짐
			//즉 길이를 리턴시켜줌, buffer의 크기 지정 용도로 인자가 가는듯
			//os.write로 길이가 -1로 끝나기 전까지 while로 buffer에 추가해줌
			while((length=fis.read(buffer))!=-1) {
				os.write(buffer, 0, length);
			}
			os.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exceptionHandler(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("e",e);
		mav.setViewName("./error/exception");
		return mav;
	}
	
}

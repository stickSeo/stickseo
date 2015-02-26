<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.*, java.io.*" %>    
<%

	try{
		
		ArrayList fileName = new ArrayList();
		ArrayList originfileName = new ArrayList();

		MultipartRequest multi = null;
		String Uploader = "C:/upload";
		File dir = new File(Uploader);
	
		if(dir.exists()==false){
			dir.mkdir(); // 디렉토리 존재하지 않을경우 디렉토리 생성
		}
		
		multi= new MultipartRequest (request, Uploader, 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());

		
		String user = multi.getParameter("user");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");

		out.print("이름 : " + user + "<br>");
		out.print("제목 이름 : " + title + "<br>");
		out.print("내용 : " + content + "<br>");
		
		Enumeration files = multi.getFileNames();
	
		while(files.hasMoreElements()){
			
			String name = (String)files.nextElement();
			fileName.add(multi.getFilesystemName(name));
			originfileName.add(multi.getOriginalFileName(name));
			String filename = multi.getFilesystemName(name);
			String original = multi.getOriginalFileName(name);
			String type = multi.getContentType(name);
			
			File f = multi.getFile(name);
			out.print("파라미터 이름 : " + name + "<br>");
			out.print("실제 파일 이름 : " + filename + "<br>");
			out.print("저장된 파일 이름 : " + original + "<br>");
			out.print("파일 타입 : " + type + "<br>");
	 		
			if(f!=null){
				out.print("크기 : " + f.length() + "바이트");
				out.print("<br>");
	
			}
		}
		
    }catch(IOException ex){
    	out.print("파일 업로드 오류 : " + ex);
    }catch(Exception e){
    	out.print("오류 > " + e.getMessage());
    }
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 다중 업로드 된 파일 정보</h1>

</body>
</html>
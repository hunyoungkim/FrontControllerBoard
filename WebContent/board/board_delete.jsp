<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title> 게시판 </title>  
</head> 
<body>  
<div id="contentsArea">  
<section id="titlename">  
<h1>게시판 글 삭제</h1>  
<p class="formSign">    <strong class="require">필수</strong> 는 반드시 입력하여야 하는 항목입니다.  </p>
 
<form id="joinForm" name="deleteForm" action="./BoardDeleteService.do?num=${param.num}" method="post">    
<fieldset>    
<legend>게시판 글 삭제</legend>    
<p> <label for="password"> 글 입력 비밀번호 <strong class="require">필수</strong> </label>
<input type="password" id="password" name="pass" required placeholder="비밀번호 입력" size="12" />    </p>    
<div class="btnJoinArea">      
<button type="submit" class="btnOk">삭제</button>      
<button type="reset" class="btnCancel">취소</button> 
<button type="button" value="button" onclick="location.href='./BoardList.do'" class="btnOk">        목록      </button>    
</div>    
</fieldset>  
</form>    
</section>  
</div> 
</body> 
</html>
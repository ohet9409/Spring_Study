<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Upload with Ajax</h1>
<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple="multiple">
</div>

<button id='uploadBtn'>Upload</button>

<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
  
<script type="text/javascript">
$(document).ready(function(){
	/* 확장자 제한 */
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");	//exe, sh, zip 제한
	var maxSize = 5242880;	//5MB
	
	function checkExtension(fileName, fileSize) {
		
		if (fileSize >= maxSize) {
			alert("파일 사이즈 초과");
			return false;
		}
		
		if (regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			return false;
		}
		return true;
	}
	$("#uploadBtn").on("click", function() {
		// 데이터 추가하기 위해 선언
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files);
		
		//add filedate to formdata
		/* for (var i = 0; i < files.length; i++) {
			formData.append("uploadFile", files[i]);
		} */
		for (var i = 0; i < files.length; i++) {
			/* 파일을 체크해서 반환 */
			if (!checkExtension(files[i].name, files[i].size)) {
				return false;
			}
			/* 동적으로 파일을 담는다  key, value*/
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result) {
				alert("Uploaded");
				console.log("result: " + result);
			}
		});	//$.ajax
	});
});
</script>
</body>
</html>
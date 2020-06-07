<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.uploadResult{
	width:100%;
	background-color: gray;
}

.uploadResult ul{
	display:flex;
	flex-flow: row;
	justify-content: center;
	align-items: center; 
}

.uploadResult ul li{
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img{
	width: 100px;
}

.uploadResult ul li span{
	color:white;
}
.bigPictureWrapper{
	position: absolute;
	display: none;
	justify-content: center;
	align-item: center;
	top:0%;
	width:100%;
	height:100%;
	background-color: gray;
	z-index: 100%;
	background: rgba(255,255,255,0.5);
}
.bigPicture{
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}
.bigPicture img{
	width: 600px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Upload with Ajax</h1>
<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple="multiple">
</div>
<div class='uploadResult'>
	<ul><!-- ul 태그를 작성해서 첨부파일 이름을 목록으로 처리할 수 있도록 준비 -->
	</ul>
</div>
<button id='uploadBtn'>Upload</button>
<div class='bigPictureWrapper'>
	<div class='bigPicture'>
	</div>
</div>
</body>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
  
<script type="text/javascript">
//원본 이미지를 보여줄 DIV 처리
function showImage(fileCallPath) {
	alert(fileCallPath);
	
	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture").html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>").animate({width: '100%', height: '100%'}, 1000);
}


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
	
	var cloneObj = $(".uploadDiv").clone();
	
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
				//alert("Uploaded");
				console.log("result: " + result);
				console.log(result.image);
				showUploadFile(result);
				// 첨부파일 부분을 초기화
				$(".uploadDiv").html(cloneObj.html());
			}
		});	//$.ajax
	});
	
	var uploadResult = $(".uploadResult ul");
	
	function showUploadFile(uploadResultArr) {
		var str = "";
		console.log("uploadResultArr: " + uploadResultArr);
		$(uploadResultArr).each(function(i, obj) {
			console.log("image: " + obj.image);
			if(!obj.image){
				console.log("durl");
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid + "_" + obj.fileName);
				//str += "<li><img src='/resources/img/attach.png'>" + obj.fileName + "</li>";
				str += "<li><div><a href='/download?fileName="+fileCallPath+"'>"+ "<img src='/resources/img/attach.png'>" + obj.fileName + "</a>"+
						"<span data-file=\'"+fileCallPath+"\' data-type='file'>x</span><div></li>";
				
			}else{
				//str += "<li>" + obj.fileName + "</li>";
				
				var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
				
				var originPath = obj.uploadPath+ "\\" + obj.uuid + "_" + obj.fileName;
				
				originPath = originPath.replace(new RegExp(/\\/g), "/");
				
				//str += "<li><img src='/display?fileName=" + fileCallPath + "'><li>";
				str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\"><img src='/display?fileName=" + fileCallPath + "'></a>"+
						"<span data-file=\'"+fileCallPath+"\' data-type='image'>x</span><li>";
			
			}
			console.log("i: " + i);
			console.log("obj: " + obj);
			console.log("fileCallPath: " + fileCallPath);
			console.log("originPath: " + originPath);
		});
		uploadResult.append(str);
	}
	$(".bigPictureWrapper").on("click", function(e) {
		$(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
		setTimeout(() => {
			$(this).hide();
		}, 1000);
	});
	
	$(".uploadResult").on("click", "span", function(e) {
		
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log(targetFile);
		
		$.ajax({
			url: '/deleteFile',
			data: {fileName: targetFile, type: type},
			dataType: 'text',
			type: 'POST',
			success: function(result) {
				alert(result);
			}
		});	//$.ajax
	});
	
});
</script>

</html>
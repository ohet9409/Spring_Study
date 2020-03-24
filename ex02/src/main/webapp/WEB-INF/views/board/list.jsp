<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board List Page
			<button id='regBtn' type="button" class="btn btn-xs pull-right">Register New Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-boardered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					
					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno }"/> </td>
							<%-- <td><a href="/board/get?bno=<c:out value='${board.bno}'/>" target="_blank"><c:out value="${board.title }"/> </a> </td> --%>
							<td><a class="move" href='<c:out value="${board.bno }"/>'><c:out value="${board.title }"/> </a></td>
							<td><c:out value="${board.writer }"/> </td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate }"/> </td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }"/> </td>
						</tr>
					</c:forEach>
				</table><!-- table태그의 끝 -->
				
				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous"><a href="${pageMaker.startPage -1 }">Previous</a></li>
						</c:if>
						
						<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
							<li class="paginate_button ${pageMaker.cri.pageNum == Num ? "active":""}"><a href="${num }">${num }</a></li>
						</c:forEach>
						
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next"><a href="${pageMaker.endPage +1 }">Next</a></li>
						</c:if>
					</ul>
				</div>
				<!-- end Pagination -->
				<!-- 페이지 이동을 위한 파라미터값 전달 -->
			<form action="/board/list" method="get" id="actionForm">
				<input type="hidden" name ="pageNum" value = "${pageMaker.cri.pageNum }">
				<input type="hidden" name ="amount" value = "${pageMaker.cri.amount }">
			</form>
			
			<!-- Modal 추가 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">처리가 완료되었습니다.</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save changes</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-6 -->
</div>
<!-- /.row -->

<%@include file="../includes/footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var result = '<c:out value="${result}"/>';
	});
</script>
<script type="text/javascript">
	$(document).ready(function(){
		var result = '<c:out value="${result}"/>';
		
		checkModal(result);
		// 등록 모달창이 다시 보이지 않게 하기 위한
		history.replaceState({},null,null);
		
		function checkModal(result) {
			if (result === '' || history.state) {
				//alert(histroy.state);
				return;
			}
			
			if (parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
				//alert(result);
				//alert(histroy.state);
			}
			
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click", function () {
			
			self.location = "/board/register";
		});
		
		var actionForm = $("#actionForm");
		
		$(".paginate_button a").on("click", function(e) {
			
			e.preventDefault();
			
			console.log('click');
			
			actionForm.find("input[name = 'pageNum']").val($(this).attr("href"));
			
			actionForm.submit();
		});
		
		$(".move").on("click", function(e) {
			
			e.preventDefault();
			// 현재 url에 bno값을 추가해서 전달하기 위함
			actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href")+"'>");
			// /board/list을 변경
			actionForm.attr("action", "/board/get");
			actionForm.submit();
		});
	});
</script>
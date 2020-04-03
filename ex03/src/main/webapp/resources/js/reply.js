/**
 * 
 */
console.log("Reply Module....");
/*
var replyService = (function() {
	
	return {name: "AAAA"}
})();
*/

var replyService = (function() {
	// reply => 데이터, 함수의 파라미터 개수를 일치시킬 필요가 없기 때문에 callback이나 error와 같은 파라미터는 필요에 따라서 작성
	function add(reply, callback, error) {
		console.log("add reply..........");
		
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			// Ajax 호출이 성공하고, callback 값으로 적절한 함수가 존재한다면 해당 함수를 호출해서 결과를 반영하는 방식
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	// param이라는 객체를 통해서 필요한 파라미터를 전달받아 JSON 목록을 호출
	// JSON 형태가 필요하므로 URL 호출 시 확장자를 '.json'으로 요구
	function getList(param, callback, error) {
		
		var bno = param.bno;
		
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
				function(data) {
					if (callback) {
						callback(data);
					}
				}).fail(function(xhr, status, err) {
					if (error) {
						error();
					}
				});
		
	}
	
	function remove(rno, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	
	function update(reply, callback, error) {
		
		console.log("RNO: " + reply.rno);
		
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	
	function get(rno, callback, error) {
		
		$.get("/replies/" + rno + ".json", function(result) {
			
			if (callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get
	};
})();
	//글목록 보여주기
	function getBoardList(page){	
		$.ajax({
// 			url:'http://localhost:8085/api/board/boardlist',
			url:'http://localhost:8085/api/board/boardlist/10/'
								+((page==null||page==undefined)?1:page),
			method:'get',
			async: false,
			dataType:'json',
			success:(data) => {
				var list = data['list'];//글목록
				var count = Math.ceil(data['count']/10);//페이지 개수 구하기 (전체개수/row개수)
				
				//h1요소 생성 : document.createElement("h1")
				var header=$("<h1>글목록</h1>");  // $("h1"): doc.getEleByTagName("h1")
			
// 				var table=$("<div></div>").text(list[0].title)
// 				"<table > <tr><td>"+list[0].title+"</td></tr>"
				var table=$('<form id="muldel">'
						   +'<table class="table">'
						   +'	<thead>'
						   +'		<tr>'
						   +'           <th><input type="checkbox" name="all"/></th>'
						   +'			<th>번호</th>'
						   +'			<th>ID</th>'
						   +'			<th>제목</th>'
						   +'			<th>작성일</th>'
						   +'		</tr>'
						   +'	</thead>'
						   +'	<tbody></tbody>'
						   +'	<tfoot>'
						   +'		<tr>'
						   +'			<td style="text-align:center;" colspan="5" id="page">'
						   +'			</td>'
						   +'		</tr>'
						   +'		<tr>'
						   +'			<td colspan="5">'
						   +'				<button class="btn" type="button" onclick="insertForm()">글추가</button>'
						   +'               <button class="btn" type="button" onclick="muldel()">삭제</button>'
						   +'			</td>'
						   +'		</tr>'
						   +'	</tfoot>'
						   +'</table></form>');
				
				//collection 객체를 타겟으로 지정하고, 그 길이만큼 반복시켜주는 메서드
				$.each(list,function(i,dto){
					var tr=$("<tr></tr>")
					    .append($('<td><input type="checkbox" name="chk" value="'+dto['seq']+'"/></td>'))
						.append($("<td></td>").text(i+1)) //번호
						.append($("<td></td>").text(dto['id']))                //아이디
						.append($("<td></td>")
							.html($('<a style="cursor:pointer;" onclick="detail('+dto['seq']+')"></a>')
						    .text(dto['title'])))             				//제목
						.append($("<td></td>").text(dto['regdate']))           //작성일
					table.find('tbody').append(tr); //생성된 tr객체를 tbody에 추가한다.
				});
				
				//page 번호 출력하기  1 2 3 4 5..
				var pageTd=table.find('#page');
				for (var i = 1; i <= count; i++) {
					pageTd.append("<a style='cursor:pointer;' onclick='getBoardList("+i+")'>"+i+"</a>&nbsp;&nbsp;");
				}
				
				
				//실제 body 화면에 table을 반영한다.
				$("#boardlist").empty().append(header).append(table);
			}
		});
	}
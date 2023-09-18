<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Full Width Pics - Start Bootstrap Template</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet" />
	<style type="text/css">
		body{margin: 50px;}
		.box{border-bottom: 1px solid gray;}
		.box > .sub_menu{text-align: right;}
		a{cursor: pointer;}
	</style>
	<script type="text/javascript">
	//cookie, session, token(jwt), sso
	
		//나의 정보조회
		function myInfo(){
			
			$.ajax({
				url:"/banking/myinfo",
// 				url:"https://testapi.openbanking.or.kr/v2.0/user/me",
// 				headers:{"Authorization":"Bearer  eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDEzMzQ4Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2OTI2OTQ3NTgsImp0aSI6IjhhYWIyODNkLTljYjEtNGNkNS1iN2FmLTAzZTA5ZDBkNDRlYSJ9.BefjiG6pniEbFzcQmfr9vyhdBeR7ctY6i-OxATg5Jh4"},
// 				data:{'user_seq_no':'1101013348'},
				method:'get',
				dataType:'json',
				success:function(data){
// 					console.log(data['res_list'].length);
					
					var res_list=data['res_list'];
					
					$("#list").empty();//화면 초기화
					
// 					급여계좌
//             		83764585969076959 [국민은행]
					for(var i=0;i<res_list.length;i++){
						$("#list").append(
							'<div class="box container">'+
	                		'	<div>'+
	                		'		<h1>'+res_list[i]['account_alias']+'</h1>'+
	                		'		<p>'+res_list[i]['fintech_use_num']+' ['+res_list[i]['bank_name']+']</p>'+
	                		'	</div>'+
	                		'	<div class="sub_menu">'+
	                		'		<a onclick="balance(\''+res_list[i]['fintech_use_num']+'\',this)" class="balance">잔액조회</a> | '+
	                		'		<a>QR생성</a>'+
	                		'	</div>'+
	                		'	<div class="balance_amt"></div>'+
	                		'</div>'	
						)                         
					}                      
				},
				error:function(){
					location.reload();//페이지 새로고침
// 					alert("통신실패");
				}
			})
		}
	
		//잔액조회하기
		function balance(fintech_use_num,target){ //target은 클릭이 발생한 a태그 객체
// 			alert(fintech_use_num);	
			$.ajax({
				url:"/banking/balance",
				method:"get",
				data:{"fintech_use_num":fintech_use_num},
				dataType:"json",
				success:function(data){
// 					console.log(data);
					var box=$(target).parents(".box").eq(0);
					box.find(".balance_amt").html(
												"<p>잔액:"+data["balance_amt"]+"</p>"
											   +"<p><a>거래내역조회</a></p>"
											   +"<div class='transaction_list'></div>"  //거래내역이 출력될 div
												);
				},
				error:function(){
					alert("통신실패");
				}
			})

		}
	</script>
</head>
<body>
    <!-- Responsive navbar-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#!">Start Fintech</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="/">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">Contact</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">${sessionScope.ldto.username}님</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a></li>
<!--                     <li class="nav-item"><a class="nav-link" href="#!" onclick="myInfo()">나의정보</a></li> -->
                    <li class="nav-item"><a class="nav-link" href="/banking/myinfo">나의정보</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Header - set the background image for the header in the line below-->
<!--     <header class="py-5 bg-image-full" style="background-image: url('https://source.unsplash.com/wfh8dDlNFOk/1600x900')"> -->
<!--         <div class="text-center my-5"> -->
<!--             <img class="img-fluid rounded-circle mb-4" style="width:200px;height:200px;" src="resources/img/bank_bitcoin.png" alt="..." /> -->
<!--             <h1 class="text-white fs-3 fw-bolder">Fintech Open Banking</h1> -->
<!--             <p class="text-white-50 mb-0">Landing Page Template</p> -->
<!--         </div> -->
<!--     </header> -->
    <!-- Content section-->
    <section class="py-5">
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-lg-12">
                	<div id="list">
                		${userMeDto.res_list[0].bank_name}
                	</div>
                </div>
            </div>
        </div>
    </section>
    <!-- Footer-->
    <footer class="py-5 bg-dark">
        <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
    </footer>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="/resources/js/scripts.js"></script>
</body>
</html>

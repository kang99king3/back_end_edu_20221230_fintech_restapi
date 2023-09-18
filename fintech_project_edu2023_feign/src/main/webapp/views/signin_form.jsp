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
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet" />

	<script type="text/javascript">
	
		//사용자 인증 요청하기
		function authorization(){
			var url="https://testapi.openbanking.or.kr/oauth/2.0/authorize?"
				   +"response_type=code&"  //고정값: 인증요청시 반환되는 값의 타입을 의미
				   +"client_id=4987e938-f84b-4e23-b0a2-3b15b00f4ffd&"  //이용기관 ID
				   +"redirect_uri=http://localhost:8090/user/authresult&" //응답URI
				   +"scope=login inquiry transfer&"  // 토큰의 권한
				   +"state=12345678123456781234567812345678&" // 32자리 난수 설정
				   +"auth_type=0"; //0:최초 한번 인증, 2:인증생략
				   
			window.open(url,"인증하기","width=400px,height=600px");
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
                    <li class="nav-item"><a class="nav-link" href="/user/signin_form">SignIN</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/signup">SignUp</a></li>
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
                <div class="col-lg-6">
                	<form action="/user/login" method="post">
                		<table class="table">
                			<tr>
                				<th>이메일</th>
                				<td><input type="email" name="useremail" class="form-control"/></td>
                			</tr>
                			<tr>
                				<th>비밀번호</th>
                				<td><input type="password" name="userpassword" class="form-control"/></td>
                			</tr>
                			<tr>
                				<td colspan="2">
                					<input type="submit" value="signIn" class="btn btn-dark"/>
                				</td>
                			</tr>
                		</table>
                	</form>
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

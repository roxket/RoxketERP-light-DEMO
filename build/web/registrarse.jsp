<%-- 
    Document   : registrarse
    Created on : 17 sept. 2019, 11:10:44
    Author     : Oxker Studio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

	<head>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Roxket Lab - Mission Control</title>

		<!-- Custom fonts for this template-->
		<link href="<%= request.getContextPath()%>/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
		<link href="<%= request.getContextPath()%>/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
		<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link href="<%= request.getContextPath()%>/css/sb-admin-2.min.css" rel="stylesheet">

		<!-- Custom styles for this page -->
		<link href="<%= request.getContextPath()%>/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


	</head>

	<body class="bg-gradient-primary">

		<div class="container">

			<div class="card o-hidden border-0 shadow-lg my-5">
				<div class="card-body p-0">
					<!-- Nested Row within Card Body -->
					<div class="row">
						<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
						<div class="col-lg-7">
							<div class="p-5">
								<div class="text-center">
									<h1 class="h4 text-gray-900 mb-4">Darse de alta!</h1>
								</div>
								<form class="user" action="/Roxket/usuarios" method="post">
									<div class="form-group row">
										<div class="col-sm-6 mb-3 mb-sm-0">
											<input type="hidden" name="accion" value="crearUsuario">
											<input type="text" class="form-control form-control-user" id="name" placeholder="Nombre">
										</div>
										<div class="col-sm-6">
											<input type="text" class="form-control form-control-user" id="surname" placeholder="Apellidos">
										</div>
									</div>
									<div class="form-group">
										<input type="email" class="form-control form-control-user" name="email" id="email" placeholder="Email">
									</div>
									<div class="form-group row">
										<div class="col-sm-6 mb-3 mb-sm-0">
											<input type="password" class="form-control form-control-user" name="pass" id="pass" placeholder="Contraseña">
										</div>
										<div class="col-sm-6">
											<input type="password" class="form-control form-control-user" name="pass2" id="pass2" placeholder="Repetir contraseña">
										</div>
									</div>
									<input type="submit" value="Registrarse" class="btn btn-primary btn-user btn-block">
									<hr>

								</form>
								<hr>
								<!-- <div class="text-center">
												<a class="small" href="forgot-password.html">Olvidaste tu contraseña?</a>
								</div>-->
								<div class="text-center">
									<a class="small" href="index.jsp">Ya tienes una cuenta? Acceder!</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

		<!-- Bootstrap core JavaScript-->
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

		<!-- Core plugin JavaScript-->
		<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

		<!-- Custom scripts for all pages-->
		<script src="js/sb-admin-2.min.js"></script>

	</body>

</html>

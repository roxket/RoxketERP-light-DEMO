<%-- 
    Document   : index
    Created on : 21 ago. 2019, 18:14:52
    Author     : Oxker Studio
--%>


<%@page import="java.util.List"%>
<%@page import="com.roxket.jockerdemo.modelos.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

	<head>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Roxket Lab - Mission Control</title>

		<!-- Custom fonts for this template-->
		<link href="<%= request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
		<link href="<%= request.getContextPath()%>/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
		<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link href="<%= request.getContextPath()%>/css/sb-admin-2.min.css" rel="stylesheet">

		<!-- Custom styles for this page -->
		<link href="<%= request.getContextPath()%>/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

	</head>

	<%
		List<Categoria> listCategorias = (List) request.getAttribute("categorias");

		String resultado = "";

		if (request.getSession().getAttribute("operacionCategoria") != null) {
			resultado = (String) request.getSession().getAttribute("operacionCategoria");
		}

	%>

	<body id="page-top">

		<!-- Page Wrapper -->
		<div id="wrapper">
			<!-- Content Wrapper -->

			<!-- Sidebar -->
			<jsp:include page="../layouts/sidebar.jsp"></jsp:include>

				<!-- Content Wrapper -->
				<div id="content-wrapper" class="d-flex flex-column">

					<!-- Main Content -->
					<div id="content">

						<!-- Header Topbar-->
					<jsp:include page="../layouts/header.jsp"></jsp:include>

						<!-- Begin Page Content -->
						<div class="container-fluid">

							<!-- Page Heading -->
							<h1 class="h3 mb-2 text-gray-800">Categorias</h1>
							<p class="mb-4">Permite ver, crear y editar las categorias o familias de cada referencia y/o producto.</p>

						<% if (!resultado.isEmpty() && resultado.contentEquals("Categoria creada correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						<% } else if (!resultado.isEmpty() && resultado.contentEquals("Se actualizó la categoria correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						<% } else if (!resultado.isEmpty() && resultado.contentEquals("Se ha eliminado la categoria correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						
						<% } else if (!resultado.isEmpty()) {%>
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>

						<% } 
							request.getSession().removeAttribute("operacionCategoria");
						%>

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">Listado</h6>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>ID Categoria</th>
												<th>Nombre</th>
												<th>Acciones</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>ID Categoria</th>
												<th>Nombre</th>
												<th>Acciones</th>
											</tr>
										</tfoot>
										<tbody>
											<%
												for (Categoria categoria : listCategorias) {
											%>
											<tr>
												<td><%= categoria.getCategoriaId()%></td>
												<td><%= categoria.getNombreCat()%></td>
												<td>

													<div class="form-row">
														<div class="form-group col-ml-1">
															<a href="categorias?accion=Editar&idCat=<%=categoria.getCategoriaId()%>" class="btn btn-info btn-circle btn-sm">
																<i class="far fa-pencil"></i>
															</a>
														</div>

														<div class="form-group col-ml-1">
															<form action="categorias" method="post">
																<button type="submit" class="btn btn-info btn-circle btn-sm">
																	<i class="far fa-trash-alt" ></i>
																	<input	type="hidden" name="accion" value="borrar">
																	<input	type="hidden" name="idCat" value="<%= categoria.getCategoriaId()%>" >
																</button>
															</form>
														</div>
													</div>
												</td>
											</tr>
											<% }%>
										</tbody>
									</table>
									<br>
									<a href="categorias?accion=Nuevo" class="btn btn-primary btn-icon-split">
										<span class="icon text-white-50">
											<i class="far fa-plus-circle"></i>
										</span>
										<span class="text">Añadir Categoria</span>
									</a>
								</div>
							</div>
						</div>

					</div>
					<!-- /.container-fluid -->

				</div>
				<!-- End of Main Content -->

				<!-- Footer -->
				<jsp:include page="../layouts/footer.jsp"></jsp:include>
					<!-- Header -->
					
				</div>
				<!-- End of Content Wrapper -->

			</div>
			<!-- End of Page Wrapper -->

			<!-- Footer Extra -->
		<jsp:include page="../layouts/footerExtra.jsp"></jsp:include>

			<!-- Page level plugins -->
			<script src="<%= request.getContextPath()%>/vendor/datatables/jquery.dataTables.min.js"></script>
		<script src="<%= request.getContextPath()%>/vendor/datatables/dataTables.bootstrap4.min.js"></script>

		<!-- Page level custom scripts -->
		<script src="js/demo/datatables-demo.js"></script>

	</body>

</html>


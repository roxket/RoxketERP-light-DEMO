<%-- 
    Document   : index
    Created on : 21 ago. 2019, 18:14:52
    Author     : Oxker Studio
--%>


<%@page import="com.roxket.jockerdemo.modelos.Cliente"%>
<%@page import="java.util.List"%>
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
		List<Cliente> listaClientes = null;

		if( request.getAttribute("clientes")!= null){
			listaClientes = (List<Cliente>) request.getAttribute("clientes");
		}

		String resultado = "";

		if (request.getSession().getAttribute("operacionCliente") != null) {
			resultado = (String) request.getSession().getAttribute("operacionCliente");
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
							<h1 class="h3 mb-2 text-gray-800">Clientes</h1>
							<p class="mb-4">Permite ver, crear y editar los clientes.</p>

						<% if (!resultado.isEmpty() && resultado.contentEquals("El cliente se ha creado correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						<% } else if (!resultado.isEmpty() && resultado.contentEquals("Los datos del cliente se han actualizado correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						<% } else if (!resultado.isEmpty() && resultado.contentEquals("Los datos del cliente se han eliminado correctamente.")) {%>
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
							request.getSession().removeAttribute("operacionCliente");
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
												<th>ID Cliente</th>
												<th>NIF</th>
												<th>Empresa</th>
												<th>Contacto</th>
												<th>Dirección</th>
												<th>Fax</th>
												<th>Email</th>
												<th>Fijo</th>
												<th>Acciones</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>ID Cliente</th>
												<th>NIF</th>
												<th>Empresa</th>
												<th>Contacto</th>
												<th>Dirección</th>
												<th>Fax</th>
												<th>Email</th>
												<th>Fijo</th>
												<th>Acciones</th>
											</tr>
										</tfoot>
										<tbody>
											<%
												for (Cliente cliente : listaClientes) {
											%>
											<tr>
												<td><%= cliente.getClientId() %></td>
												<td><%= cliente.getCedulaRuc() %></td>
												<td><%= cliente.getNombreCia() %></td>
												<td><%= cliente.getNombreContacto() %></td>
												<td><%= cliente.getDireccionCli() %></td>
												<td><%= cliente.getFax() %></td>
												<td><%= cliente.getEmail() %></td>
												<td><%= cliente.getFijo() %></td>
												<td>

													<div class="form-row">
														<div class="form-group col-ml-1">
															<a href="clientes?accion=Editar&idCliente=<%=cliente.getClientId() %>" class="btn btn-info btn-circle btn-sm">
																<i class="far fa-pencil"></i>
															</a>
														</div>

														<div class="form-group col-ml-1">
															<form action="clientes" method="post">
																<button type="submit" class="btn btn-info btn-circle btn-sm">
																	<i class="far fa-trash-alt" ></i>
																	<input	type="hidden" name="accion" value="Borrar">
																	<input	type="hidden" name="idCliente" value="<%= cliente.getClientId()%>" >
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
									<a href="clientes?accion=Nuevo" class="btn btn-primary btn-icon-split">
										<span class="icon text-white-50">
											<i class="far fa-plus-circle"></i>
										</span>
										<span class="text">Añadir Cliente</span>
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


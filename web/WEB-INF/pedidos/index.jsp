<%-- 
    Document   : index
    Created on : 21 ago. 2019, 18:14:52
    Author     : Oxker Studio
--%>


<%@page import="com.roxket.jockerdemo.modelos.Orden"%>
<%@page import="com.roxket.jockerdemo.modelos.Producto"%>
<%@page import="com.roxket.jockerdemo.modelos.Proveedor"%>
<%@page import="java.util.List"%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
		List<Orden> listaOrdenes = null;

		if( request.getAttribute("ordenes")!= null){
			listaOrdenes = (List<Orden>) request.getAttribute("ordenes");
		}

		String resultado = "";

		if (request.getAttribute("operacionOrden") != null) {
			resultado = (String) request.getSession().getAttribute("operacionOrden");
		} else if (request.getSession().getAttribute("operacionOrden") != null){
            resultado = (String)request.getSession().getAttribute("operacionOrden");
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
					<jsp:useBean class="com.roxket.jockerdemo.modelos.Orden" id="ordenCreada" scope="session"></jsp:useBean>
						<!-- Begin Page Content -->
						<div class="container-fluid">

							<!-- Page Heading -->
							<h1 class="h3 mb-2 text-gray-800">Ordenes</h1>
							<p class="mb-4">Permite ver ordenes.</p>

						<% if (!resultado.isEmpty() && resultado.contentEquals("La orden se ha creado correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						<% } else if (!resultado.isEmpty() && resultado.contentEquals("Los datos de la orden se han actualizado correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						<% } else if (!resultado.isEmpty() && resultado.contentEquals("Los datos de la orden se han eliminado correctamente.")) {%>
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
						else if (ordenCreada != null ) {%>
<!--						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong>Se ha creado el pedido con folio <%= ordenCreada.getOrdenId() %></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span> 
							</button>
						</div>-->

						<% } 
							request.getSession().removeAttribute("ordenCreada");
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
												<th>ID Orden</th>
                                                <th>Fecha</th>
                                                <th>Cliente</th>
                                                <th>Importe</th>
												<th>Acciones</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>ID Orden</th>
                                                <th>Fecha</th>
                                                <th>Cliente</th>
                                                <th>Importe</th>
												<th>Acciones</th>
											</tr>
										</tfoot>
										<tbody>
                                            <c:forEach items="${ordenes}" var="orden">
                                            <tr>
                                                <td>${orden.ordenId}</td>
                                                <td>${orden.fechaOrden}</td>
                                                <td>${orden.cliente.nombreCia}</td>
                                                <td>${orden.importe}</td>
												<td>
													<div class="form-row">
														<div class="form-group col-ml-1">
															<a href="pedidos?accion=verPedido&idPedido=${orden.ordenId}" class="btn btn-info btn-circle btn-sm">
																<i class="fal fa-eye"></i>
															</a>
														</div>
													</div>
												</td>
                                            </tr> 
                                            </c:forEach>
                                        </tbody>
									</table>
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


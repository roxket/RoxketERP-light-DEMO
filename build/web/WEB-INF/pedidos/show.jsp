<%-- 
    Document   : show
    Created on : 16 sept. 2019, 22:24:14
    Author     : Oxker Studio
--%>

<%@page import="java.text.SimpleDateFormat"%>
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
					<jsp:useBean class="com.roxket.jockerdemo.modelos.Orden" id="orden" scope="request"></jsp:useBean>
						<!-- Begin Page Content -->
						<div class="container-fluid">

							<!-- Page Heading -->
							<h1 class="h3 mb-2 text-gray-800">Pedido</h1>
							<p class="mb-4">Permite ver detalles del pedido.</p>
							<br>
							<div class="form-group col-md-6">
										<a href="pedidos?accion=verPedidos" class="btn btn-primary btn-icon-split">
											<span class="icon text-white-50">
												<i class="fal fa-arrow-square-left"></i>
											</span>
											<span class="text">Volver atrás</span>
										</a>
							</div>
                            <br>

							<!-- DataTales Example -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Pedido ${orden.ordenId}</h6>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-sm-3">
										Fecha del pedido:
										
											<h4><%= new SimpleDateFormat("dd/MM/yyy").format(orden.getFechaOrden())%></h4>
										
									</div>
									<div class="col-sm-3" >
										Vendedor:
										
											<h4>${orden.empleado.nombre} ${orden.empleado.apellido}</h4>
										
									</div>
									<div class="col-sm-3" >
										Cliente:
										
											<h4>${orden.cliente.nombreCia}</h4>
										
									</div>
								</div>
										
<!--								<hr>-->
								<br>

								<table class="table">
                                    <thead>
                                        <tr>
                                            <th>Producto</th>
                                            <th>Unidades</th>
                                            <th>Precio Unitario</th>
                                            <th>Importe</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${orden.detalles}" var="detalle">
                                            <tr>
                                                <td>${detalle.producto.descripcion}</td>
                                                <td>${detalle.cantidad}</td>
                                                <td>${detalle.producto.precioUnit}</td>
                                                <td>${detalle.importe}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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



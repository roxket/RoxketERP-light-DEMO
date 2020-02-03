<%-- 
    Document   : new
    Created on : 13 sept. 2019, 15:30:01
    Author     : Oxker Studio
--%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.roxket.jockerdemo.modelos.Orden"%>
<%@page import="com.roxket.jockerdemo.modelos.Producto"%>
<%@page import="com.roxket.jockerdemo.modelos.Proveedor"%>
<%@page import="java.util.List"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
			<jsp:useBean class="com.roxket.jockerdemo.modelos.Orden" id="orden" scope="session"></jsp:useBean>
				<!-- Content Wrapper -->
				<div id="content-wrapper" class="d-flex flex-column">

					<!-- Main Content -->
					<div id="content">

						<!-- Header Topbar-->
					<jsp:include page="../layouts/header.jsp"></jsp:include>

						<!-- Begin Page Content -->
						<div class="container-fluid">

							<!-- Page Heading -->
							<h1 class="h3 mb-2 text-gray-800">Pedido</h1>
							<p class="mb-4">Permite crear un pedido.</p>

						<% if (!resultado.isEmpty() && resultado.contentEquals("El producto se ha creado correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						<% } else if (!resultado.isEmpty() && resultado.contentEquals("Los datos del producto se han actualizado correctamente.")) {%>
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong><%= resultado%></strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
							
						<% } else if (!resultado.isEmpty() && resultado.contentEquals("Los datos del producto se han eliminado correctamente.")) {%>
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
							request.getSession().removeAttribute("operacionOrden");
						%>

						<!-- DataTales Example -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">Realizar Pedido</h6>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-md-12 panel-primary">
										<div class="content-box-header panel-heading">
									
										</div>
										<div class="content-box-large box-with-header">
											<form action="pedidos" method="post">
												<input type="hidden" name="accion" value="terminarPedido">
												<div class="row">
													<div class="col-sm-6">
														<div class="row">
															<div class="col-sm-6">
																Fecha del pedido:
																<input type="text" name="fechaPedido" class="form-control" placeholder="fecha" value="<%= new SimpleDateFormat("dd/MM/yyy").format(orden.getFechaOrden())%>">
															</div>
														</div>
														<div class="row" style="margin-top: 15px;">
															<div class="col-sm-6">
																Seleccionar empleado:
																<select name="idEmpleado" id="" class="form-control" >
																	<c:forEach items="${empleados}" var="empleado">
																		<option value="${empleado.empleadoId}">${empleado.nombreCompleto}</option>
																	</c:forEach>
																</select>
																
																<div class="invalid-feedback">
																	Descripción no válida
																</div>
															</div>
															<div class="col-sm-6">
																Seleccionar cliente:
																<select name="idCliente" id="" class="form-control">
																	<c:forEach items="${clientes}" var="cliente">
																		<option value="${cliente.clienteId}">${cliente.nombreCia}</option>
																	</c:forEach> 
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-6">
														<div class="col-sm-12" style="text-align: center;">
															<button type="submit" class="btn btn-primary btn-icon-split btn-sm">
																<span class="icon text-primary-white">
																	<i class="far fa-cart-arrow-down"></i>
																	<span class="text">Terminar Pedido</span>
																</span>
															</button>
															
														</div>
														<div class="col-sm-12" style="text-align: center;">
															<p style="font-size: 60px; color: #9F81F7; vertical-align: baseline;">
																${orden.importeRedondeado}
															</p>
														</div>
													</div>
												</div>
											</form>
											<br>
											<hr>
											
											
											
											<div class="row">
												<form action="pedidos" method="post">
													<input type="hidden" name="accion" value="addProducto">
													
													<div class="form-row pt-3 pr-3 pl-3">
														
														<div class="form-group col-md-3">
															<label for="ID">ID Producto</label>
															<input type="text" class="form-control" name="" id="" placeholder="ID Producto">
														</div>
														
														<div class="form-group col-md-3">
															<label for="idProd">Seleccionar</label>
															<select name="idProd" id="" class="form-control" selected="true" required>
																<option value="" selected="true" disabled="true">Seleccionar un producto...</option>
																<c:forEach items="${productos}" var="producto" >
																	<option value="${producto.productoId}">${producto.descripcion}</option>
																</c:forEach>
															</select>
															<div class="invalid-feedback">
																Producto no válido
															</div>
														</div>
														
														<div class="form-group col-md-3">
															<label for="unidades">Unidades</label>
															<input type="number" class="form-control" name="cantProd" id="" placeholder="Unidades" required>
															<div class="invalid-feedback">
																Unidades no válidas
															</div>
														</div>
														
														<div class="form-group col-md-3">
															<br>
															<button type="submit" class="btn btn-primary btn-icon-split btn-sm" style="margin-top: 8px;">
																<span class="icon text-primary-white">
																	<i class="far fa-plus-circle" ></i>
																	<span class="text">Agregar</span>
																</span>
															</button>
														</div>	
													</div>
												</form>
											</div>
											<hr>
											<div class="row">
												<div class="col-sm-12">
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
																	<td>${detalle.producto.descripcion} </td>
																	<td>${detalle.cantidad} </td>
																	<td>${detalle.producto.precioUnit}</td>
																	<td>${detalle.importeRedondeado}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
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



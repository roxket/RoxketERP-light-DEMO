<%-- 
    Document   : form
    Created on : 27 ago. 2019, 13:58:14
    Author     : Oxker Studio
--%>


<%@page import="com.roxket.jockerdemo.modelos.Categoria"%>
<%@page import="com.roxket.jockerdemo.modelos.Producto"%>
<%@page import="com.roxket.jockerdemo.modelos.Proveedor"%>
<%@page import="java.text.SimpleDateFormat"%>

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

	</head>

	<%
		List<Proveedor> listaProveedores = null;

		if( request.getAttribute("proveedores")!= null){
			listaProveedores = (List<Proveedor>) request.getAttribute("proveedores");
		}
		
		List<Categoria> listaCategorias = null;

		if( request.getAttribute("categorias")!= null){
			listaCategorias = (List<Categoria>) request.getAttribute("categorias");

		}
		
		String tipoForm = (String) request.getAttribute("tipoForm");
		
		Producto producto = null;
		if(tipoForm.equals("Actualizar")){
			producto = (Producto) request.getAttribute("producto");
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
							<h1 class="h3 mb-2 text-gray-800"> <%= tipoForm %> productos</h1>
							<p class="mb-4">Configuración de datos del producto.</p>

							<!-- DataTales Example -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Configuración Producto</h6>
								</div>
									<form action="productos" method="post" class="needs-validation" novalidate>
										<div class="form-row pt-3 pr-3 pl-3">
											<div class="form-group col-md-6">
												<input type="hidden" name="accion" value="<%= tipoForm %>">

												<label for="idProducto">ID</label>
												<input type="number" class="form-control" name="idProducto" id="idProducto" placeholder="ID Producto" value="<% if(tipoForm.equals("Actualizar")){out.print(producto.getProductoId());}%>" required <% if(tipoForm.equals("Actualizar")){out.print("readonly");}%>>
												<div class="invalid-feedback">
													ID no válido
												</div>
											</div>
											<div class="form-group col-md-6">
												<label for="precioUnit">Proveedor</label>
												<select class="form-control" name="idProveedor" id="idProveedor">
													<% for (Proveedor prov : listaProveedores) { %>
													<option value="<% out.print(prov.getProveedorId());%>" 
															<% 
																if (tipoForm.equals("Actualizar")) {
																	if (prov.getProveedorId() == producto.getProveedor().getProveedorId()) {
																		out.print("selected");} else{out.print("");
																	}
																}
															%> > <% out.print(prov.getNombreProv()); %> 
													</option>
													<% } %>
												</select>
												<div class="invalid-feedback">
													Proveedor no válido
												</div>
											</div>
										</div>
										
										<div class="form-row pt-3 pr-3 pl-3">
											
											<div class="form-group col-md-6">
												<label for="precioUnit">Categoria</label>
												<select class="form-control" name="idCategoria" id="idCategoria">
													<% for (Categoria cat : listaCategorias) { %>
													<option value="<% out.print(cat.getCategoriaId());%>" 
															<% if(tipoForm.equals("Actualizar")){
																if (cat.getCategoriaId() == producto.getCategoria().getCategoriaId()) { 
																	out.print("selected");
																}
															}
															%> > <% out.print(cat.getNombreCat()); %> </option>
													<% } %>
												</select>
												<div class="invalid-feedback">
													Categoría no válida
												</div>
											</div>
												
											
											<div class="form-group col-md-6">
												<label for="precioUnit">Precio unitario</label>
												<input type="number" step="0.01" class="form-control" name="precioUnit" id="precioUnit" placeholder="Precio unitario" value="<% if(tipoForm.equals("Actualizar")){out.print(producto.getPrecioUnit());}%>" required >
												<div class="invalid-feedback">
													Precio no válido
												</div>
											</div>
										</div>
												
										<div class="form-row pt-3 pr-3 pl-3">
											<div class="form-group col-md-6">
												<label for="existencia">Existencias</label>
												<input type="number" class="form-control" name="existencia" id="existencia" placeholder="Existencias" value="<% if(tipoForm.equals("Actualizar")){out.print(producto.getExistencia());}%>" required >
												<div class="invalid-feedback">
													Existencias no válida
												</div>
											</div>
										</div>
									
										<div class="form-row pl-3 pr-3">
											<div class="form-group col-md-12">
												<label for="descripcion">Descripción</label>
												<textarea class="form-control" name="descripcion" id="descripcion" rows="3" required>
														<% if(tipoForm.equals("Actualizar")){out.print(producto.getDescripcion());}%>" 
												</textarea>
												<div class="invalid-feedback">
													Descripción no válida
												</div>
												<br/>
												<button type="submit" class="btn btn-primary btn-icon-split">
												<span class="icon text-primary-white">
													<i class="<% if(tipoForm.equals("Actualizar")){out.print("far fa-pencil");} else if(tipoForm.equals("Crear")){out.print("far fa-plus-circle");}%>" ></i>
													<span class="text"> <%= tipoForm %> </span>
												</span>
												</button>
											</div>
										</div>
									</form>
								
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
		
		<script>
			// Example starter JavaScript for disabling form submissions if there are invalid fields
			(function() {
			  'use strict';
			  window.addEventListener('load', function() {
				// Fetch all the forms we want to apply custom Bootstrap validation styles to
				var forms = document.getElementsByClassName('needs-validation');
				// Loop over them and prevent submission
				var validation = Array.prototype.filter.call(forms, function(form) {
				  form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
					  event.preventDefault();
					  event.stopPropagation();
					}
					form.classList.add('was-validated');
				  }, false);
				});
			  }, false);
			})();
		</script>

	</body>

</html>



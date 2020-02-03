<%-- 
    Document   : sidebar
    Created on : 21 ago. 2019, 17:02:49
    Author     : Oxker Studio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fal fa-rocket"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Roxket <sup>demo</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="#">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Mission Control</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        ERP
    </div>

    <!-- Nav Item - Categorias Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
            <i class="fas fa-fw fa-cog"></i>
            <span>Categorias</span>
        </a>
        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header"></h6>
                <a class="collapse-item" href="/Roxket/categorias">Listado</a>
                <a class="collapse-item" href="/Roxket/categorias?accion=Nuevo">Nuevo</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Proveedores Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-fw fa-cog"></i>
            <span>Proveedores</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header"></h6>
                <a class="collapse-item" href="/Roxket/proveedores">Listado</a>
                <a class="collapse-item" href="/Roxket/proveedores?accion=Nuevo">Nuevo</a>
                <a class="collapse-item" href="#">Informes</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Productos Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
            <i class="fas fa-fw fa-cog"></i>
            <span>Stock</span>
        </a>
        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header"></h6>
                <a class="collapse-item" href="/Roxket/productos">Listado</a>
                <a class="collapse-item" href="/Roxket/productos?accion=Nuevo">Nuevo</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Empleados Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFour" aria-expanded="true" aria-controls="collapseFour">
            <i class="fas fa-fw fa-cog"></i>
            <span>Empleados</span>
        </a>
        <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header"></h6>
                <a class="collapse-item" href="/Roxket/empleados">Listado</a>
                <a class="collapse-item" href="/Roxket/empleados?accion=Nuevo">Nuevo</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        CRM
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFive" aria-expanded="true" aria-controls="collapseFive">
            <i class="fas fa-fw fa-folder"></i>
            <span>Clientes</span>
        </a>
        <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header"></h6>
                <a class="collapse-item" href="/Roxket/clientes">Listado</a>
                <a class="collapse-item" href="/Roxket/clientes?accion=Nuevo">Nuevo</a>
                <a class="collapse-item" href="#">Informe</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Pedidos Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSix" aria-expanded="true" aria-controls="collapseSix">
            <i class="fas fa-fw fa-cog"></i>
            <span>Pedidos</span>
        </a>
        <div id="collapseSix" class="collapse" aria-labelledby="headingSix" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header"></h6>
                <a class="collapse-item" href="/Roxket/pedidos">Listado</a>
                <a class="collapse-item" href="/Roxket/pedidos?accion=Nuevo">Nuevo</a>
                <a class="collapse-item" href="#">Informes</a>
            </div>
        </div>
    </li>
    
    <!-- Divider -->
    <hr class="sidebar-divider">
    
     <!-- Heading -->
    <div class="sidebar-heading">
        Flotas
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSeven" aria-expanded="true" aria-controls="collapseSeven">
            <i class="fas fa-fw fa-folder"></i>
            <span>Vehiculos</span>
        </a>
        <div id="collapseSeven" class="collapse" aria-labelledby="headingSeven" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header"></h6>
                <a class="collapse-item" href="#">Listado</a>
                <a class="collapse-item" href="#">Nuevo</a>
                <a class="collapse-item" href="#">Informe</a>
                <a class="collapse-item" href="#">GPS Tracking</a>
            </div>
        </div>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider">
</ul>
<!-- End of Sidebar -->

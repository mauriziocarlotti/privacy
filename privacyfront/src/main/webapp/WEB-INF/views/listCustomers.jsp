<div class="container">
	<div class="row">
		<!-- Sidebar -->
		<div class="col-md-3">
			<%@ include file="./shared/sidebar.jsp"%>
		</div>
		<!-- Customers -->
		<div class="col-md-9">
			<!-- Aggiunta di un breadcrumb -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllCustomers == true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Tutti i clienti</li>
						</ol>
					</c:if>
					<c:if test="${userClickCategoryCustomers == true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Categoria</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
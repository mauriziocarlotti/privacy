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
						<script>
							window.categoryId = '';
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Tutti i clienti</li>
						</ol>
					</c:if>
					<c:if test="${userClickCategoryCustomers == true}">
						<script>
							window.categoryId = '${category.id}';
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Categoria</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">

					<table id="customerListTable"
						class="table table-striped table-borderd">
						<thead>
							<tr>
								<th>Nome</th>
 								<th>Cognome</th>
								<th>Mail</th>
								<th>Descrizione</th>
								<th></th>
							</tr>
						</thead>

						<tfoot>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Mail</th>
								<th>Descrizione</th>
								<th></th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
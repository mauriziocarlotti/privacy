<div class="container">

	<!--  Breadcrumb -->
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/customers">Customers</a></li>
				<li class="active">${customer.last_name}</li>

			</ol>

		</div>

	</div>
</div>

<div class="row">
	<!-- Display customer image -->
	<div class="col-xs-12 col-sm-4">

		<div class="thumbnail">

		</div>

	</div>

	<!-- Display customer description -->
	<div class="col-xs-12 col-sm-8">

		<h3>${customer.last_name}</h3>
		<hr />
		<p>${customer.description}</p>
		<hr />
		<a href="${contextRoot}/show/all/customers" class="btn btn-success">Back</a>

	</div>

</div>
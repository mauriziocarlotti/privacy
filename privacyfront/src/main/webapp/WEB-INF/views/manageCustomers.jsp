<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
		<c:if test="${not empty message}">

			<div class="col-xs-12">
				<div class="alert alert-success alert-disissible">
					<button type="button" class="clos" data-dismission="alert">&times;</button>
					${message}

				</div>

			</div>
		</c:if>

		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<h4>Gestione clienti</h4>


				</div>
				<div class="panel-body">

					<!--  FORM ELEMENTS -->
					<sf:form class="form-horizontal" modelAttribute="customer"
						action="${contextRoot}/manage/customers" method="POST">

						<div class="form-group">
							<label class="control-label col-md-4" for="first_name">Nome:
							</label>
							<div class="col-md-8">
								<sf:input type="text" path="first_name" id="first_name"
									placeholder="Nome cliente" class="form-control" />
								<em class="help-block">Inserire il nome del cliente</em>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="first_name">Cognome:
							</label>
							<div class="col-md-8">
								<sf:input type="text" path="last_name" id="last_name"
									placeholder="Cognome cliente" class="form-control" />
								<em class="help-block">Inserire il cognome del cliente</em>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="email">Mail: </label>
							<div class="col-md-8">
								<sf:input type="text" path="email" id="email"
									placeholder="Cognome cliente" class="form-control" />
								<em class="help-block">Inserire la mail</em>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="description">Descrizione:
							</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4"
									placeholder="Breve descrizione" class="form-control" />
								<em class="help-block">Inserire una breve descrizione</em>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Categoria:
							</label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId"
									path="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" path="submit" id="submit" value="Submit" />
								<!-- Hidden -->
								<sf:hidden path="id" />
								<sf:hidden path="active" />
								<sf:hidden path="codice" />
								<sf:hidden path="contact_number" />
								<sf:hidden path="supplierId" />
							</div>
						</div>

					</sf:form>


				</div>


			</div>


		</div>

	</div>

</div>
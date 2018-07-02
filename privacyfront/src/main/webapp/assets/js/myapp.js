$(function() {
	// for adding a loader
	$(window).load(function(){
		setTimeout(function() {
			$(".se-pre-con").fadeOut("slow");
		}, 500);			
	});	
	
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if((token!=undefined && header !=undefined) && (token.length > 0 && header.length > 0)) {		
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
	
	// solving the menu problem
	switch (menu) {

	case 'Chi siamo':
		$('#about').addClass('active');
		break;
	case 'Contatti':
		$('#contact').addClass('active');
		break;
	case 'Tutti i clienti':
		$('#listCustomers').addClass('active');
		break;
	case 'Gestione clienti':
		$('#manageCustomers').addClass('active');
		break;
	case 'Shopping Cart':
		$('#userModel').addClass('active');
		break;		
	default:
		if (menu == "Home")
			break;
		$('#listCustomers').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for jquery
	// create a dataset
	var $table = $('#customerListTable');

	var jsonUrl = '';
	if (window.categoryId == '') {
		jsonUrl = window.contextRoot + '/json/data/all/customers';
	} else {
		jsonUrl = window.contextRoot + '/json/data/category/'
				+ window.categoryId + '/customers';
	}

	// code for jquery dataTable
	var $table = $('#customerListTable');

	// execute
	if ($table.length) {
		// console.log('Interno tabella');
		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/customers';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/customers';
		}

		$table
				.DataTable({

					lengthMenu : [
							[ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records',
									'All records' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'first_name'
							},
							{
								data : 'last_name'
							},
							{
								data : 'email',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'description'
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/customer" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
									if(userRole !== 'ADMIN') {
										if (row.quantity < 1) {
											str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										} else {
	
											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										}
									}
									else {
										str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/customer" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
									}
									return str;

								}
							}, ]

				});
	}
	// list of all products for admin
	var $productsTable = $('#customersTable');
	
	
	if($productsTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/all/customers';
		console.log(jsonUrl);
		
		$productsTable.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{data: 'id'},

								{
									data : 'first_name'
								},
								{
									data : 'last_name'
								},
								{
									data : 'email',
									mRender : function(data, type, row) {
										return '&#8377; ' + data
									}
								},
								{
									data : 'description'
								},

								{
									data : 'active',
									bSortable : false,
									mRender : function(data, type, row) {
										var str = '';
										if(data) {											
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'" checked="checked">  <div class="slider round"> </div></label>';
											
										}else {
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'">  <div class="slider round"> </div></label>';
										}
										
										return str;
									}
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/customer" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

										return str;
									}
								}					           	
					],
					
					
					initComplete: function () {
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change' , function() {							
							var dText = (this.checked)? 'You want to activate the Customer?': 'You want to de-activate the Customer?';
							var checked = this.checked;
							var checkbox = $(this);
							debugger;
						    bootbox.confirm({
						    	size: 'medium',
						    	title: 'Customer Activation/Deactivation',
						    	message: dText,
						    	callback: function (confirmed) {
							        if (confirmed) {
							            $.ajax({							            	
							            	type: 'GET',
							            	url: window.contextRoot + '/manage/customer/'+checkbox.prop('value')+'/activation',
							        		timeout : 100000,
							        		success : function(data) {
							        			bootbox.alert(data);							        										        			
							        		},
							        		error : function(e) {
							        			bootbox.alert('ERROR: '+ e);
							        			//display(e);
							        		}						            	
							            });
							        }
							        else {							        	
							        	checkbox.prop('checked', !checked);
							        }
						    	}
						    });																											
						});
							
					}
				});
	}
	
	
	
	
	// jQuery Validation Code

	//methods required for validation
	
	function errorPlacement(error, element) {
		// Add the 'help-block' class to the error element
		error.addClass("help-block");
		
		// add the error label after the input element
		error.insertAfter(element);
		
		
		// add the has-feedback class to the
		// parent div.validate in order to add icons to inputs
		element.parents(".validate").addClass("has-feedback");	

	}	
	
	
	
	// validating the product form element	
	// fetch the form element
	$categoryForm = $('#categoryForm');
	
	if($categoryForm.length) {
		
		$categoryForm.validate({			
				rules: {
					name: {
						required: true,
						minlength: 3
					},
					description: {
						required: true,
						minlength: 3					
					}				
				},
				messages: {					
					name: {
						required: 'Please enter customer name!',
						minlength: 'Please enter atleast five characters'
					},
					description: {
						required: 'Please enter customer name!',
						minlength: 'Please enter atleast five characters'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					errorPlacement(error, element);
				}				
			}
		
		);
		
	}
	
	/*validating the loginform*/
	
	// validating the product form element	
	// fetch the form element
	$loginForm = $('#loginForm');
	
	if($loginForm.length) {
		
		$loginForm.validate({			
				rules: {
					username: {
						required: true,
						email: true
						
					},
					password: {
						required: true
					}				
				},
				messages: {					
					username: {
						required: 'Please enter your email!',
						email: 'Please enter a valid email address!'
					},
					password: {
						required: 'Please enter your password!'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					// Add the 'help-block' class to the error element
					error.addClass("help-block");
					
					// add the error label after the input element
					error.insertAfter(element);
				}				
			}
		
		);
		
	}
		
	
	
	/*------*/
	/* for fading out the alert message after 3 seconds */
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)

	}

	// ---------------------------------

	$('.switch input[type="checkbox"]')
			.on(
					'change',
					function() {

						var checkbox = $(this);
						var checked = checkbox.prop('checked');
						var dMsg = (checked) ? 'Vuoi attivare il cliente?'
								: 'Vuoi disabilitare il cliente?';
						var value = checkbox.prop('value');

						bootbox
								.confirm({
									size : 'medium',
									title : 'Attivazione & disabilitazione clienti',
									message : dMsg,
									callback : function(confirmed) {
										if (confirmed) {
											console.log(value);
											bootbox
													.alert({
														size : 'medium',
														title : 'Informazioni',
														message : 'Stai per effettuare un operazione sul cliente'
																+ value
													});
										} else {
											checkbox.prop('checked', !checked);
										}
									}

								});

					});

	// ---------------------------------------
	// data table for admin
	// ---------------------------------------

	var $adminCustomersTable = $('#adminCustomersTable');

	if ($adminCustomersTable.length) {

		var jsonUrl = window.contextRoot + '/json/data/admin/all/customers';

		$adminCustomersTable
				.DataTable({

					lengthMenu : [
							[ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records',
									'All records' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'first_name'
							},
							{
								data : 'last_name'
							},
							{
								data : 'email'
							},
							{
								data: 'active',
								bSortable: false,
								mRender: function(data, type, row) {
									var str = '';
									str += '<label class="switch"> ';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '"></input>';

									} else {
										str += '<input type="checkbox" value="'
												+ row.id + '"></input>';
									}
									str += '<div class="slider"></div></label>';

									return str;
								}
							},

							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'+window.contextRoot+'/manage/'
											+ data
											+ '/customer" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';
									return str;
								}
							} 
							],
						initComplete: function() {
							var api = this.api();
							api.$('.switch input[type="checkbox"]')
							.on(
									'change',
									function() {

										var checkbox = $(this);
										var checked = checkbox.prop('checked');
										var dMsg = (checked) ? 'Vuoi attivare il cliente?'
												: 'Vuoi disabilitare il cliente?';
										var value = checkbox.prop('value');

										bootbox
												.confirm({
													size : 'medium',
													title : 'Attivazione & disabilitazione clienti',
													message : dMsg,
													callback : function(confirmed) {
														if (confirmed) {
															console.log(value);
															
															var activationUrl = window.contextRoot + '/manage/customer/' + value + '/activation';
															$.post(activationUrl, function(data) {
																													
															bootbox
																	.alert({
																		size : 'medium',
																		title : 'Informazioni',
																		message : data
																	});
															});
														} else {
															checkbox.prop('checked', !checked);
														}
													}

												});

									});

						}

				});
	}
// ----------------------------------
	// validation code for category
	
	
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length) {
		$categoryForm.validate({
			rules : {
				name : {
					required: true,
					minlength: 2
				},
				description : {
					required: true
				}
				
			},
			messages : {
				name : {
					required: 'Inserire il nome della categoria',
					minlength: 'Il nome della categoria dovrà essere alme 2 caratteri'
				},
				description : {
					required: 'Aggiungere la descrizione della categoria'
				}
			},
			errorElement: 'em',
			errorPlacement: function(error, element) {
				// add the class of help-block
				error.addClass('help-block');
				// add the error element after the input element
				error.insertAfter(element);
			}
		});
	}
	
	// -------------------------------------
	
});

$(function() {
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
	default:
		if(menu == "Home") break;
		$('#listCustomers').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	
	// code for jquery
	// create a dataset
	var $table = $('#customerListTable');

	var jsonUrl = '';
	if(window.categoryId == '') {
		jsonUrl = window.contextRoot + '/json/data/all/customers';
	} else {
		jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/customers';
	}

	// execute
	if($table.length) {
		// console.log('Interno tabella');
		$table.DataTable( {
			
			lengthMenu: [[3,5,10,-1],['3 Records','5 Records','10 Records', 'All records']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'first_name'
				},
				{
					data: 'last_name'
				},
				{
					data: 'email',
					mRender: function(data, type, row) {
						return '&#8377; ' + data
					}
				},
				{
					data: 'description'
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						var str = '';
						str +='<a href="'+window.contextRoot + '/show/'+data+'/customer" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
						return str;
					}
				},
			]
			
		});
	}
	
	// dismissing the alert after 3 seconds
	var $alert = $('.alert');
	if($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
		
	}

	// ---------------------------------
	
	$('.switch input[type="checkbox"]').on('change',function() {
		
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked)?'Vuoi attivare il cliente?':
			                 'Vuoi disabilitare il cliente?';
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			size: 'medium',
			title: 'Attivazione & disabilitazione clienti',
			message: dMsg,
			callback: function(confirmed) {
				if(confirmed) {
					console.log(value);
					bootbox.alert({
						size: 'medium',
						title: 'Informazioni',
						message: 'Stai per effettuare un operazione sul cliente' + value
					});
				} else {
					checkbox.prop('checked',!checked);
				}
			}
		
		});
		
	});		
	
});
	

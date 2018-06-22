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
	default:
		if(menu == "Home") break;
		$('#listCustomers').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	
	// code for jquery
	// create a dataset
	var customers = [
		['1','abc'],
		['2','due'],
		['3','tre'],
		['4','quattro'],
		['5','cinque'],
		['6','sei']
		
	];
	var $table = $('#customerListTable');
	
	// execute
	if($table.length) {
		 console.log('Interno tabella');
		$table.DataTable( {
			
			lengthMenu: [[3,5,10,-1],['3 Records','5 Records','10 Records', 'All records']],
			pageLength: 5,
			data: customers
			
		});
	}
	
});
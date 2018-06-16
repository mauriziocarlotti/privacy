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
		$('#listCustomers').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
});
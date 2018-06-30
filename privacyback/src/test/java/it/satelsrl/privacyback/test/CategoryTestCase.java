package it.satelsrl.privacyback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.satelsrl.privacyback.dao.CategoryDAO;
import it.satelsrl.privacyback.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private static Category category;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("it.satelsrl.privacyback");
		context.refresh();

		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
//	@Test
//	public void testAddCategory() {
//		category = new Category();
//		
//		// prima riga
//		category.setName("Completa");
//		category.setDescription("Anagrafica completa");
//		category.setImageURL("CAT_1.png");
//		category.setActive(true);
//		
//		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
//
//		
//	}
//	@Test
//	public void testGetCategory() {
//		
//		category = categoryDAO.get(1);
//		assertEquals("Successfully fetched a single category from table category!","Completa",category.getName());
//	}

//	@Test
//	public void testUpdateCategory() {
//		
//		category = categoryDAO.get(1);
//		
//		category.setName("Privacy per web");
//		assertEquals("Successfully update a single category from table category!",true,categoryDAO.update(category));
//	}

//	@Test
//	public void testDeleteCategory() {
//		
//		category = categoryDAO.get(1);
//		
//		assertEquals("Successfully delete a single category from table category!",true,categoryDAO.delete(category));
//	}
	
//	@Test
//	public void testActiveCategory() {
//		
//		assertEquals("Successfully fetched the list categories from table category!",0,categoryDAO.list().size());
//	}
	
	@Test
	public void testCRUDCategory() {
		
		category = new Category();
		
		category.setName("Pubblica");
		category.setDescription("Questa è la categoria pubblica");
		category.setImageURL("CAT_20.png");
		
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.addCategory(category));
		
		category = new Category();
		category.setName("Lite");
		category.setDescription("Questa è la categoria lite");
		category.setImageURL("CAT_30.png");
		
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.addCategory(category));
		
		// fetching and updating the category
		
		category = categoryDAO.get(2);
		
		category.setName("Privacy per pubblicità");
		assertEquals("Successfully update a single category from table category!",true,categoryDAO.updateCategory(category));
		
		// delete category
		
		assertEquals("Successfully delete a single category from table category!",true,categoryDAO.deleteCategory(category));
		
		// fetching the list
		assertEquals("Successfully fetched the list categories from table category!",1,categoryDAO.list().size());
	}
}

/**
 * 
 */
package it.satelsrl.privacyback.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import it.satelsrl.privacyback.dao.CategoryDAO;
import it.satelsrl.privacyback.dto.Category;

/**
 * @author Maurizio Carlotti
 *
 */
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<Category>();

	static {
		Category category = new Category();
		// prima riga
		category.setId(1);
		category.setName("Completa");
		category.setDescription("Anagrafica completa");
		category.setImageURL("");
		category.setActive(true);

		categories.add(category);

		category = new Category();
		// seconda riga
		category.setId(2);
		category.setName("Solo nominativo");
		category.setDescription("Solo nominativo del ");
		category.setImageURL("");
		category.setActive(true);

		categories.add(category);

		category = new Category();
		// terza riga
		category.setId(3);
		category.setName("Normativa web");
		category.setDescription("Vengono richiesti i dati da web");
		category.setImageURL("");
		category.setActive(true);

		categories.add(category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.satelsrl.privacyback.dao.CategoryDAO#list()
	 */

	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	
	public Category get(int id) {

		for(Category category : categories) {
			if(category.getId() == id) return category;
		}
		return null;
	}

}

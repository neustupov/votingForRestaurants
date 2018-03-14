package ru.neustupov.votingforrestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.neustupov.votingforrestaurants.model.Menu;
import ru.neustupov.votingforrestaurants.repository.MenuRepository;

import java.util.List;

@Repository
public class DataJpaMenuRepositoryImpl implements MenuRepository {

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Transactional
    @Override
    public Menu save(Menu menu, int restId) {
        if (!menu.isNew() && get(menu.getId(), restId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restId));
        return crudMenuRepository.save(menu);
    }

    @Transactional
    @Override
    public boolean delete(int id, int restId) {
        return crudMenuRepository.delete(id, restId) != 0;
    }

    @Override
    public Menu get(int id, int restId) {
        return crudMenuRepository.get(id, restId).orElse(null);
    }

    @Override
    public List<Menu> getAll(int restId) {
        return crudMenuRepository.getAll(restId);
    }

    @Override
    public Menu getWithRestaurant(int id) {
        return crudMenuRepository.getWithRestaurant(id);
    }

    @Override
    public Menu getWithMeals(int id) {
        return crudMenuRepository.getWithMeals(id);
    }

    @Override
    public Menu getWithRestaurantAndMeals(int id) {
        return crudMenuRepository.getWithRestaurantAndMeals(id);
    }
}

package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.model.Menu;
import ru.neustupov.votingForRestaurants.repository.MenuRepository;

import java.util.List;

@Repository
public class DataJpaMenuRepositoryImpl implements MenuRepository {

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Override
    public Menu save(Menu menu, int restId) {
        return crudMenuRepository.save(menu);
    }

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
        return crudMenuRepository.findAllByIdRest(restId);
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

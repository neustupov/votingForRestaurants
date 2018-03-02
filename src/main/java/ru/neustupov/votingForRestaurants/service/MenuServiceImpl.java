package ru.neustupov.votingForRestaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.neustupov.votingForRestaurants.model.Menu;
import ru.neustupov.votingForRestaurants.repository.MenuRepository;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

import static ru.neustupov.votingForRestaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repository;


    @Autowired
    public MenuServiceImpl(MenuRepository repository) {
        this.repository = repository;
    }

    @Override
    public Menu create(Menu menu, int restId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restId);
    }

    @Override
    public void delete(int id, int restId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restId), id);
    }

    @Override
    public Menu get(int id, int restId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restId), id);
    }

    @Override
    public void update(Menu menu, int restId) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(repository.save(menu, restId), menu.getId());
    }

    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Menu> getAllByIdRest(int idRest) {
        return repository.getAllByRestId(idRest);
    }

    @Override
    public Menu getWithMeals(int id) {
        return checkNotFoundWithId(repository.getWithMeals(id), id);
    }

    @Override
    public Menu getWithRestaurant(int id) {
        return checkNotFoundWithId(repository.getWithRestaurant(id), id);
    }

    @Override
    public Menu getWitMealsAndRestaurant(int id) {
        return checkNotFoundWithId(repository.getWithRestaurantAndMeals(id), id);
    }
}

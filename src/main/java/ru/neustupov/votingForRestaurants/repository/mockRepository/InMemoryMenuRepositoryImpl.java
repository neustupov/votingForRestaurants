package ru.neustupov.votingForRestaurants.repository.mockRepository;

import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.model.Menu;
import ru.neustupov.votingForRestaurants.repository.MenuRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryMenuRepositoryImpl implements MenuRepository {

    private Map<Integer, Map<Integer, Menu>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new Menu(2, LocalDateTime.of(2015, Month.JUNE, 1, 10, 0)),
                2);
        save(new Menu(3, LocalDateTime.of(2015, Month.JUNE, 2, 10, 0)),
                3);
        save(new Menu(1, LocalDateTime.of(2015, Month.MAY, 29, 10, 0)),
                1);
        save(new Menu(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0)),
                1);
        save(new Menu(4, LocalDateTime.of(2015, Month.MAY, 29, 10, 0)),
                4);
    }

    @Override
    public Menu save(Menu menu, int restId) {
        Map<Integer, Menu> menus = repository.computeIfAbsent(restId, ConcurrentHashMap::new);
        if (menu.isNew()) {
            menu.setId(counter.incrementAndGet());
            menus.put(menu.getId(), menu);
            return menu;
        }
        return menus.computeIfPresent(menu.getId(), (id, oldMenu) -> menu);
    }

    @Override
    public boolean delete(int id, int restId) {
        Map<Integer, Menu> menus = repository.get(restId);
        return menus != null && menus.remove(id) != null;
    }

    @Override
    public Menu get(int id, int restId) {
        Map<Integer, Menu> menus = repository.get(restId);
        return menus == null ? null : menus.get(id);
    }

    @Override
    public List<Menu> getAll(int restId) {
        return getAllAsStream(restId).collect(Collectors.toList());
    }

    @Override
    public Menu getWithRestaurant(int id) {
        return null;
    }

    @Override
    public Menu getWithMeals(int id) {
        return null;
    }

    @Override
    public Menu getWithRestaurantAndMeals(int id) {
        return null;
    }

    private Stream<Menu> getAllAsStream(int restId) {
        Map<Integer, Menu> menus = repository.get(restId);
        return menus == null ?
                Stream.empty() :
                menus.values().stream()
                        .sorted(Comparator.comparing(Menu::getId));
    }
}

package ru.neustupov.votingForRestaurants.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.neustupov.votingForRestaurants.model.Menu;
import ru.neustupov.votingForRestaurants.service.MenuService;

import java.util.List;

import static ru.neustupov.votingForRestaurants.util.ValidationUtil.assureIdConsistent;
import static ru.neustupov.votingForRestaurants.util.ValidationUtil.checkNew;

@Controller
public class MenuRestController {

    private static final Logger log = LoggerFactory.getLogger(MenuRestController.class);

    private MenuService service;

    @Autowired
    public MenuRestController(MenuService service) {
        this.service = service;
    }

    public Menu get(int id, int restId) {
        log.info("get menu {} for restaurant {}", id, restId);
        return service.get(id, restId);
    }

    public void delete(int id, int restId) {
        log.info("delete menu {} for restaurant {}", id, restId);
        service.delete(id, restId);
    }

    public List<Menu> getAll(int restId) {
        log.info("getAll menus for restaurant {}", restId);
        return service.getAll(restId);
    }

    public Menu create(Menu menu, int restId) {
        checkNew(menu);
        log.info("create {} for restaurant {}", menu, restId);
        return service.create(menu, restId);
    }

    public void update(Menu menu, int restId) {
        assureIdConsistent(menu, restId);
        log.info("update {} for restaurant {}", menu, restId);
        service.update(menu, restId);
    }
}

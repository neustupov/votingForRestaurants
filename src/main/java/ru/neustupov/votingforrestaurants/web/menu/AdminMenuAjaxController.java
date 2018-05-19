package ru.neustupov.votingforrestaurants.web.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neustupov.votingforrestaurants.model.Menu;
import ru.neustupov.votingforrestaurants.util.exception.IllegalRequestDataException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/admin/menus")
public class AdminMenuAjaxController extends AbstractMenuController {

    @Autowired
    private MessageSource messageSource;

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable("id") int id,
                    @RequestParam("restId") int restId) {
        return super.get(id, restId);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAll(@RequestParam("restId") int restId) {
        return super.getAll(restId);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id,
                       @RequestParam("restId") int restId) {
        super.delete(id, restId);
    }

    @PostMapping
    public void create(@RequestParam("restId") int restId) {
        try {
            super.create(new Menu(Date.valueOf(LocalDate.now())), restId);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalRequestDataException(messageSource.getMessage(EXCEPTION_DUPLICATE_TODAYS_MENU,
                    null, LocaleContextHolder.getLocale()));
        }
    }
}

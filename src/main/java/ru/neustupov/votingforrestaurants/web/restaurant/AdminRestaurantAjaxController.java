package ru.neustupov.votingforrestaurants.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.neustupov.votingforrestaurants.View;
import ru.neustupov.votingforrestaurants.model.Restaurant;
import ru.neustupov.votingforrestaurants.to.RestaurantWithVotes;
import ru.neustupov.votingforrestaurants.util.ControllerUtil;
import ru.neustupov.votingforrestaurants.util.exception.IllegalRequestDataException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/admin/restaurants")
public class AdminRestaurantAjaxController extends AbstractRestaurantController {

    @Autowired
    private MessageSource messageSource;

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithVotes> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Validated(View.Web.class) Restaurant restaurant) {
        try {
            if (restaurant.isNew()) {
                super.create(restaurant);
            } else {
                super.update(restaurant, restaurant.getId());
            }
        } catch (DataIntegrityViolationException e) {
            throw new IllegalRequestDataException(messageSource.getMessage(EXCEPTION_DUPLICATE_NAME_RESTAURANT,
                    null, LocaleContextHolder.getLocale()));
        }
    }
}

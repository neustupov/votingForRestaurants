package ru.neustupov.votingforrestaurants;

import ru.neustupov.votingforrestaurants.model.Menu;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.neustupov.votingforrestaurants.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {

    public static final int RUSSIA_MENU_ID1 = START_SEQ + 7;
    public static final int UKRAINE_MENU_ID = START_SEQ + 8;
    public static final int U_KOLYANA_MENU_ID = START_SEQ + 9;
    public static final int ALMAZ_MENU_ID = START_SEQ + 10;
    public static final int FART_MENU_ID = START_SEQ + 11;
    public static final int RUSSIA_MENU_ID2 = START_SEQ + 12;

    public static final Menu RUSSIA_MENU1 = new Menu(RUSSIA_MENU_ID1, of(2015, Month.MAY, 1, 8, 0));
    public static final Menu UKRAINE_MENU = new Menu(UKRAINE_MENU_ID, of(2015, Month.MAY, 2, 9, 0));
    public static final Menu U_KOLYANA_MENU = new Menu(U_KOLYANA_MENU_ID, of(2015, Month.MAY, 3, 10, 0));
    public static final Menu ALMAZ_MENU = new Menu(ALMAZ_MENU_ID, of(2015, Month.MAY, 1, 8, 0));
    public static final Menu FART_MENU = new Menu(FART_MENU_ID, of(2015, Month.MAY, 2, 7, 0));
    public static final Menu RUSSIA_MENU2 = new Menu(RUSSIA_MENU_ID2, of(2015, Month.MAY, 2, 8, 0));

    public static final List<Menu> MENUS = Arrays.asList(RUSSIA_MENU1, UKRAINE_MENU, U_KOLYANA_MENU, ALMAZ_MENU, FART_MENU, RUSSIA_MENU2);

    public static Menu getCreated() {
        return new Menu(null, of(2015, Month.JUNE, 1, 18, 0));
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "meals", "restaurant");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("meals", "restaurant").isEqualTo(expected);
    }
}

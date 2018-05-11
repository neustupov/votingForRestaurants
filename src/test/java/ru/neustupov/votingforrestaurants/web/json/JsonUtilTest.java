package ru.neustupov.votingforrestaurants.web.json;

import org.junit.Assert;
import org.junit.Test;
import ru.neustupov.votingforrestaurants.UserTestData;
import ru.neustupov.votingforrestaurants.model.Meal;
import ru.neustupov.votingforrestaurants.model.User;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static ru.neustupov.votingforrestaurants.MealTestData.APPLE;
import static ru.neustupov.votingforrestaurants.MealTestData.MEALS;
import static ru.neustupov.votingforrestaurants.MealTestData.assertMatch;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(APPLE);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        assertMatch(meal, APPLE);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(MEALS);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        assertMatch(meals, MEALS);
    }

    @Test
    public void testWriteOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.USER);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.USER, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        Assert.assertEquals(user.getPassword(), "newPass");
    }
}
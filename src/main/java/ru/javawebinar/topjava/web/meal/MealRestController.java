package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, SecurityUtil.authUserId());
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        return getAll("", "", "", "");
    }

    public List<MealTo> getAll(String startDate, String startTime, String endDate, String endTime) {
        log.info("getAll startDate={}, startTime={}, endDate={}, endTime={}", startDate, startTime, endDate, endTime);
        LocalDate saveStartDate = startDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate saveEndDate = endDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDate);
        LocalTime saveStartTime = startTime.isEmpty() ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime saveEndTime = endTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(endTime);
        return MealsUtil.getTos(
                service.getAll(SecurityUtil.authUserId(), saveStartDate, saveStartTime, saveEndDate, saveEndTime),
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void update(Meal meal, int mealId) {
        log.info("update {} with id={}", meal, mealId);
        service.update(meal, mealId, SecurityUtil.authUserId());
    }
}
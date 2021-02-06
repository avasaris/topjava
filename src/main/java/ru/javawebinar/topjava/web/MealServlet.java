package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealStorage;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private final MealStorage mealStorage = new MealStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: simple CRUD with meals in memory storage

        List<MealTo> meals = MealsUtil.filteredByStreams(mealStorage.getFiltered(meal -> true), MealsUtil.CALORIES_LIMIT);

        request.setAttribute("meals", meals);

        log.debug("forward to meals.jsp");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}

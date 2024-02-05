package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.IdGenerator;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final Map<Integer, Meal> MEALS = MealsUtil.getAll();
    private static final IdGenerator generator = IdGenerator.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", MealsUtil.getMealToList(new ArrayList<>(MEALS.values())));
            request.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        StringBuilder requestPath = new StringBuilder("/WEB-INF/jsp/");
        switch (action) {
            case "delete":
                MEALS.remove(Integer.parseInt(id));
                response.sendRedirect("meals");
                return;
            case "create":
                meal = new Meal(generator.getNextUniqueIndex());
                requestPath.append("edit");
                break;
            case "update":
                meal = MEALS.get(Integer.parseInt(id));
                requestPath.append("edit");
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher(requestPath.append(".jsp").toString()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", MealsUtil.getMealToList(new ArrayList<>(MEALS.values())));
            request.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(request, response);
            return;
        }
        String id = request.getParameter("id");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        String dateTime = request.getParameter("date_time");
        Meal meal;
        switch (action) {
            case "update":
                meal = MEALS.get(Integer.parseInt(id));
                meal.setCalories(Integer.parseInt(calories));
                meal.setDescription(description);
                meal.setDateTime(LocalDateTime.parse(dateTime));
                break;
            case "create":
                meal = new Meal(Integer.parseInt(id), LocalDateTime.parse(dateTime), description, Integer.parseInt(calories));
                MEALS.put(meal.getId(), meal);
                break;
        }
        response.sendRedirect("meals");
    }
}

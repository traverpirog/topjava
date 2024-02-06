package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealCrudDAO;
import ru.javawebinar.topjava.dao.MealCrudInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private final MealCrudDAO mealCrud = new MealCrudInMemoryImpl();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", mealCrud.getAllMealTo());
            request.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        StringBuilder requestPath = new StringBuilder("/WEB-INF/jsp/");
        switch (action) {
            case "delete":
                mealCrud.delete(Integer.parseInt(id));
                response.sendRedirect("meals");
                return;
            case "create":
                meal = new Meal(-1, LocalDateTime.now(), "", 0);
                break;
            case "update":
                meal = mealCrud.get(Integer.parseInt(id));
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher(requestPath.append("edit_meal.jsp").toString()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String dateTime = request.getParameter("date_time");
        Meal meal = new Meal(id, LocalDateTime.parse(dateTime), description, calories);
        if (id == -1) {
            mealCrud.create(meal);
        } else {
            mealCrud.update(meal);
        }
        response.sendRedirect("meals");
    }
}

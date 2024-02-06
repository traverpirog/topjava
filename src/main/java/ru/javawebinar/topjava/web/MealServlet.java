package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Crud;
import ru.javawebinar.topjava.dao.InMemoryMealCrud;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private Crud<Meal> dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new InMemoryMealCrud();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", MealsUtil.filteredByStreams(dao.getAll(), null, null, 2000));
            request.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        StringBuilder requestPath = new StringBuilder("/WEB-INF/jsp/");
        switch (action) {
            case "create":
                meal = new Meal();
                break;
            case "update":
                meal = dao.get(Integer.parseInt(id));
                break;
            case "delete":
                dao.delete(Integer.parseInt(id));
            default:
                response.sendRedirect("meals");
                return;
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher(requestPath.append("edit_meal.jsp").toString()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String dateTime = request.getParameter("date_time");
        Meal meal;
        if (id == null) {
            meal = new Meal();
            meal.setDateTime(LocalDateTime.parse(dateTime));
            meal.setCalories(calories);
            meal.setDescription(description);
            dao.create(meal);
        } else {
            meal = new Meal(Integer.parseInt(id), LocalDateTime.parse(dateTime), description, calories);
            dao.update(meal);
        }
        response.sendRedirect("meals");
    }
}

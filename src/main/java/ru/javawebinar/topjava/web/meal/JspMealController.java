package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController {
    @Autowired
    private MealRestController mealController;

    @PostMapping("/")
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (!StringUtils.hasText(request.getParameter("id"))) {
            mealController.create(meal);
        } else {
            mealController.update(meal, getId(request));
        }
        //response.sendRedirect("meals");
        return "redirect:meals";
    }

    @GetMapping("/delete")
    protected String doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getId(request);
        mealController.delete(id);
        //response.sendRedirect("meals");
        return "redirect:meals";
    }

    @GetMapping("/create")
    protected String doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        final Meal meal = "create".equals(action) ?
                new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                mealController.get(getId(request));
        request.setAttribute("meal", meal);
        //request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
        return "meals";

    }

    @GetMapping("/filter")
    protected String doFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
        //request.getRequestDispatcher("/meals.jsp").forward(request, response);
        return "meals";
    }

    @GetMapping("/")
    protected String doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("meals", mealController.getAll());
        //request.getRequestDispatcher("/meals.jsp").forward(request, response);
        return "meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

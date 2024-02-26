package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = Meal.FIND_ALL, query = "FROM Meal m WHERE m.user.id=:user_id ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.BY_DATE_TIME,
                query = "FROM Meal m " +
                        "WHERE m.dateTime>=:startTime AND m.dateTime<:endTime AND m.user.id=:user_id " +
                        "ORDER BY m.dateTime DESC")
})
@Table(name = "meal", uniqueConstraints = @UniqueConstraint(
        name = "meal_unique_user_datetime_idx",
        columnNames = {"user_id", "date_time"})
)
public class Meal extends AbstractBaseEntity {
    public static final String DELETE = "Meal.delete";
    public static final String FIND_ALL = "Meal.findAll";
    public static final String BY_DATE_TIME = "Meal.findByDateTime";

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @NotBlank
    @Size(min = 2, max = 120)
    @Column(name = "description", nullable = false)
    private String description;

    @Range(min = 10, max = 5000)
    @Column(name = "calories", nullable = false)
    private int calories;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}

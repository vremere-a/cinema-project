package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exeptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.interfaces.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log4j
public class Main {
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) throws AuthenticationException {

        log.info("APPLICATION START WORK");
        MovieService movieService = context.getBean(MovieService.class);

        Movie spiderMan = new Movie();
        spiderMan.setTitle("Spider Man");
        movieService.add(spiderMan);

        Movie ironMan = new Movie();
        ironMan.setTitle("Iron Man");
        movieService.add(ironMan);

        Movie flash = new Movie();
        flash.setTitle("Flash");
        movieService.add(flash);
        log.info("Getting all movie title");
        movieService.getAll().forEach(log::info);

        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        CinemaHall marvelHall = new CinemaHall();
        marvelHall.setCapacity(100);
        cinemaHallService.add(marvelHall);
        log.info("Getting all cinemaHall");
        cinemaHallService.getAll().forEach(log::info);

        MovieSession spiderManSession = new MovieSession();
        spiderManSession.setCinemaHall(marvelHall);
        spiderManSession.setMovie(spiderMan);
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        spiderManSession.setShowTime(LocalDateTime.now().plusMonths(5));
        movieSessionService.add(spiderManSession);

        MovieSession ironManSession = new MovieSession();
        ironManSession.setCinemaHall(marvelHall);
        ironManSession.setMovie(ironMan);
        ironManSession.setShowTime(LocalDateTime.now().plusMonths(3));
        movieSessionService.add(ironManSession);

        MovieSession flashSession = new MovieSession();
        flashSession.setCinemaHall(marvelHall);
        flashSession.setMovie(flash);
        flashSession.setShowTime(LocalDateTime.now().plusMonths(2));
        movieSessionService.add(flashSession);

        log.info("Getting all Available Sessions");
        movieSessionService.findAvailableSessions(spiderMan.getId(), LocalDate.now())
                .forEach(log::info);
        movieSessionService.findAvailableSessions(ironMan.getId(), LocalDate.now())
                .forEach(log::info);
        movieSessionService.findAvailableSessions(flash.getId(), LocalDate.now())
                .forEach(log::info);

        UserService userService = context.getBean(UserService.class);
        User user1 = new User();
        user1.setEmail("arts@ukr.net");
        user1.setPassword("123");
        userService.add(user1);
        log.info("Find User by email " + userService.findByEmail(user1.getEmail()));

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        authenticationService.register("arts2@ukr.net", "1234");
        log.warn("Incorrect username or password");

        User user2 = userService.findByEmail("arts2@ukr.net").get();

        authenticationService.register("arts3@ukr.net", "12345");
        log.warn("Incorrect username or password");

        User user3 = userService.findByEmail("arts3@ukr.net").get();

        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        ShoppingCart userShoppingCart3 = shoppingCartService.getByUser(user3);
        ShoppingCart userShoppingCart2 = shoppingCartService.getByUser(user2);
        log.info("show Shopping cart by user # 3 " + userShoppingCart3);
        log.info("show Shopping cart by user # 2 " + userShoppingCart2);

        shoppingCartService.addSession(ironManSession, user3);
        shoppingCartService.addSession(spiderManSession, user3);
        log.info("show Shopping cart by user # 3 " + shoppingCartService.getByUser(user3));

        shoppingCartService.addSession(flashSession, user2);
        shoppingCartService.addSession(spiderManSession, user2);
        log.info("show Shopping cart by user # 2 " + shoppingCartService.getByUser(user2));
        shoppingCartService.clear(shoppingCartService.getByUser(user2));

        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user3).getTickets(), user3);
        log.info("getting all order history");
        orderService.getOrderHistory(user3).forEach(log::info);
        log.info("APPLICATION EXITS");
    }
}

package lee.controller;

import lee.domain.Movie;
import lee.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author leon
 * @date 2018-06-19 12:01
 * @desc
 */
@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @RequestMapping("list")
    public ModelAndView movieView(HttpServletRequest request,Pageable pageable, @RequestParam(required = false) String movieName){
        ModelAndView modelAndView = new ModelAndView("/admin/movie-list");
        Page<Movie> movies = movieService.list(pageable,movieName);
        modelAndView.addObject("movies",movies);
        modelAndView.addObject("movieName",movieName);
        modelAndView.addObject("pagesize",movies.getSize());
        String sort = "ratingNum,desc";
        if (pageable.getSort()!=null){
            sort = pageable.getSort().toString();
            sort = sort.split(":")[0] + "," + sort.split(":")[1].trim().toLowerCase();
        }
        modelAndView.addObject("sort",sort);
        return modelAndView;
    }
}

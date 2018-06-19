package lee.service;

import lee.domain.Movie;
import lee.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author leon
 * @date 2018-06-11 13:08
 * @desc 电影服务类
 */
@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public Long countMovie(){
        return movieRepository.count();
    }
    public Page<Movie> list(Pageable pageable,String movieName){
        if (StringUtils.isEmpty(movieName)){
            return movieRepository.findAll(pageable);
        }else {
            movieName = "%" + movieName +"%";
            return movieRepository.findAllByNameLike(movieName,pageable);
        }
    }
}

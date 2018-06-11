package lee.service;

import lee.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

package lee.repository;

import lee.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author leon
 * @date 2018-06-11 13:07
 * @desc 电影仓库接口
 */
public interface MovieRepository extends Repository<Movie> {

    Page<Movie> findAllByNameLike(String name, Pageable pageable);
}

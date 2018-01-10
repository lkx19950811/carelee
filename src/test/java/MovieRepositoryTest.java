import lee.domain.Movie;
import lee.repository.MovieRepository;
import lee.utils.LoadSeed;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述:
 *
 * @author Leo
 * @create 2017-12-26 下午 7:21
 */
public class MovieRepositoryTest extends TestBasic {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    LoadSeed loadSeed;
    @Test
    public void insert(){
        for (int i=0;i<10;i++){
            Movie movie = new Movie();
            movie.setName("肖申克的救赎");
            movieRepository.save(movie);
        }
    }
    @Test
    public void readSeed(){
        System.out.println(loadSeed.getHttp());
    }
}

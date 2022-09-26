package guru.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import guru.springframework.norris.chuck.ChuckNorrisInfoContributor;

@Component
public class ChuckNorrisConfig {

    /**
     * Create a bean ChuckNorrisInfoContributor
     * 
     * The class <code>chuckNorrisInfoContributor</code> implements a <code>InfoContributorInterface</code> which displays the information for /info
     * 
     * @return A chuckNorrisInfocontributor
     */
    @Bean
    public ChuckNorrisInfoContributor chuckNorrisInfoContributor() {
        return new ChuckNorrisInfoContributor();
    }

}
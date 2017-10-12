package player.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import player.domain.Resource;

public interface ResourceRepository extends MongoRepository<Resource, String> {

    public List<Resource> findBySite(String site);

}

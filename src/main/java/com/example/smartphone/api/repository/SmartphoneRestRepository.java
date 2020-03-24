package com.example.smartphone.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.smartphone.api.model.Smartphone;

@RepositoryRestResource(path = "smartphones", collectionResourceRel = "smartphones")
public interface SmartphoneRestRepository extends JpaRepository<Smartphone, Long> {

	@RestResource(path = "query", rel = "query")
	List<Smartphone> findByModelContainingIgnoreCaseOrCodeContainingIgnoreCaseOrBrandContainingIgnoreCase(
			@Param("model") String model, @Param("code") String code, @Param("brand") String brand);

}

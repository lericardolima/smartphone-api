package com.example.smartphone.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.smartphone.api.model.Smartphone;

@RepositoryRestResource(path = "smartphones", collectionResourceRel = "smartphones")
public interface SmartphoneRestRepository extends JpaRepository<Smartphone, Long> {

}

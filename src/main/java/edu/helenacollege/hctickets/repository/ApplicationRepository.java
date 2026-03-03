package edu.helenacollege.hctickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	public Application findByAppName(String appName);
	public List<Application> findByStatus(String status);
}
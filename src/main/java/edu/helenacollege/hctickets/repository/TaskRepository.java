package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.Task;
public interface TaskRepository extends JpaRepository<Task, Integer> {
}

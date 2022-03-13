package com.nanos.irctc.repository.train;

import com.nanos.irctc.entity.train.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Long> {
}

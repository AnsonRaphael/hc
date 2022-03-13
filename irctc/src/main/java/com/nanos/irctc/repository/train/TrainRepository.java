package com.nanos.irctc.repository.train;

import com.nanos.irctc.entity.train.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Long> {
}

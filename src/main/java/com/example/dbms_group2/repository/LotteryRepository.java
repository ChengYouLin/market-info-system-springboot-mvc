package com.example.dbms_group2.repository;

import com.example.dbms_group2.model.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery,Long> {

    void updateLotteryResult(String userEmail, String marketEmail,
                             String prizeName);
}

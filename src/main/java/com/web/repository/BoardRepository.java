package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.Board;
import com.web.domain.User;

public interface BoardRepository extends JpaRepository<Board,Long>{
	Board findByUser(User user);

}

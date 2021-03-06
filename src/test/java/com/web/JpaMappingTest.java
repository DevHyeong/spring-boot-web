package com.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
	private final String boardTestTitle = "�׽�Ʈ";
	private final String email = "test@gmail.com";
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Before
	public void init() {
		User user = userRepository.save(User.builder()
				.name("havi")
				.password("test")
				.email(email)
				.createdDate(LocalDateTime.now())
				.build());
		
		boardRepository.save(Board.builder()
				.title(boardTestTitle)
				.subTitle("���� Ÿ��Ʋ")
				.content("������")
				.boardType(BoardType.free)
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.user(user).build());
	}
	
	@Test
	public void �����_�����ƴ���_�׽�Ʈ() {
		User user = userRepository.findByEmail(email);
		assertThat(user.getName(), is("havi"));
		assertThat(user.getPassword(), is("test"));
		assertThat(user.getEmail(), is(email));
		
		Board board = boardRepository.findByUser(user);
		assertThat(board.getTitle(), is(boardTestTitle));
		assertThat(board.getSubTitle(), is("���� Ÿ��Ʋ"));
		assertThat(board.getContent(), is("������"));
		assertThat(board.getBoardType(), is(BoardType.free));
		
		
		
	}
}

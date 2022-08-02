package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.HouseholdAccountRepository;

@ExtendWith(MockitoExtension.class)
class HouseholdAccountServiceTest {

	@Mock
	private HouseholdAccountRepository repository;
	@Mock
	private CategoryRepository categoryRepository;
	@InjectMocks
	HouseholdAccountService service;
	
	@Test
	void listがNULLのテスト() {
		List<Integer> list = new ArrayList();
		list.add(null);
		when(repository.sumIncome(anyString(), anyString())).thenReturn(list);
		list = service.sumIncome(2022, 8);
		assertEquals(list.get(0), 0);
	}

}

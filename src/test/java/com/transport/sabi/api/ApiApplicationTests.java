package com.transport.sabi.api;

import com.transport.sabi.api.domain.Address;
import com.transport.sabi.api.domain.Location;
import com.transport.sabi.api.domain.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApiApplicationTests {

	@Autowired
	LocationRepository locationRepository;

	@Test
	void contextLoads() {
		Location location = new Location();
		location.setKmAllocated(10L);
		Address address = new Address();
		address.setZipcode("638316");
		address.setAddress("chithode");
		address.setCity("erode");
		address.setState("Tamil Nadu");
		location.setAddress(address);
		location.setDistributorName("siri");

		locationRepository.saveAndFlush(location);

		assertThat(location.getId()).isEqualTo(2L);
	}

}

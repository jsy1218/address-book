package com.sijiang.addressbook.service.contact_list.enrichment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sijiang.addressbook.contact_list.enrichment.AddressBookAddAddressToPersonHappyTest;
import com.sijiang.addressbook.model.Address;
import com.sijiang.addressbook.repo.AddressBookRepository;
import com.sijiang.addressbook.repo.impl.AddressBookRepositoryImpl;
import com.sijiang.addressbook.repository_config.JDBCDriverConfig;
import com.sijiang.addressbook.service.AddressBookManager;
import com.sijiang.addressbook.service.impl.AddressBookManagerImpl;
import com.sijiang.addressbook.service_config.ServiceConfig;
import com.sijiang.addressbook.util.DefaultAddressBookParams;

public class AddressBookAddAddressToPersonServiceHappyTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddressBookAddAddressToPersonServiceHappyTest.class);
	
	@Test
	public void runAddressBookAddAddressToPersonServiceHappyTest()
			throws JsonGenerationException, JsonMappingException, IOException {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				ServiceConfig.class, JDBCDriverConfig.class);

		try {
			AddressBookManager addressBookManager = context
					.getBean("com.sijiang.addressbook.service.impl.AddressBookManagerImpl", AddressBookManagerImpl.class);
						
			addressBookManager.addAddressToPerson(DefaultAddressBookParams.DEFAULT_FIRST_NAME, 
					DefaultAddressBookParams.DEFAULT_LAST_NAME, DefaultAddressBookParams.DEFAULT_STREET_NAME,
					DefaultAddressBookParams.DEFAULT_CITY, DefaultAddressBookParams.DEFAULT_COUNTRY, 
					DefaultAddressBookParams.DEFAULT_POSTAL_CODE, DefaultAddressBookParams.DEFAULT_ADDRESS_TYPE.toString());

		} finally {
			((AnnotationConfigApplicationContext) context).close();
		}
	}


}

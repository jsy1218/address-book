package com.sijiang.addressbook.service.closeaccount;

import java.io.IOException;
import java.util.UUID;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;

import com.sijiang.addressbook.model.Account;
import com.sijiang.addressbook.model.Account.AccountStatus;
import com.sijiang.addressbook.repository_config.JDBCDriverConfig;
import com.sijiang.addressbook.service.AddressBookManager;
import com.sijiang.addressbook.service.impl.AddressBookManagerImpl;
import com.sijiang.addressbook.service_config.ServiceConfig;

public class AddressBookCloseAccountServiceHappyTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddressBookCloseAccountServiceHappyTest.class);

	@Test
	public void runAddressBookCloseAccountServiceHappyTest()
			throws JsonGenerationException, JsonMappingException, IOException {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				ServiceConfig.class, JDBCDriverConfig.class);

		try {
			AddressBookManager addressBookManager = context
					.getBean("com.sijiang.addressbook.service.impl.AddressBookManagerImpl", AddressBookManagerImpl.class);
			
			String username = UUID.randomUUID().toString() + "@email.com";
			String password = "aaaBBB";
			
			addressBookManager.openAccount(username, password);
			addressBookManager.closeAccount(username);
			
			Account closedAccount = addressBookManager.findAccountByUsername(username);
			
			Assert.assertEquals("account expected to be closed, but not.", 
					AccountStatus.CLOSED, closedAccount.getAccountStatus());
			
		} catch (DuplicateKeyException dupEx) {
			LOGGER.error(dupEx.getMessage());
			throw new DuplicateKeyException(dupEx.getMessage());
		} finally {
			((AnnotationConfigApplicationContext) context).close();
		}
	}

}

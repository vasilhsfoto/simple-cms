package org.dtelaroli.cms.domain.model.base;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import models.MyModel;

import org.junit.Before;
import org.junit.Test;

import com.avaje.ebean.Ebean;

public class LoggedModelTest {

	private MyModel model;

	@Before
	public void setUp() throws Exception {
		model = new MyModel();
		model.setName("foo");
	}

	@Test
	public void shouldPersistModel() {
		Ebean.save(model);
		
		assertThat(model.getId(), notNullValue());
		assertThat(model.getId(), greaterThan(0L));
	}
	
	@Test
	public void shouldSetDatesOnPersist() {
		Ebean.save(model);
		
		assertThat(model.getId(), greaterThan(0L));
		assertThat(model.getCreatedAt(), notNullValue());
		assertThat(model.getUpdatedAt(), notNullValue());
		assertThat(model.getCreatedAt(), equalTo(model.getUpdatedAt()));
	}

	@Test
	public void shouldSetUpdatedAtOnUpdate() throws InterruptedException {
		Ebean.save(model);
		assertThat(model.getId(), greaterThan(0L));
		assertThat(model.getCreatedAt(), notNullValue());
		assertThat(model.getUpdatedAt(), notNullValue());
		assertThat(model.getCreatedAt(), equalTo(model.getUpdatedAt()));

		Thread.sleep(200);
		model.setName("bar");
		Ebean.update(model);
		assertThat(model.getCreatedAt(), lessThan(model.getUpdatedAt()));
	}
	
	@Test
	public void shouldReturnNullIfCreatedAtIsNull() {
		assertThat(model.getCreatedAtDate(), nullValue());
	}
	
	@Test
	public void shouldReturnNullIfUpdatedAtIsNull() {
		assertThat(model.getUpdatedAtDate(), nullValue());
	}
	
	@Test
	public void shouldReturnNotNullIfCreatedAtIsNull() {
		Ebean.save(model);
		assertThat(model.getCreatedAtDate(), notNullValue());
	}
	
	@Test
	public void shouldReturnNotNullIfUpdatedAtIsNull() {
		Ebean.save(model);
		assertThat(model.getUpdatedAtDate(), notNullValue());
	}
	
	@Test
	public void shouldReturnToString() {
		Ebean.save(model);
		
		assertThat(model.toString(), containsString("MyModel{"));
	}
	
	@Test
	public void shouldReturnEquals() {
		assertThat(model, equalTo(model));
	}
	
}

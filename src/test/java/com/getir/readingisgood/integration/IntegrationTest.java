package com.getir.readingisgood.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.config.GlobalConfig;
import com.getir.readingisgood.domain.customer.adapter.repository.jpa.CustomerJpaRepository;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.message.CreateCustomerMessageBuilder;
import com.getir.readingisgood.util.Randomizer;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@Tag("integrationTest")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class IntegrationTest {
  private ClientAndServer mockServer;
  private final ObjectMapper objectMapper = GlobalConfig.createObjectMapper();
  @Autowired private MockMvc mockMvc;
  @Autowired private CustomerJpaRepository customerJpaRepository;

  public static String asJsonString(final Object obj) {
    try {
      return GlobalConfig.createObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeAll
  void setUpServer() {
    mockServer = ClientAndServer.startClientAndServer(8092);
  }

  @AfterAll
  void tearDown() {
    mockServer.stop();
  }

  @BeforeEach
  void setUp() {
    customerJpaRepository.deleteAll();
  }

  @Test
  public void shouldSimulateRegisterCustomerApiAndReturnIsAcceptedStatus() throws Exception {
    Faker faker = Randomizer.create().getFaker();
    Email email = Email.create(faker.name().firstName() + "@gmail.com");
    CreateCustomerMessageBuilder createCustomerMessage =
        CreateCustomerMessageBuilder.create().withEmail(email.getValue());
    var customerMessage = createCustomerMessage.buildCreateCustomerMessage();
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/customer/register")
                .content(asJsonString(customerMessage))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }
}

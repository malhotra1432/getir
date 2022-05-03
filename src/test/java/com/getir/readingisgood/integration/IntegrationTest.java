package com.getir.readingisgood.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.config.GlobalConfig;
import com.getir.readingisgood.domain.book.adapter.repository.jpa.BookStateJPARepository;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.customer.adapter.repository.jpa.CustomerJpaRepository;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.adapter.repository.jpa.OrderStateJPARepository;
import com.getir.readingisgood.message.CreateBookMessageBuilder;
import com.getir.readingisgood.message.CreateCustomerMessageBuilder;
import com.getir.readingisgood.message.CreateOrderMessageBuilder;
import com.getir.readingisgood.util.Randomizer;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
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
  @Autowired private BookStateJPARepository bookStateJPARepository;
  @Autowired private OrderStateJPARepository orderStateJPARepository;

  Faker faker = Randomizer.create().getFaker();
  Email CUSTOMER_EMAIL = Email.create(faker.name().firstName() + "@gmail.com");
  Name BOOK_NAME = Name.create(faker.book().title());

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
    bookStateJPARepository.deleteAll();
    orderStateJPARepository.deleteAll();
  }

  @Test
  public void shouldSimulateOrderJourneyFlow() throws Exception {
    var customerEmail = CUSTOMER_EMAIL;
    var bookName = BOOK_NAME;
    CreateCustomerMessageBuilder createCustomerMessage =
        CreateCustomerMessageBuilder.create().withEmail(customerEmail.getValue());

    CreateBookMessageBuilder createBookMessageBuilder =
        CreateBookMessageBuilder.create()
            .withName(bookName.getValue())
            .withPrice(BigDecimal.valueOf(10));

    CreateOrderMessageBuilder createOrderMessageBuilder =
        CreateOrderMessageBuilder.create()
            .withCustomerEmail(customerEmail.getValue())
            .withBookName(bookName.getValue());

    shouldSimulateCustomerCreation(createCustomerMessage);
    shouldSimulateBookCreation(createBookMessageBuilder);
    shouldSimulateOrderCreation(createOrderMessageBuilder);
  }

  private void shouldSimulateCustomerCreation(CreateCustomerMessageBuilder createCustomerMessage)
      throws Exception {
    var customerMessage = createCustomerMessage.buildCreateCustomerMessage();
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/customer/register")
                .content(asJsonString(customerMessage))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }

  private void shouldSimulateBookCreation(CreateBookMessageBuilder createBookMessageBuilder)
      throws Exception {
    var createBookMessage = createBookMessageBuilder.buildCreateBookMessage();
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/book/create")
                .content(asJsonString(createBookMessage))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }

  private void shouldSimulateOrderCreation(CreateOrderMessageBuilder createOrderMessageBuilder)
      throws Exception {
    var createOrderMessage = createOrderMessageBuilder.buildCreateOrderMessage();
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/order/create")
                .content(asJsonString(createOrderMessage))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }
}

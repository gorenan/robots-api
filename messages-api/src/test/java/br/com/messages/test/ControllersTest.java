package br.com.messages.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.messages.controllers.BotsController;
import br.com.messages.controllers.MessageController;
import br.com.messages.entities.BotEntity;
import br.com.messages.entities.MessageEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ControllersTest {

	private MockMvc mockMvc;
	private MockMvc mockMvcMsg;
	
	@Autowired
	private BotsController botController;
	
	@Autowired
	private MessageController msgController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(botController).build();
		this.mockMvcMsg = MockMvcBuilders.standaloneSetup(msgController).build();
	}
	
	@Test
	public void testBotController() throws Exception {
	//Test get
	mockMvc.perform(MockMvcRequestBuilders.get("/bots/ddiuashiaus")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	mockMvc.perform(MockMvcRequestBuilders.get("/bots/")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());

	mockMvc.perform(MockMvcRequestBuilders.get("/bots?id=dasdas")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	
	//Test POST
	BotEntity newBot = new BotEntity();
	
	mockMvc.perform(MockMvcRequestBuilders.post("/bots")
			.content(asJsonString(newBot))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());

	newBot.setName("Post Test");
	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/bots")
			.content(asJsonString(newBot))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	BotEntity botGenerated = new Gson().fromJson(result.getResponse().getContentAsString(), BotEntity.class);

	MvcResult getReturn = mockMvc.perform(MockMvcRequestBuilders.get("/bots/"+botGenerated.getId())
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	BotEntity botReturn = new Gson().fromJson(getReturn.getResponse().getContentAsString(), BotEntity.class);
	
	assertEquals(botGenerated.getId(), botReturn.getId());
	
	//TEST PUT
	botGenerated.setName("Put Test");
	MvcResult putResult = mockMvc.perform(MockMvcRequestBuilders.put("/bots")
			.content(asJsonString(botGenerated))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	BotEntity putReturn = new Gson().fromJson(putResult.getResponse().getContentAsString(), BotEntity.class);
	assertEquals(putReturn.getName(),"Put Test");
	
	mockMvc.perform(MockMvcRequestBuilders.put("/bots")
			.content(asJsonString(newBot))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
	
	//TEST DELETE
	mockMvc.perform(MockMvcRequestBuilders.delete("/bots")
			.content(asJsonString(botGenerated))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	
	mockMvc.perform(MockMvcRequestBuilders.delete("/bots")
			.content(asJsonString(newBot))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
	}
	
	@Test
	public void testMsgController() throws Exception {
		//Test POST
		MessageEntity msg = new MessageEntity(); 

		mockMvcMsg.perform(MockMvcRequestBuilders.post("/messages")
				.content(asJsonString(msg))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
		
		msg.setFrom("From");
		msg.setTo("To");
		msg.setText("Text");
		MvcResult andReturn = mockMvcMsg.perform(MockMvcRequestBuilders.post("/messages")
				.content(asJsonString(msg))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
		msg.setFrom("From");
		msg.setTo(null);
		msg.setText("Text");
		mockMvcMsg.perform(MockMvcRequestBuilders.post("/messages")
				.content(asJsonString(msg))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
		
		//test GET
		mockMvcMsg.perform(MockMvcRequestBuilders.get("/messages?conversationId=5d7bc732ce44e94a22a51fba")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		mockMvcMsg.perform(MockMvcRequestBuilders.get("/messages/5d7bc714510d3921db35ff40")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
	}
	
}

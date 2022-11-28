package pro.sky.java.course6.animal_shelter_bot.controllerTest;


import com.pengrad.telegrambot.TelegramBot;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.java.course6.animal_shelter_bot.controller.ContactController;
import pro.sky.java.course6.animal_shelter_bot.model.Contact;
import pro.sky.java.course6.animal_shelter_bot.repositories.ContactRepository;
import pro.sky.java.course6.animal_shelter_bot.repositories.ShelterRepository;
import pro.sky.java.course6.animal_shelter_bot.service.AnimalShelterBotService;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    TelegramBot telegramBot;

   @Mock
   private AnimalShelterBotService animalShelterBotService;


    @InjectMocks
    private ContactController contactController;


    @Test
    public void saveContactTest() throws Exception {
        //String token = "5758859832:AAEwJ4cIzXZnXITbJ1CnX1sy1K7WmXW-uhc";
        //TelegramBot bot = new TelegramBot(token);
        final int id = 1;
        final String numberPhone = "89061877772";
        final String name = "Иванов Иван Иванович";

        JSONObject jsonContact = new JSONObject();
        jsonContact.put("id", id);
        jsonContact.put("89061877772", numberPhone);
        jsonContact.put("Иванов Иван Иванович", name);

        Contact contact = new Contact();
        contact.setId(1);
        contact.setNumberPhone("89061877772");
        contact.setName("Иванов Иван Иванович");

        List<Contact> allContacts = new ArrayList<>();
        allContacts.add(contact);

        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        when(contactRepository.findById(any(Integer.class))).thenReturn(Optional.of(contact));
        when(contactRepository.findAll()).thenReturn(allContacts);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/newContact")
                        .content(jsonContact.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.numberPhone").value(numberPhone))
                .andExpect(jsonPath("$.name").value(name));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.numberPhone").value(numberPhone))
                .andExpect(jsonPath("$.name").value(name));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/contact/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    }

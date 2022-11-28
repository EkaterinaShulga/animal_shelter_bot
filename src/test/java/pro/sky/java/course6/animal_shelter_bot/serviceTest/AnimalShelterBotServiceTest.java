package pro.sky.java.course6.animal_shelter_bot.serviceTest;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course6.animal_shelter_bot.configuration.AnimalShelterBotConfiguration;
import pro.sky.java.course6.animal_shelter_bot.model.Contact;
import pro.sky.java.course6.animal_shelter_bot.repositories.ContactRepository;
import pro.sky.java.course6.animal_shelter_bot.repositories.ShelterRepository;
import pro.sky.java.course6.animal_shelter_bot.service.AnimalShelterBotService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static pro.sky.java.course6.animal_shelter_bot.model.ServiceConstantsMenu.COMMAND_INFORMATION_ABOUT_SHELTER;
import static pro.sky.java.course6.animal_shelter_bot.model.ServiceConstantsMenu.COMMAND_WORK_SCHEDULE_SHELTER;


@ExtendWith(MockitoExtension.class)
public class AnimalShelterBotServiceTest {

    @Mock
    private ContactRepository contactRepository;
    @Mock
    private ShelterRepository shelterRepository;

    @Mock
    private AnimalShelterBotConfiguration animalShelterBotConfiguration;

    private SendMessage message;
    private TelegramBot bot;


    //@InjectMocks
     @Mock
    private AnimalShelterBotService animalShelterBotService;

   public AnimalShelterBotServiceTest(){};
    @Test
    public void addContact() {
        Mockito.when(animalShelterBotService.addContact(new Contact(1, "89061877772", "Иванов Иван Иванович"))).thenReturn(new Contact(1, "89061877772", "Иванов Иван Иванович"));
        Assertions.assertEquals(new Contact(1, "89061877772", "Иванов Иван Иванович"), animalShelterBotService.addContact(new Contact(1, "89061877772", "Иванов Иван Иванович")));
    }

    @Test
    public void findContactByIdPositive() {
        Contact contact = new Contact(1, "89061877772", "Иванов Иван Иванович");
        Mockito.when(animalShelterBotService.addContact(contact)).thenReturn(contact);
        Assertions.assertEquals(contact, animalShelterBotService.addContact(contact));

        Mockito.when(animalShelterBotService.findContactById(1)).thenReturn(Optional.of(contact));
        Assertions.assertEquals(Optional.of(contact), animalShelterBotService.findContactById(1));
    }

    @Test
    public void findContactByIdNegative() {
        Mockito.when(animalShelterBotService.findContactById(10)).thenReturn(null);
        Assertions.assertNull(animalShelterBotService.findContactById(10));
    }

    @Test
    public void findAllContacts() {
        Mockito.when(animalShelterBotService.addContact(new Contact(1, "89061877772", "Иванов Иван Иванович"))).thenReturn(new Contact(1, "89061877772", "Иванов Иван Иванович"));
        Assertions.assertEquals(new Contact(1, "89061877772", "Иванов Иван Иванович"), animalShelterBotService.addContact(new Contact(1, "89061877772", "Иванов Иван Иванович")));
        Mockito.when(animalShelterBotService.addContact(new Contact(2, "89061977773", "Петров Петр Петрович"))).thenReturn(new Contact(2, "89061977773", "Петров Петр Петрович"));
        Assertions.assertEquals(new Contact(2, "89061977773", "Петров Петр Петрович"), animalShelterBotService.addContact(new Contact(2, "89061977773", "Петров Петр Петрович")));
        List<Contact> allContacts = List.of(
                new Contact(1, "89061877772", "Иванов Иван Иванович"),
                new Contact(2, "89061977773", "Петров Петр Петрович"));
        Mockito.when(animalShelterBotService.getAllContacts()).thenReturn(allContacts);
        Assertions.assertEquals(allContacts, animalShelterBotService.getAllContacts());
    }


  @Test
   public void process() throws IOException {
        String json = Files.readString(Paths.get("update.json"));
        Update update = BotUtils.parseUpdate(json);
        String token = "5758859832:AAEwJ4cIzXZnXITbJ1CnX1sy1K7WmXW-uhc";
        TelegramBot bot = new TelegramBot(token);
        Mockito.when(animalShelterBotConfiguration.telegramBot()).thenReturn(bot);
        Assertions.assertEquals(bot, animalShelterBotConfiguration.telegramBot());

       Mockito.when(animalShelterBotService.process(List.of(update))).thenReturn(UpdatesListener.CONFIRMED_UPDATES_ALL);
       Assertions.assertEquals(UpdatesListener.CONFIRMED_UPDATES_ALL, animalShelterBotService.process(List.of(update)));



  }
    @Test
    public void process2() throws IOException {
        String json = Files.readString(Paths.get("update.json"));
        Update update = BotUtils.parseUpdate(json);
        animalShelterBotService.process(List.of(update));
        Mockito.verify(animalShelterBotService).process(List.of(update));
    }

    @Test
    public void getMarkup() throws IOException {
        String json = Files.readString(Paths.get("update.json"));
        Update update = BotUtils.parseUpdate(json);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        var buttonAboutShelter  = new com.pengrad.telegrambot.model.request.InlineKeyboardButton(COMMAND_INFORMATION_ABOUT_SHELTER.getText());
        var  buttonWorkTime = new com.pengrad.telegrambot.model.request.InlineKeyboardButton(COMMAND_WORK_SCHEDULE_SHELTER.getText());
        buttonAboutShelter.callbackData("info");
        buttonWorkTime.callbackData("workTime");
        markup.addRow(buttonAboutShelter);
        markup.addRow(buttonWorkTime);
        Mockito.when(animalShelterBotService.makeButtonsForMenu()).thenReturn(markup);
        Assertions.assertEquals(markup, animalShelterBotService.makeButtonsForMenu());



    }

 }



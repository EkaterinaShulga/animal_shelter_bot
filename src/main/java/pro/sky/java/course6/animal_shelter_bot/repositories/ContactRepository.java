package pro.sky.java.course6.animal_shelter_bot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.java.course6.animal_shelter_bot.model.Contact;



@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {






}

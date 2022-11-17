package pro.sky.java.course6.animal_shelter_bot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.java.course6.animal_shelter_bot.model.Shelter;



@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Integer> {


}
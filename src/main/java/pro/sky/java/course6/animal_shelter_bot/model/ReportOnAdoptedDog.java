package pro.sky.java.course6.animal_shelter_bot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class ReportOnAdoptedDog {

  //  @Id
    private String nicknameDog;

    private String dogsDiet;
    private String health;
    private String welfare;
    private String adaptation;

    public ReportOnAdoptedDog() {
    }


    public String getNicknameDog() {
        return nicknameDog;
    }

    public void setNicknameDog(String nicknameDog) {
        this.nicknameDog = nicknameDog;
    }

    public String getDogsDiet() {
        return dogsDiet;
    }

    public void setDogsDiet(String dogsDiet) {
        this.dogsDiet = dogsDiet;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getAdaptation() {
        return adaptation;
    }

    public void setAdaptation(String adaptation) {
        this.adaptation = adaptation;
    }
}

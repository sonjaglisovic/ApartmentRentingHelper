package rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity;

import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.UserRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    List<Demand> demands = new ArrayList<>();

    public UserEntity() {}

    public UserEntity(UserRequest userRequest) {
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }
}

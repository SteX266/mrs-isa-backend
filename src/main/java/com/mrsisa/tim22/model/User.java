package com.mrsisa.tim22.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="user_table")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String username;
    @JsonIgnore
    @Column
    private String password;
    @Column(name="last_password_reset_date")
    private Timestamp lastPasswordResetDate;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private boolean isDeleted;
    @Column
    private boolean isEnabled;
    @Column
    private int loyaltyPoints;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<AccountCancellationRequest> accountCancellationRequests = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    @ManyToMany
    @JoinTable(name="subscribtions", joinColumns = @JoinColumn(name="client_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="system_entity_id", referencedColumnName = "id"))
    private Set<SystemEntity> subscribtions = new HashSet<>();

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Complaint> complaints = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Penalty> penalties = new HashSet<>();

    @OneToMany(mappedBy ="owner", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SystemEntity> entities = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void addSubscribtion(SystemEntity e){
        this.subscribtions.add(e);
    }

    public void removeSubscribtion(SystemEntity e) {
        this.subscribtions.remove(e);
    }

    public void addPenalty(Penalty p) {
        this.penalties.add(p);
    }

    public int getUserPenalties() {


        LocalDate todayDate = LocalDate.now();
        todayDate = todayDate.withDayOfMonth(1);
        int penaltyNumber = 0;
        for (Penalty p : this.penalties) {
            if (p.getDate().isAfter(todayDate)) {
                penaltyNumber++;
            }
        }
        return penaltyNumber;
    }

    public void addPoints(int pointsPerReservation) {
        this.loyaltyPoints += pointsPerReservation;
    }
}

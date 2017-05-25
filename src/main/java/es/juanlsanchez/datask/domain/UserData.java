package es.juanlsanchez.datask.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.juanlsanchez.datask.domain.enumeration.EnumUserProfile;
import es.juanlsanchez.datask.domain.enumeration.EnumUserType;

/**
 * A UserData.
 */
@Entity
@Table(name = "user_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserData implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull
  @Column(name = "surname", nullable = false)
  private String surname;

  @NotNull
  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "office_phone")
  private String officePhone;

  @Enumerated(EnumType.STRING)
  @Column(name = "profile")
  private EnumUserProfile profile;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_user")
  private EnumUserType typeUser;

  @OneToMany(mappedBy = "userData")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Notification> notifications = new HashSet<>();

  @OneToMany(mappedBy = "userData")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Comment> comments = new HashSet<>();

  @OneToOne
  @JoinColumn(unique = true)
  private Preference preference;

  @OneToMany(mappedBy = "userData")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<TaskRequest> taskRequests = new HashSet<>();

  @OneToMany(mappedBy = "userData")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Task> tasks = new HashSet<>();

  @ManyToOne
  private Company company;

  @OneToOne(mappedBy = "userData")
  @JsonIgnore
  private UserProject userProject;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getOfficePhone() {
    return officePhone;
  }

  public void setOfficePhone(String officePhone) {
    this.officePhone = officePhone;
  }

  public EnumUserProfile getProfile() {
    return profile;
  }

  public void setProfile(EnumUserProfile profile) {
    this.profile = profile;
  }

  public EnumUserType getTypeUser() {
    return typeUser;
  }

  public void setTypeUser(EnumUserType typeUser) {
    this.typeUser = typeUser;
  }

  public Set<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(Set<Notification> notifications) {
    this.notifications = notifications;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public Preference getPreference() {
    return preference;
  }

  public void setPreference(Preference preference) {
    this.preference = preference;
  }

  public Set<TaskRequest> getTaskRequests() {
    return taskRequests;
  }

  public void setTaskRequests(Set<TaskRequest> taskRequests) {
    this.taskRequests = taskRequests;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public UserProject getUserProject() {
    return userProject;
  }

  public void setUserProject(UserProject userProject) {
    this.userProject = userProject;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserData userData = (UserData) o;
    if (userData.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, userData.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "UserData{" + "id=" + id + ", name='" + name + "'" + ", surname='" + surname + "'"
        + ", email='" + email + "'" + ", officePhone='" + officePhone + "'" + ", profile='"
        + profile + "'" + ", typeUser='" + typeUser + "'" + '}';
  }
}

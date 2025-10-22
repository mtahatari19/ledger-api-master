package com.ledgerx.accountGroup.entity;

import com.ledger.core.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_ACCOUNT_GROUPS", uniqueConstraints = {
    @UniqueConstraint(name = "UK_ACCOUNT_GROUP_CODE", columnNames = "code")
})
public class AccountGroup extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNT_GROUP")
  @SequenceGenerator(name = "SEQ_ACCOUNT_GROUP", sequenceName = "SEQ_ACCOUNT_GROUP", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @NotNull(message = "{accountGroup.validation.code.required}")
  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @NotBlank(message = "{accountGroup.validation.name.required}")
  @Size(max = 200, message = "{accountGroup.validation.name.size}")
  @Column(name = "name", nullable = false, length = 200)
  private String name;

  @Size(max = 200, message = "{accountGroup.validation.englishName.size}")
  @Column(name = "english_name", length = 200)
  private String englishName;

  @NotBlank(message = "{accountGroup.validation.groupType.required}")
  @Column(name = "group_type", nullable = false, length = 50)
  private String groupType;

  @NotNull(message = "{accountGroup.validation.status.required}")
  @Column(name = "status", nullable = false)
  private Boolean status = true;

  public AccountGroup() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEnglishName() {
    return englishName;
  }

  public void setEnglishName(String englishName) {
    this.englishName = englishName;
  }

  public String getGroupType() {
    return groupType;
  }

  public void setGroupType(String groupType) {
    this.groupType = groupType;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountGroup that = (AccountGroup) o;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public String toString() {
    return "AccountGroup{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", name='" + name + '\'' +
        ", englishName='" + englishName + '\'' +
        ", groupType='" + groupType + '\'' +
        ", status=" + status +
        '}';
  }
}

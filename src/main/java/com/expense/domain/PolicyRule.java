package com.expense.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.LocalDate;

import com.expense.domain.constants.StopType;
import com.expense.domain.policy.MaximumAmountPerNightPolicyRule;
import com.expense.domain.policy.MaximumAmountPolicyRule;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Table
@Entity
@Audited
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "name")
@JsonTypeInfo(property = "name", use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(name = MaximumAmountPolicyRule.POLICY_NAME, value = MaximumAmountPolicyRule.class),
    @JsonSubTypes.Type(name = MaximumAmountPerNightPolicyRule.POLICY_NAME, value = MaximumAmountPerNightPolicyRule.class) })
public abstract class PolicyRule<T> {

  @Id
  @GeneratedValue
  protected Long id;
  @Column(insertable = false, updatable = false)
  protected String name;
  protected String ruleValue;
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
  protected LocalDate startDate;
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
  protected LocalDate endDate;
  @Enumerated(EnumType.STRING)
  protected StopType stopType;
  protected boolean active;
  @Transient
  protected String description;

  public PolicyRule() {
    startDate = LocalDate.now();
    endDate = LocalDate.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRuleValue() {
    return ruleValue;
  }

  public void setRuleValue(String ruleValue) {
    this.ruleValue = ruleValue;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public StopType getStopType() {
    return stopType;
  }

  public void setStopType(StopType stopType) {
    this.stopType = stopType;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public abstract String getName();

  public abstract boolean isValid(Expense expense);

  public abstract T getConvertedRuleValue();

  public abstract boolean isValueValid();

  public abstract String getInvalidMessage(Expense expense);

  public abstract String getDescription();

  @Override
  public String toString() {
    return "PolicyRule [id=" + id + ", name=" + name + ", ruleValue="
        + ruleValue + ", startDate=" + startDate + ", endDate=" + endDate
        + ", stopType=" + stopType + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((ruleValue == null) ? 0 : ruleValue.hashCode());
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
    result = prime * result + ((stopType == null) ? 0 : stopType.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PolicyRule<?> other = (PolicyRule<?>) obj;
    if (endDate == null) {
      if (other.endDate != null)
        return false;
    }
    else if (!endDate.equals(other.endDate))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    }
    else if (!name.equals(other.name))
      return false;
    if (ruleValue == null) {
      if (other.ruleValue != null)
        return false;
    }
    else if (!ruleValue.equals(other.ruleValue))
      return false;
    if (startDate == null) {
      if (other.startDate != null)
        return false;
    }
    else if (!startDate.equals(other.startDate))
      return false;
    if (stopType != other.stopType)
      return false;
    return true;
  }

}

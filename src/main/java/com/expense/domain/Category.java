package com.expense.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.expense.domain.category.AirCategory;
import com.expense.domain.category.CarCategory;
import com.expense.domain.category.LodgingCategory;
import com.expense.domain.category.StandardCategory;
import com.expense.domain.category.TelecomCategory;
import com.expense.domain.constants.CategoryType;
import com.expense.domain.constants.StopType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

@Entity
@Table
@Audited
@DiscriminatorColumn(name = "categoryType", length = 20)
@JsonTypeInfo(property = "categoryType", use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @Type(name = CategoryType.AIR, value = AirCategory.class),
    @Type(name = CategoryType.CAR, value = CarCategory.class),
    @Type(name = CategoryType.LODGING, value = LodgingCategory.class),
    @Type(name = CategoryType.STANDARD, value = StandardCategory.class),
    @Type(name = CategoryType.TELECOM, value = TelecomCategory.class) })
public abstract class Category {

  @Id
  @GeneratedValue
  protected Long id;
  protected String name;
  @Column(insertable = false, updatable = false)
  protected String categoryType;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(name = "CategoryPolicyRuleMapping", joinColumns = @JoinColumn(name = "categoryId"), inverseJoinColumns = @JoinColumn(name = "ruleId"))
  protected List<PolicyRule<?>> policyRules;

  protected Category() {
    this.name = "";
    this.policyRules = new ArrayList<>();
  }

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

  public String getCategoryType() {
    return this.categoryType;
  }

  public List<PolicyRule<?>> getPolicyRules() {
    return policyRules;
  }

  public void setPolicyRules(List<PolicyRule<?>> policyRules) {
    this.policyRules = policyRules;
  }

  public void addPolicyRule(PolicyRule<?> policyRule) {
    this.policyRules.add(policyRule);
  }

  public boolean isValid(Expense expense) {
    for (PolicyRule<?> policyRule : policyRules) {
      if (!policyRule.isValid(expense)) {
        return false;
      }
    }
    return true;
  }

  public Map<StopType, Collection<String>> getInvalidMessages(Expense expense) {
    Multimap<StopType, String> messageMap = ArrayListMultimap.create();
    for (PolicyRule<?> policyRule : policyRules) {
      if (!policyRule.isValid(expense)) {
        messageMap.put(policyRule.getStopType(),
            policyRule.getInvalidMessage(expense));
      }
    }
    return messageMap.asMap();
  }

  public List<PolicyRule<?>> getAvailablePolicyRules() {
    if (policyRules.isEmpty()) {
      return getReleventPolicyRules();
    }
    else {
      List<PolicyRule<?>> available = new ArrayList<>();
      for (PolicyRule<?> relevent : getReleventPolicyRules()) {
        boolean include = true;
        for (PolicyRule<?> added : getPolicyRules()) {
          if (added.getName().equals(relevent.getName())) {
            include = false;
            break;
          }
        }
        if (include) {
          available.add(relevent);
        }
      }
      return available;
    }
  }

  public abstract List<PolicyRule<?>> getReleventPolicyRules();

  @Override
  public String toString() {
    return "Category [id=" + id + ", name=" + name + ", categoryType="
        + categoryType + ", policyRules=" + policyRules + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((categoryType == null) ? 0 : categoryType.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    Category other = (Category) obj;
    if (categoryType == null) {
      if (other.categoryType != null)
        return false;
    }
    else if (!categoryType.equals(other.categoryType))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    }
    else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    }
    else if (!name.equals(other.name))
      return false;
    return true;
  }

}

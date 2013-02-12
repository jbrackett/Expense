package com.expense.domain;

import java.util.Collection;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.expense.domain.category.StandardCategory;
import com.expense.domain.constants.StopType;
import com.expense.domain.embed.Money;

@Entity
@Table
@Audited
public class Expense {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private DateTime transactionDate;
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
  private LocalDate startDate;
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
  private LocalDate endDate;
  private String vendor;
  @Embedded
  private Money money;
  @OneToOne
  @JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false)
  private Category category;

  public Expense() {
    this.name = "";
    this.transactionDate = DateTime.now();
    this.startDate = LocalDate.now();
    this.endDate = LocalDate.now();
    this.vendor = "";
    this.money = new Money();
    this.category = new StandardCategory();
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

  public DateTime getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(DateTime transactionDate) {
    this.transactionDate = transactionDate;
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

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public Money getMoney() {
    return money;
  }

  public void setMoney(Money money) {
    this.money = money;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public boolean isValid() {
    return category.isValid(this);
  }

  public Map<StopType, Collection<String>> getInvalidMessages() {
    return category.getInvalidMessages(this);
  }

  @Override
  public String toString() {
    return "Expense [id=" + id + ", name=" + name + ", transactionDate="
        + transactionDate + ", startDate=" + startDate + ", endDate=" + endDate
        + ", vendor=" + vendor + ", money=" + money + ", category=" + category
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
    result = prime * result + ((money == null) ? 0 : money.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
    result = prime * result
        + ((transactionDate == null) ? 0 : transactionDate.hashCode());
    result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
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
    Expense other = (Expense) obj;
    if (category == null) {
      if (other.category != null)
        return false;
    }
    else if (!category.equals(other.category))
      return false;
    if (endDate == null) {
      if (other.endDate != null)
        return false;
    }
    else if (!endDate.equals(other.endDate))
      return false;
    if (money == null) {
      if (other.money != null)
        return false;
    }
    else if (!money.equals(other.money))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    }
    else if (!name.equals(other.name))
      return false;
    if (startDate == null) {
      if (other.startDate != null)
        return false;
    }
    else if (!startDate.equals(other.startDate))
      return false;
    if (transactionDate == null) {
      if (other.transactionDate != null)
        return false;
    }
    else if (!transactionDate.equals(other.transactionDate))
      return false;
    if (vendor == null) {
      if (other.vendor != null)
        return false;
    }
    else if (!vendor.equals(other.vendor))
      return false;
    return true;
  }

}

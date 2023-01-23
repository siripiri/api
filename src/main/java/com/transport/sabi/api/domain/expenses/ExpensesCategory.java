package com.transport.sabi.api.domain.expenses;

import com.transport.sabi.api.domain.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class ExpensesCategory extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "expensesCategory", cascade = CascadeType.ALL)
    private Set<Expenses> expensesSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Expenses> getExpensesSet() {
        return expensesSet;
    }

    public void setExpensesSet(Set<Expenses> expensesSet) {
        this.expensesSet = expensesSet;
    }
}

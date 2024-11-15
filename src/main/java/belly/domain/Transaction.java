package belly.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private Long id;
    private String description;
    private Double value;
    private Account account;
    private LocalDate date;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getValue(), that.getValue()) && Objects.equals(getAccount(), that.getAccount()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getValue(), getAccount(), getDate(), getStatus());
    }
}

package belly.service.events;

import belly.domain.Account;

public interface AccountEvent {

    public enum EventType {CREATED, UPDATED, DELETED}

    void dispatch(Account account, EventType type);
}

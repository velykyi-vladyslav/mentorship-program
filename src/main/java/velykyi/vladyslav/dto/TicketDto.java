package velykyi.vladyslav.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import velykyi.vladyslav.model.Ticket;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("eventId")
    private long eventId;

    @JsonProperty("userId")
    private long userId;

    @JsonProperty("category")
    private Ticket.Category category;

    @JsonProperty("place")
    private int place;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Ticket.Category getCategory() {
        return category;
    }

    public void setCategory(Ticket.Category category) {
        this.category = category;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TicketDto dto = (TicketDto) o;
        return id == dto.id && eventId == dto.eventId && userId == dto.userId && place == dto.place
               && category == dto.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, userId, category, place);
    }

    @Override
    public String toString() {
        return "TicketDto{" + "id=" + id + ", eventId=" + eventId + ", userId=" + userId + ", category=" + category
               + ", place=" + place + '}';
    }
}

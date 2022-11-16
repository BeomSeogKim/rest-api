package inflearn.whiteship.restapiwithspring.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.EntityModel;

public class EventResource extends EntityModel<Event> {
    @JsonUnwrapped // event로 감싸는 것을 없애줌.
    private Event event;

    public EventResource(Event event) {
        this.event = event;
    }


    public Event getEvent() {
        return event;
    }
}

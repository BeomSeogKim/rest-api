package inflearn.whiteship.restapiwithspring.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static inflearn.whiteship.restapiwithspring.events.EventStatus.DRAFT;
import static inflearn.whiteship.restapiwithspring.events.EventStatus.PUBLISHED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
//@WebMvcTest     // Web 전용 Bean 들만 등록을 해주고 Repository 는 등록을 안해줌
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

//    @MockBean
//    EventRepository eventRepository;   // EventRepository 를 Mock 으로 등록을 해달라

    @Test
    public void createEvent() throws Exception {

        // Event 생성
        Event event = Event.builder()
                .id(100)        // 계산되어져야 하는 값
                .name("Spring")
                .description("REST API Development with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 11, 14, 20, 32))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 11, 15, 20, 32))
                .beginEventDateTime(LocalDateTime.of(2022, 11, 16, 20, 32))
                .endEventDateTime(LocalDateTime.of(2022, 11, 17, 20, 32))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타트업 팩토리")
                .free(true)         // 계산되어져야 하는 값
                .eventStatus(PUBLISHED)     // 계산되어져야 하는 값
                .build();


        mockMvc.perform(post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())  // 요청받은 정보를 테스트 상에서 확인 할 수 있음
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())    // id가 나오는지 확인
                .andExpect(header().exists("Location"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
                .andExpect(jsonPath("eventStatus").value(DRAFT.name()));


    }

}

package test60.api1;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Data
@Validated
public class MeetData {


    @Pattern(regexp = "publish|stop", message = "ActionType only supports publish,stop")
    private String ActionType;
    //@NotBlank(message = "MeetingRoomID can not be blank")
    private String MeetingRoomID;




}

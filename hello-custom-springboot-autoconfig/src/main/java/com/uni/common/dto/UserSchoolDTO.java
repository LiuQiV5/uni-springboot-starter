package com.uni.common.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class UserSchoolDTO {

    @NotBlank(message = "用户账号不能为空")
    private String userName;

    @NotBlank(message = "学校名不能为空")
    private String schoolName;

    @NotBlank(message = "学校guid不能为空")
    private String schoolGuid;

    private LocalDate localDate;
    
    private List<String> list;

    private Boolean primary;

    private Integer sum;
}

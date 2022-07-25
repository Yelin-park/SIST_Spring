package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
/*
 * @ToString
 * @AllArgsConstructor
 * @Getter
 * @Setter
 */
@Data // Data 애노테이션으로 모든 함수가 오버라이딩 되어짐
public class Restaurant {

 // @Setter로 setter를 생성 onMethod_ = @Autowired로 자동 주입받음
 // onMethod 속성은 생성되는 setChef()에 @Autowired 어노테이션을 추가하도록 한다.
 @Setter(onMethod_ = @Autowired)
 private Chef chef;

}


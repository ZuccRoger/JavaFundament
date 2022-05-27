package roger.com.javafundament.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: 骆佳俊
 * @date: 2022/5/5 9:20 AM
 */
@Controller
@Slf4j
public class StudentController {
  @PostMapping("/regStudent/{name}")
  @ResponseBody
  public String saveUser(String name) throws Exception {
    System.out.println("用户注册成功");
    return "success";
  }
}

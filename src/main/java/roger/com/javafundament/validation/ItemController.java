package roger.com.javafundament.validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * @author: 骆佳俊
 * @date: 2022/5/26 9:26 PM
 */
@RestController
public class ItemController {
  @PostMapping("/item/add")
  public String addItem(@Valid @Validated({Item.Save.class}) @RequestBody Item item) {
    System.out.println(item);
    return "ok";
  }
}

package roger.com.javafundament.validation;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: 骆佳俊
 * @date: 2022/5/26 9:25 PM
 */
@Data
public class Item {
  @NotNull(
      message = "id不能为空",
      groups = {Update.class})
  @Min(value = 1, message = "id必须为正整数")
  private Long id;

  @Valid
  @NotNull(
      message = "props不能为空",
      groups = {Save.class, Update.class})
  @Size(
      min = 1,
      message = "props至少要有一个自定义属性",
      groups = {Save.class, Update.class})
  private List<Prop> props;

  /** 保存的时候校验分组 */
  public interface Save {}

  /** 更新的时候校验分组 */
  public interface Update {}

  /** 删除的时候校验分组 */
  public interface Delete {}
}

package roger.com.javafundament.validation;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: 骆佳俊
 * @date: 2022/5/26 9:25 PM
 */
@Data
public class Prop {

  @NotNull(message = "pid不能为空")
  @Min(value = 1, message = "pid必须为正整数")
  private Long pid;

  @NotNull(message = "vid不能为空")
  @Min(value = 1, message = "vid必须为正整数")
  private Long vid;

  @NotBlank(message = "pidName不能为空")
  private String pidName;

  @NotBlank(message = "vidName不能为空")
  private String vidName;
}

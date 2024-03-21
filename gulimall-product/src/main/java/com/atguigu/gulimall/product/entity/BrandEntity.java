package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;


/**
 * 品牌
 * 
 * @author litong
 * @email 1002411753@qq.com
 * @date 2023-12-03 23:14:00
 *
 * @NotEmpty 、@NotBlank 和 @NotNull 这三个注解都是 Java Bean Validation API 中的一部分，主要用于对 Java 对象属性进行约束校验，以确保输入的有效性和完整性。以下是它们的具体区别：
 * @NotNull
 * 作用于任何非原始类型的字段，包括包装类型（如 Integer, String 等）以及引用类型。
 * 它仅检查字段值是否为 null，如果字段值为 null，则校验失败；但如果字段是一个字符串，即使这个字符串内容为空串（""），校验也会通过。
 * @NotEmpty
 * 主要应用于容器类型，例如 List, Set, Map 或数组，以及字符串 (String)。
 * 对于集合类，它会检查集合本身是否为 null 以及其大小是否为零。
 * 对于字符串，它不仅检查字符串是否为 null，还会检查字符串的长度是否为零，也就是说，字符串不能为空串。
 * @NotBlank
 * 专门针对 String 类型设计，通常用于文本输入验证。
 * 不仅要求字符串不为 null，而且要求在去除两端空白字符（trim）之后，字符串的实际内容（非空白字符部分）长度必须大于零。
 * 所以，对于字符串而言，@NotBlank 比 @NotEmpty 更严格，因为它不允许有纯空白字符的字符串。
 * 总结：
 * 当你需要检查某个对象是否为 null 时，使用 @NotNull。
 * 当你要确保一个集合或数组不为 null 并且至少包含一个元素时，使用 @NotEmpty。
 * 当你要验证一个字符串不是 null，并且去除两端空白后还有实际内容时，使用 @NotBlank。
 */
@Data
@TableName("pms_brand")
public class BrandEntity  {
//	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空")
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(message = "品牌logo地址不能为空")
	@URL(message = "logo必须是一个合法的url地址")
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(message = "显示状态[0-不显示；1-显示]不能为空")
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotBlank(message = "检索首字母不能为空")
	@Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母")
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "排序不能为空")
	@Min(value = 0, message = "排序必须为大于等于0的整数")
	private Integer sort;

}

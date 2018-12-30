package cn.unowen.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "prototype")
@Component
public class Book {
	private Integer id;
	
	@NotNull
	@Length(min = 16, max = 16)
	private String idcode;

	@NotNull
	@Length(min = 22, max = 22)
	private String booknumber;

	private Date regtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode == null ? null : idcode.trim();
	}

	public String getBooknumber() {
		return booknumber;
	}

	public void setBooknumber(String booknumber) {
		this.booknumber = booknumber == null ? null : booknumber.trim();
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
}
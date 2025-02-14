package com.welcome.bot.domain;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Entity(name="schedule_tbl")
@JsonPropertyOrder({"scheduleId", "active", "repeat", "runAt"})
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleId;
	
	private Boolean active;
	
	@Column(name = "`repeat`") 
	private Boolean repeat;
	private Date runAt;
	
	@ManyToOne
	@JoinColumn(name = "message_id")
	private Message message;
		
	public Schedule(boolean active, boolean repeat, Date runAt, Message message) {
		this.active = active;
		this.repeat = repeat;
		this.runAt = runAt;
		this.message = message;
	}

	protected Schedule() {
		
	}
		
	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getRepeat() {
		return repeat;
	}

	public void setRepeat(Boolean repeat) {
		this.repeat = repeat;
	}

	public Date getRunAt() {
		return runAt;
	}

	public void setRunAt(Date runAt) {
		this.runAt = runAt;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", active=" + active + ", repeat=" + repeat + ", runAt=" + runAt + ", message="
				+ message + "]";
	}
}


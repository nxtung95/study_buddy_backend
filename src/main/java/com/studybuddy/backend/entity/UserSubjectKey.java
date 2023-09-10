package com.studybuddy.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class UserSubjectKey implements Serializable {
	@Column(name = "user_id")
	Integer userId;

	@Column(name = "subject_id")
	Integer subjectId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserSubjectKey))
			return false;
		UserSubjectKey that = (UserSubjectKey) o;
		return userId.equals(that.userId) && subjectId.equals(that.subjectId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, subjectId);
	}
}

package com.studybuddy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_subject")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserSubject {
	@EmbeddedId
	private UserSubjectKey id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
}

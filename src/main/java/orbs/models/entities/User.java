package orbs.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

// Stores User match history, other statistic
@Data	// lombok's annotation which hides all the JPA's manual annotations
@Entity
public class User {
	private @Id @GeneratedValue Long id;
	private String nickname;

	public User(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return this.nickname;
	}
}

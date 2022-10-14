package egovframework.kit.linc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import egovframework.kit.linc.domain.member.Member;

import java.util.List;

@Entity
@Table(name = "notice")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "noticeAttatchFK", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Files> files;

}

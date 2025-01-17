package umc.stockoneqback.share.domain.model;

import jakarta.persistence.*;
import lombok.*;
import umc.stockoneqback.business.domain.Business;
import umc.stockoneqback.global.base.BaseTimeEntity;
import umc.stockoneqback.global.base.Status;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
/*
 * TODO : file type change required
 * */
@Table(name = "share")
public class Share extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String file;

    private String content;

    @Convert(converter = Category.CategoryConverter.class)
    private Category category;

    @Convert(converter = Status.StatusConverter.class)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_id")
    private Business business;

    @Builder
    public Share(String title, String file, String content, Category category, Business business) {
        this.title = title;
        this.file = file;
        this.content = content;
        this.category = category;
        this.business = business;
        this.status = Status.NORMAL;
    }

    public static Share createShare(String title, String file, String content, Category category, Business business) {
        return new Share(title, file, content, category, business);
    }

    public void update(String title, String file, String content) {
        this.title = title;
        this.file = file;
        this.content = content;
    }
}

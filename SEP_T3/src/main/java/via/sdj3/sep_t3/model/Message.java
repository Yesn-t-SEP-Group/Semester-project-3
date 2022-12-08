package via.sdj3.sep_t3.model;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import via.sdj3.sep_t3.protobuf.ReadDMGrpcDto;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="messages")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_from")
    @ToString.Exclude
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_to")
    @ToString.Exclude
    private User userTo;

    @Column(name = "message_text")
    @Type(type = "org.hibernate.type.TextType")
    private String messageText;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Message message = (Message) o;
        return id != null && Objects.equals(id, message.id);
    }

    public ReadDMGrpcDto convertToGrpc()
    {
        return ReadDMGrpcDto.newBuilder()
                .setMessageText(messageText)
                .setDmId(id)
                .setUserFromId(userFrom.getId())
                .setUserToId(userTo.getId())
                .setPostId(post.getId())
                .build();
    }
}

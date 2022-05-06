package secuwow.MET.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class TeamInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dep_id", nullable = false)
    private DepInfo depInfo;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "create_user", nullable = false)
    private String createUser;

    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @OneToMany(mappedBy = "teamInfo")
    @JsonManagedReference
    private List<UserInfo> userInfo = new ArrayList<>();

}

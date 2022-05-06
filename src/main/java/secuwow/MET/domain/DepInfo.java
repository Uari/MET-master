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
public class DepInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_id")
    private Long depId;

    @Column(name = "dep_name", nullable = false)
    private String depName;

    @Column(name = "create_user", nullable = false)
    private String createUser;

    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @OneToMany(mappedBy = "depInfo")
    @JsonManagedReference
    private List<TeamInfo> teamInfo = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "corp_id", nullable = false)
    private CorpInfo corpInfo;

}

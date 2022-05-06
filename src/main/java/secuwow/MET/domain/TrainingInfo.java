package secuwow.MET.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class TrainingInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    private Long        trainingId;

    @Column(name = "training_name", nullable = false)
    private String      trainingName;

    @Column(name = "training_st_dt", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "training_end_dt", nullable = false)
    private LocalDateTime endTime;

    @CreatedDate
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "creator_user_id", nullable = false)
    private UserInfo    userInfo;

    @OneToMany(mappedBy = "trainingInfo")
    @JsonManagedReference
    private List<TrainingUserInfo> trainingUserInfo = new ArrayList<>();

}

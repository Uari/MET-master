package secuwow.MET.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class CorpInfo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "corp_id")
        private Long corpId;

        @Column(name = "corp_name", nullable = false)
        private String corpName;

        @Column(name = "create_user", nullable = false)
        private String createUser;

        @Column(name = "create_date_time", nullable = false)
        private LocalDateTime createDateTime;

        @JsonManagedReference
        @OneToMany(mappedBy = "corpInfo")
        private List<DepInfo> depInfo = new ArrayList<>();
}

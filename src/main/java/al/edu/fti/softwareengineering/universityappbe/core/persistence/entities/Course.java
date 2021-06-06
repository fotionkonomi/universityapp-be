package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.BaseEntity;
import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.enums.CourseRepeatType;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "course")
@SQLDelete(sql = "UPDATE course SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
public class Course extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date_time", updatable = false, columnDefinition = "DATETIME")
    private Date startDateTime;

    @Column(name = "repeat_count", nullable = false)
    private int repeatCount;

    @Column(name = "repeat_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CourseRepeatType repeatType;
}

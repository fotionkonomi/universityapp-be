package al.edu.fti.softwareengineering.universityappbe.core.persistence.entities;

import al.edu.fti.softwareengineering.universityappbe.core.persistence.entities.common.mappedSuperclasses.SoftDeletionEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "friendship")
@SQLDelete(sql = "UPDATE friendship SET deleted = 1 WHERE ID = ?")
@Where(clause = "deleted <> '1'")
public class Friendship extends SoftDeletionEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_requested_by", referencedColumnName = "id")
    private User requestedBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_requested_to", referencedColumnName = "id")
    private User requestedTo;

    @Column(nullable = false)
    private Boolean active;

}

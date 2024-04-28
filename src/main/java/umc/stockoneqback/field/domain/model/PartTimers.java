package umc.stockoneqback.field.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PartTimers {
    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST)
    private List<PartTimer> partTimers = new ArrayList<>();

    public static PartTimers createPartTimers() {
        return new PartTimers();
    }

    public void addPartTimer(PartTimer partTimer) {
        this.partTimers.add(partTimer);
    }

    public void deletePartTimer(PartTimer partTimer) {
        this.partTimers.remove(partTimer);
    }
}

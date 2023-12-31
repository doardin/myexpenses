package br.com.doardin.myexpenses.domain.category;

import java.time.LocalDateTime;
import java.util.List;

import br.com.doardin.myexpenses.domain.transaction.Transaction;
import br.com.doardin.myexpenses.domain.user.User;
import br.com.doardin.myexpenses.enums.CategoryTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
    @Id
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryTypes type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

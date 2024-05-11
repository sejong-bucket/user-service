package com.sejong.bucketmanager.domain.major.entity;

import com.sejong.bucketmanager.domain.common.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name = "MAJOR_TABLE")
public class Major extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /*public static Major of(String name) {
        return Major.builder()
                .name(name)
                .build();
    }

    public String changeName(String modifiedRepresentName) {
        this.name = modifiedRepresentName;
        return name;
    }*/

    @Override
    public boolean equals(Object obj) {
        Major major = (Major) obj;
        if (!this.id.equals(major.id)) {
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package ru.abdramanova.university_platform.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(nullable = false,length = 400)
    private String content;
    @Column(nullable = false)
    private LocalDateTime deadline;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Material> materials;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Assessment> assessments;
    @ManyToOne
    private SubInGroup subInfo;

    public Task() {
    }

    public Task(String name, String content, LocalDateTime deadline, List<Material> materials, SubInGroup subInfo) {
        this.name = name;
        this.content = content;
        this.deadline = deadline;
        this.materials = materials;
        this.subInfo = subInfo;
    }

    public Task(String name, String content, LocalDateTime deadline, SubInGroup subInfo) {
        this.name = name;
        this.content = content;
        this.deadline = deadline;
        this.subInfo = subInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SubInGroup getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(SubInGroup subInfo) {
        this.subInfo = subInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}